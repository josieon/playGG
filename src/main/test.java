import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.logging.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class test {
    public static void main(String[] args) throws Exception {
        String matchId = "KR_6478591086";
        String apiKey = "RGAPI-fb809792-8ba3-4779-83fe-2438e5b097db";
        String apiUrl = "https://asia.api.riotgames.com/lol/match/v5/matches/" + matchId + "?api_key=" + apiKey;
        System.out.println(apiUrl);

        String driver = "org.mariadb.jdbc.Driver";
        String DB_IP = "localhost";
        String DB_PORT = "3306";
        String DB_NAME = "playGG";
        String DB_URL = "jdbc:mariadb://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;

        int connTimeout = 5000;
        int readTimeout = 5000;
        URL url = null;
        HttpURLConnection urlConnection = null;
        String readLine = null;

        StringBuilder buffer = null;
        BufferedReader bufferedReader = null;
        InputStream inputStream = null;
        BufferedWriter bufferedWriter = null;
//        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        boolean flag = true;

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
                bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
                while((readLine = bufferedReader.readLine()) != null)
                    buffer.append(readLine);
            } else {
                buffer.append("code : ");
                buffer.append(urlConnection.getResponseCode()).append("\n");
                buffer.append("message : ");
                buffer.append(urlConnection.getResponseMessage()).append("\n");
                flag = false;
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) bufferedWriter.close();
//                if (bufferedReader != null) bufferedReader.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }


        if(!flag) {
            System.out.println(buffer.toString());
            return;
        } else {
            System.out.println("GET SUCCESS");
        }


        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject)parser.parse(buffer.toString());
        ObjectMapper mapper = new ObjectMapper();
        JSONObject meta = (JSONObject)jsonObject.get("metadata");
        System.out.println(mapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(((JSONObject)jsonObject.get("metadata")).get("matchId")));

//
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//
//        try {
//            Class.forName(driver);
//            conn = DriverManager.getConnection(DB_URL, "root", "playdata");
//        } catch (ClassNotFoundException e) {
//            System.out.println("드라이버 로드 실패");;
//            e.printStackTrace();
//        } catch (SQLException e) {
//            System.out.println("DB 접속 실패");
//            e.printStackTrace();
//        }
//
//        try {
//            ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//            matchDTO match = objectMapper.readValue(buffer.toString(), matchDTO.class);
//            pstmt = conn.prepareStatement("insert into matches (matchId, matchDTO) values (?, ?)");
//            pstmt.setString(1, matchId);
//            pstmt.setString(2, buffer.toString());
//            pstmt = conn.prepareStatement(
//                    "insert into matches_new (match_id, game_duration, game_endtime, queue_id, participants, teams) values (?, ?, ?, ?, ?, ?)");
//            pstmt.setString(1, matchId);
//            pstmt.setString(2, match.getInfo().getGameDuration());
//            pstmt.setString(3, match.getInfo().getEndTimestamp());
//            pstmt.setInt(4, match.getInfo().getQueueId());
//            pstmt.setString(5, match.getInfo().getParticipants());
//            pstmt.setString(6, match.getInfo().getTeams());
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println("error: " + e);
//        } finally {
//            if (pstmt != null) {
//                pstmt.close();
//            }
//            if (conn != null && !conn.isClosed()) {
//                conn.close();
//            }
//        }

    }
}
