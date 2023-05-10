import java.net.*;
import java.io.*;

public class Mmain {
    public static void main(String[] args) {
        String matchId = "KR_6300000005";
        String apiKey = "RGAPI-12ee4b3d-b665-47b2-bbb9-fffd4540964b";
        String apiUrl = "https://asia.api.riotgames.com/lol/match/v5/matches/" + matchId + "?api_key=" + apiKey;

        int connTimeout = 5000;
        int readTimeout = 5000;
        URL url = null;
        HttpURLConnection urlConnection = null;
        String readLine = null;

        StringBuilder buffer = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {
            url = new URL(apiUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(connTimeout);
            urlConnection.setReadTimeout(readTimeout);
            urlConnection.setRequestProperty("Accept", "application/json;");

            buffer = new StringBuilder();
            if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
                while((readLine = bufferedReader.readLine()) != null)
                    buffer.append(readLine).append("\n");
            } else {
                buffer.append("code : ");
                buffer.append(urlConnection.getResponseCode()).append("\n");
                buffer.append("message : ");
                buffer.append(urlConnection.getResponseMessage()).append("\n");
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) bufferedWriter.close();
                if (bufferedReader != null) bufferedReader.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
        System.out.println("결과 : " + buffer.toString());
    }
}