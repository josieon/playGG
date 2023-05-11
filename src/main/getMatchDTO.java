import com.fasterxml.jackson.databind.*;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

public class getMatchDTO {
    public static void getMatchDto(String matchId, String apiKey) throws Exception {
        String apiUrl = "https://asia.api.riotgames.com/lol/match/v5/matches/" + matchId + "?api_key=" + apiKey;
        System.out.println(apiUrl);

        String driver = "org.mariadb.jdbc.Driver";
        String DB_IP = "localhost";
        String DB_PORT = "3306";
        String DB_NAME = "playGG";
        String DB_URL = "jdbc:mariadb://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;
        Logger logger = Logger.getLogger("mylogger");
        String log = "log.log";
        FileHandler logFile = null;
        try {
            logFile = new FileHandler(log, true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        logFile.setFormatter(new SimpleFormatter());
        logFile.setLevel(Level.ALL);
        logger.addHandler(logFile);

        logger.info(matchId);

        int connTimeout = 5000;
        int readTimeout = 5000;
        URL url = null;
        HttpURLConnection urlConnection = null;
        String readLine = null;

        StringBuilder buffer = null;
        BufferedReader bufferedReader = null;
        InputStream inputStream = null;
        BufferedWriter bufferedWriter = null;
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
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
//                matchDTO m = mapper.readValue(urlConnection.getInputStream(), matchDTO.class);
//                buffer.append(m);
//                buffer.append("GET SUCCESSFUL");
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
            logger.info(buffer.toString());
            logFile.close();
            return;
        } else {
            System.out.println("GET SUCCESS");
            logger.info("GET SUCCESS\n");
            logFile.close();
        }


        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, "root", "playdata");
//            if (conn != null) {
//                System.out.println("DB 접속 성공\n");
//            }
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로드 실패");;
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("DB 접속 실패");
            e.printStackTrace();
        }

        try {
            pstmt = conn.prepareStatement("insert into matches (matchId, matchDTO) values (?, ?)");
            pstmt.setString(1, matchId);
            pstmt.setString(2, buffer.toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error: " + e);
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }

    }
}
