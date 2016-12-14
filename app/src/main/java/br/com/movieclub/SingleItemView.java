package br.com.movieclub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SingleItemView extends Activity {

    static String tagAdult;
    static String tagTitle;
    static String tagOriginalTitle;
    static String tagPosterPath;
    static String tagOverview;
    static String tagPopularity;
    static String tagReleaseDate;
    static String tagVoteAverage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_results_single_item);

        ImageLoader imageLoader = new ImageLoader(this);

        Intent intent = getIntent();
        tagAdult = intent.getStringExtra("tagAdult");
        tagTitle = intent.getStringExtra("tagTitle");
        tagOriginalTitle = intent.getStringExtra("tagOriginalTitle");
        tagPosterPath = intent.getStringExtra("tagPosterPath");
        tagOverview = intent.getStringExtra("tagOverview");
        tagReleaseDate = intent.getStringExtra("tagReleaseDate");
        tagPopularity = intent.getStringExtra("tagPopularity");
        tagVoteAverage = intent.getStringExtra("tagVoteAverage");

        ImageView iv_results_single_adult = (ImageView) findViewById(R.id.iv_results_single_adult);
        TextView tv_results_single_title = (TextView) findViewById(R.id.tv_results_single_title);
        TextView tv_results_single_original_title = (TextView) findViewById(R.id.tv_results_single_original_title);
        ImageView iv_results_single_poster_path = (ImageView) findViewById(R.id.iv_results_single_poster_path);
        TextView tv_results_single_overview = (TextView) findViewById(R.id.tv_results_single_overview);
        TextView tv_results_single_release_date = (TextView) findViewById(R.id.tv_results_single_release_date);
        TextView tv_results_single_popularity = (TextView) findViewById(R.id.tv_results_single_popularity);
        TextView tv_results_single_vote_average = (TextView) findViewById(R.id.tv_results_single_vote_average);

        boolean visible = Boolean.parseBoolean(tagAdult);
        if (!visible) {
            iv_results_single_adult.setVisibility(View.INVISIBLE);
        } else {
            iv_results_single_adult.setVisibility(View.VISIBLE);
        }
        tv_results_single_title.setText(tagTitle);
        tv_results_single_original_title.setText(tagOriginalTitle);
        imageLoader.DisplayImage(tagPosterPath, iv_results_single_poster_path);
        tv_results_single_overview.setText(tagOverview);
        tv_results_single_release_date.setText(tagReleaseDate);
        tv_results_single_popularity.setText(tagPopularity);
        tv_results_single_vote_average.setText(tagVoteAverage);
    }

}
