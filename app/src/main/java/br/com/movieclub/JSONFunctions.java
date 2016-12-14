package br.com.movieclub;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class JSONFunctions {

    public static JSONObject getJSONFromUrl(String url) {
        InputStream inputStream = null;
        String result = "";
        JSONObject jArray = null;

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            inputStream = entity.getContent();
        } catch (Exception ex) {
            Log.e("Log_tag", "Erro na conexão HTTP " + ex.toString());
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            inputStream.close();
            result = sb.toString();
        } catch (Exception ex) {
            Log.e("Log-tag", "Erro na conversão " + ex.toString());
        }

        try {
            jArray = new JSONObject(result);
        } catch (JSONException ex) {
            Log.e("Log_tag", "Erro parsing " + ex.toString());
        }

        return jArray;
    }

}
