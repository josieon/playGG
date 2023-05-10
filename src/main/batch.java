import com.fasterxml.jackson.databind.*;

import java.net.*;
import java.io.*;
import java.sql.*;
import java.util.logging.*;

import static java.lang.Thread.sleep;

public class batch {
    public static void main(String[] args) throws Exception {
        long num = 6300034194L;
//        String matchId = "KR_" + (num);

        for (int i = 1; ; i++) {
            if(i % 50 == 0) {  // 50번 request마다 1분 정지
                sleep(60000);
            }
            String matchId = "KR_" + (num + i);
//            System.out.println(matchId);
            getMatchDTO.getMatchDto(matchId);
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