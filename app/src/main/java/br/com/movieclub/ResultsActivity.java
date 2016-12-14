package br.com.movieclub;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ResultsActivity extends Activity {

    ProgressDialog progressDialog;
    ArrayList<HashMap<String, String>> arrayList;

    static String apiUrl = "http://api.themoviedb.org/3/search/movie?";
    static String apiKey = "api_key="; // API-KEY
    static String apiQuery = "";
    static String apiSearch = "";
    static String apiPage = "&page=";
    static String apiCurrentPage = "1";

    static String tagAdult = "adult";
    static String tagTitle = "title";
    static String tagOriginalTitle = "original_title";
    static String tagPosterPath = "poster_path";
    static String tagOverview = "overview";
    static String tagPopularity = "popularity";
    static String tagReleaseDate = "release_date";
    static String tagVoteAverage = "vote_average";
    static String tagTotalPages = "total_pages";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_results);

        Intent intent = getIntent();
        apiSearch = intent.getSerializableExtra("et_main_search").toString();
        apiQuery = "&query=";
        apiQuery += intent.getSerializableExtra("et_main_search").toString();
        apiQuery = apiQuery.trim().replace("  ", " ").replace(" ", "%20");

        DatabaseController crud = new DatabaseController(getBaseContext());
        crud.insertData(apiSearch, apiUrl + apiKey + apiQuery);

        new DownloadJSON().execute();
    }

    private class DownloadJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(ResultsActivity.this);
            progressDialog.setTitle("Aguarde...");
            progressDialog.setMessage("Estamos realizando sua busca :D");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            arrayList = new ArrayList<HashMap<String, String>>();
            JSONObject jObject = JSONFunctions.getJSONFromUrl(apiUrl + apiKey + apiQuery + apiPage + apiCurrentPage);

            try {
                JSONArray jArray = jObject.getJSONArray("results");

                for (int i = 0; i < jArray.length(); i++) {
                    HashMap<String, String> map = new HashMap<String, String>();

                    jObject = jArray.getJSONObject(i);
                    map.put("adult", jObject.getString("adult"));
                    map.put("title", jObject.getString("title"));
                    map.put("original_title", jObject.getString("original_title"));
                    map.put("poster_path", "http://image.tmdb.org/t/p/w500" + jObject.getString("poster_path"));
                    map.put("overview", jObject.getString("overview"));
                    map.put("popularity", jObject.getString("popularity"));
                    map.put("release_date", jObject.getString("release_date"));
                    map.put("vote_average", jObject.getString("vote_average"));

                    arrayList.add(map);
                }
            } catch (JSONException e) {
                Log.e("Erro:", e.getMessage());
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            ListView listView = (ListView) findViewById(R.id.lv_listview_results);
            ListViewAdapter adapter = new ListViewAdapter(ResultsActivity.this, arrayList);
            listView.setAdapter(adapter);
            progressDialog.dismiss();
        }
    }
}