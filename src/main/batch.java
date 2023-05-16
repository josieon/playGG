import com.fasterxml.jackson.databind.*;

import java.net.*;
import java.io.*;
import java.sql.*;
import java.util.logging.*;

import static java.lang.Thread.sleep;

public class batch {
    public static void main(String[] args) throws Exception {
        long num = 6300766751L;
        String key = null;
        for (int i = 1; true;) {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/API_KEY.txt")));
            while((key = br.readLine()) != null){
                for(int j = 0; j < 50; i++, j++) {
                    String matchId = "KR_" + (num + i);
                    getMatchDTO.getMatchDto(matchId, key);
                }
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