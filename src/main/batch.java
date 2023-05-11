import com.fasterxml.jackson.databind.*;

import java.net.*;
import java.io.*;
import java.sql.*;
import java.util.logging.*;

import static java.lang.Thread.sleep;

public class batch {
    public static void main(String[] args) throws Exception {
        long num = 6300098993L;
//        String matchId = "KR_" + (num);

        for (int i = 1; ;) {
//            System.out.println(matchId);
            for(int j = 0; j < 50;i++, j++) {
                String matchId = "KR_" + (num + i);
                getMatchDTO.getMatchDto(matchId, "RGAPI-58f34acb-d75d-4d57-ae97-f6f5fb125fb6");
            }
            for(int j = 0; j < 50;i++, j++) {
                String matchId = "KR_" + (num + i);
                getMatchDTO.getMatchDto(matchId, "RGAPI-82c25212-ef4f-41c6-9725-aafb3f47d070");
            }
            for(int j = 0; j < 50;i++, j++) {
                String matchId = "KR_" + (num + i);
                getMatchDTO.getMatchDto(matchId, "RGAPI-be0039aa-75df-496a-818a-43e9fbfea52d");
            }
            sleep(60000);
        }
//        Logger logger = Logger.getLogger("mylogger");
//        String log = "log.log";
//        FileHandler logFile = null;
//        try {
//            logFile = new FileHandler(log, true);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        logFile.setFormatter(new SimpleFormatter());
//        logFile.setLevel(Level.ALL);
//        logger.addHandler(logFile);
//
//        logger.info(matchId);
    }
}