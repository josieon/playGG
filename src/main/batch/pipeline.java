package batch;

import DTO.*;
import com.fasterxml.jackson.databind.*;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

import static hidden.hidden.*;
import static batch.batch.*;

public class pipeline implements Runnable {
    String matchId;
    String apiKey;

    public pipeline(String matchId, String apiKey) {
        this.matchId = matchId;
        this.apiKey = apiKey;
    }

    // 포지션 enum 클래스
    enum POSITION{
        TOP(0),
        JUNGLE(1),
        MIDDLE(2),
        BOTTOM(3),
        UTILITY(4);
        private final int value;

        POSITION(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    @Override
    public void run() {
        // RIOT API URL
        String apiUrl = "https://asia.api.riotgames.com/lol/match/v5/matches/" + matchId + "?api_key=" + apiKey;
        // DB DRIVER
        String driver = "org.mariadb.jdbc.Driver";
        // DB URL
        String DB_URL = "jdbc:mariadb://" + DB_IP + ":" + 3306 + "/" + DB_NAME;

        //================================================================
        // API GET 메서드 호출
        int connTimeout = 5000; // 커넥션 타임아웃
        int readTimeout = 5000;
        URL url = null;
        HttpURLConnection urlConnection = null;
        String readLine = null;
        StringBuilder buffer = null;    // RESPONSE BODY
        BufferedReader bufferedReader = null;
        boolean flag = true;    // RESPONSE CODE 에러 여부

        // API GET
        try {
            url = new URL(apiUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(connTimeout);
            urlConnection.setReadTimeout(readTimeout);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setDoOutput(true);

            buffer = new StringBuilder();
            if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                // 응답이 정상적일때
                bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
                while((readLine = bufferedReader.readLine()) != null)
                    buffer.append(readLine);
            } else {
                // RESPONSE CODE 를 에러로 받은 경우
                buffer.append(matchId + "\t");
                buffer.append(urlConnection.getResponseCode()).append("\t");    // 404
                buffer.append(urlConnection.getResponseMessage()).append("\n"); // Not Found
                flag = false;
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        // Log 기록 로거
        Logger logger = Logger.getLogger("mylogger");
        String log = "log.log"; // 로그 파일명
//        FileHandler logFile = null;
        synchronized (logFile) {
            try {
                logFile = new FileHandler(log, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            logFile.setFormatter(new SimpleFormatter());
            logFile.setLevel(Level.ALL);

            logger.addHandler(logFile);
            if(!flag) {
                // API RESPONSE CODE가 에러인 경우
                logger.info(buffer.toString());
                logFile.close();
                return;
            } else {
                // API RESPONSE 가 정상적일때
                logger.info(matchId+"\tGET SUCCESS\n");
                logFile.close();
            }
        }

        //================================================================
        // DB UPDATE
        Connection conn = null;
        PreparedStatement pstmt = null;

        // DB 접속
        try {
            // DB 접속 성공
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로드 실패");;
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("DB 접속 실패");
            e.printStackTrace();
        }

        // 테이블 UPDATE
        try {
            // 이미 DB에 있는지 판단(추후 API 호출 전으로 위치 변경 필요)
            pstmt = conn.prepareStatement("select match_id from matches where match_id = ?");
            pstmt.setString(1, matchId);
            if(!pstmt.executeQuery().next()) {
            // matches 테이블에 레코드가 없는 경우

                // RESPONSE BODY 가 JSON 이므로 DTO 로 매핑
                ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                matchDTO match = null;
                try {
                    match = objectMapper.readValue(buffer.toString(), matchDTO.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // 협곡 솔랭인 경우만 DB UPDATE
                if(match.info.queueId == 420) {

                    // matches 테이블 INSERT 수행
                    pstmt = conn.prepareStatement(
                            "insert into matches (match_id, game_duration, game_endtime, queue_id, participants, teams) values (?, ?, ?, ?, ?, ?)");
                    pstmt.setString(1, matchId);
                    pstmt.setString(2, match.getInfo().getGameDuration());
                    pstmt.setLong(3, match.getInfo().getEndTimestamp());
                    pstmt.setInt(4, match.getInfo().getQueueId());
                    pstmt.setString(5, match.getInfo().getParticipants());
                    pstmt.setString(6, match.getInfo().getTeams());
                    pstmt.executeUpdate();

                    // RED, BLUE 챔피언 ID 배열 [ TOP, JUNGLE, MIDDLE, BOTTOM, UTILITY ]
                    int[] RED = new int[5];
                    int[] BLUE = new int[5];

                    // 참가자 조회하여 통계 테이블 UPDATE
                    for (participantDTO p : match.info.participants) {

                        // RED, BLUE 배열에 챔피언 ID 할당
                        if (p.teamId == 100) {
                            BLUE[POSITION.valueOf(p.individualPosition).value] = p.championId;
                        } else {
                            RED[POSITION.valueOf(p.individualPosition).value] = p.championId;
                        }

                        // champion_statistic 테이블 INSERT or UPDATE
                        pstmt = conn.prepareStatement("select * from champion_statistic where champion_id = ?");
                        pstmt.setInt(1, p.championId);
                        if (pstmt.executeQuery().next()) {   // 해당하는 레코드가 존재하는 경우
                            pstmt = conn.prepareStatement("update champion_statistic set choices = choices + 1" + (p.win ? ", wins = wins + 1" : "") + " where champion_id = ?");
                            pstmt.setInt(1, p.championId);
                        } else {                            // 해당하는 레코드가 존재하지 않는 경우
                            pstmt = conn.prepareStatement("insert into champion_statistic values " + (p.win ? "(?, ?, 1, 1, 0)" : "(?, ?, 0, 1, 0)"));
                            pstmt.setInt(1, p.championId);
                            Statement stmt = conn.createStatement();
                            ResultSet rs = stmt.executeQuery("SELECT champion_name FROM champion_info where champion_id = " + p.championId);
                            rs.next();
                            pstmt.setString(2, rs.getString("champion_name"));
                        }
                        pstmt.executeUpdate();

                        // item_statistic 테이블 INSERT or UPDATE
                        for (int item : new int[]{p.item0, p.item1, p.item2, p.item3, p.item4, p.item5, p.item6}) {
                            if (conn.createStatement().executeQuery("select * from item_statistic where champion_id = " + p.championId + " and item_id = " + item).next()) {
                                // 해당하는 레코드가 존재하는 경우
                                pstmt = conn.prepareStatement("update item_statistic set choices = choices + 1" + (p.win ? ", wins = wins + 1" : "") + " where champion_id = ? and item_id = ?");
                            } else {
                                // 해당하는 레코드가 존재하지 않는 경우
                                pstmt = conn.prepareStatement("insert into item_statistic values " + (p.win ? "(?, ?, 1, 1)" : "(?, ?, 0, 1)"));
                            }
                            pstmt.setInt(1, p.championId);
                            pstmt.setInt(2, item);
                            pstmt.executeUpdate();
                        }

                        // perk_statistic 테이블 INSERT or UPDATE
                        pstmt = conn.prepareStatement("select * from perk_statistic where champion_id = ? and perks = ?");
                        pstmt.setInt(1, p.championId);
                        pstmt.setString(2, p.perks.getPerks());
                        if (pstmt.executeQuery().next()) {
                            // 해당 레코드가 존재하는 경우
                            pstmt = conn.prepareStatement("update perk_statistic set choices = choices + 1" + (p.win ? ", wins = wins + 1" : "") + " where champion_id = ? and perks = ?");
                        } else {
                            // 해당 레코드가 존재하지 않는 경우
                            pstmt = conn.prepareStatement("INSERT INTO perk_statistic VALUES " + (p.win ? "(?, ?, 1, 1)" : "(?, ?, 1, 0)"));
                        }
                        pstmt.setInt(1, p.championId);
                        pstmt.setString(2, p.perks.getPerks());
                        pstmt.executeUpdate();

                        // spell_statistic 테이블 INSERT or UPDATE
                        int[] spells = new int[]{p.summoner1Id, p.summoner2Id};
                        Arrays.sort(spells);
                        pstmt = conn.prepareStatement("SELECT * FROM spell_statistic WHERE champion_id = ? and summoner1 = ? and summoner2 = ?");
                        pstmt.setInt(1, p.championId);
                        pstmt.setInt(2, spells[0]);
                        pstmt.setInt(3, spells[1]);
                        if (pstmt.executeQuery().next()) {
                            // 해당 레코드가 존재하는 경우
                            pstmt = conn.prepareStatement("UPDATE spell_statistic SET choices = choices + 1" + (p.win ? ", wins = wins + 1" : "") + " WHERE champion_id = ? and summoner1 = ? and summoner2 = ?");
                        } else {
                            // 해당 레코드가 존재하지 않는 경우
                            pstmt = conn.prepareStatement("INSERT INTO spell_statistic VALUES" + (p.win ? "(?, ?, ?, 1, 1)" : "(?, ?, ?, 0, 1)"));
                        }
                        pstmt.setInt(1, p.championId);
                        pstmt.setInt(2, spells[0]);
                        pstmt.setInt(3, spells[1]);
                        pstmt.executeUpdate();

                    }

                    // counter_statistic 테이블 INSERT or UPDATE
                    int winner = match.info.teams.get(0).win ? match.info.teams.get(0).teamId : match.info.teams.get(1).teamId; // 이긴 teamId ( 100: BLUE, 200: RED )
                    for (int i = 0; i < 5; i++) {
                        pstmt = conn.prepareStatement("select * from counter_statistic where champion_id = ? and counter_id = ?");
                        pstmt.setInt(1, RED[i]);
                        pstmt.setInt(2, BLUE[i]);
                        if (pstmt.executeQuery().next()) {
                            pstmt = conn.prepareStatement("update counter_statistic set choices = choices + 1" + (winner == 100 ? ",wins = wins+1" : "") + " where champion_id = ? and counter_id = ?");
                            pstmt.setInt(2, RED[i]);
                            pstmt.setInt(1, BLUE[i]);
                            pstmt.executeUpdate();
                            pstmt = conn.prepareStatement("update counter_statistic set choices = choices + 1" + (winner == 200 ? ",wins = wins+1" : "") + " where champion_id = ? and counter_id = ?");
                            pstmt.setInt(1, RED[i]);
                            pstmt.setInt(2, BLUE[i]);
                            pstmt.executeUpdate();
                        } else {
                            if (winner == 100) {
                                // BLUE 가 이긴 경우
                                Statement stmt = conn.createStatement();
                                stmt.execute(String.format("insert into counter_statistic values (%d, %d, 0, 1)", RED[i], BLUE[i]));
                                stmt.execute(String.format("insert into counter_statistic values (%d, %d, 1, 1)", BLUE[i], RED[i]));
                            } else {
                                // RED 가 이긴 경우
                                Statement stmt = conn.createStatement();
                                stmt.execute(String.format("insert into counter_statistic values (%d, %d, 0, 1)", BLUE[i], RED[i]));
                                stmt.execute(String.format("insert into counter_statistic values (%d, %d, 1, 1)", RED[i], BLUE[i]));
                            }
                        }
                    }

                    // champion_statistic bans INSERT or UPDATE
                    for(teamDTO team : match.info.teams) {
                        for(BanDTO ban : team.bans) {
                            if (ban.championId == -1)
                                continue;
                            pstmt = conn.prepareStatement("SELECT * FROM champion_statistic WHERE champion_id = ?");
                            pstmt.setInt(1, ban.championId);
                            if (pstmt.executeQuery().next()) {
                                // 레코드가 있는 경우
                                pstmt = conn.prepareStatement("UPDATE champion_statistic SET bans = bans + 1 WHERE champion_id = ?");
                            } else {
                                pstmt = conn.prepareStatement("INSERT INTO champion_statistic VALUES (?, ?, 0, 0, 1)");
                                Statement stmt = conn.createStatement();
                                ResultSet rs = stmt.executeQuery("SELECT champion_name FROM champion_info where champion_id = " + ban.championId + ";");
                                rs.next();
                                pstmt.setString(2, rs.getString("champion_name"));
                            }
                            pstmt.setInt(1, ban.championId);
                            pstmt.executeUpdate();
                        }
                    }
                }
            }
        }
        catch (SQLException e) {
            System.out.println("error: " + e);
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
