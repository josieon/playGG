package batch;

import com.fasterxml.jackson.databind.*;

import java.net.*;
import java.io.*;
import java.sql.*;
import java.util.logging.*;

import static java.lang.Thread.sleep;

public class batch {
    public static FileHandler logFile;
    public static void main(String[] args) throws Exception {
        long num = 6300855792L;
        String key = null;
        for (int i = 1; true;) {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/API_KEY.txt")));
            while((key = br.readLine()) != null){
                for(int j = 0; j < 20; i++, j++) {
                    String matchId = "KR_" + (num + i);
                    Thread thread = new Thread(new pipeline(matchId, key));
                    thread.start();
                }
            }
            sleep(25000);
        }
    }
}