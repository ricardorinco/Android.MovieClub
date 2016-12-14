package br.com.movieclub;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ListViewAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    ImageLoader imageLoader;
    HashMap<String, String> result = new HashMap<String, String>();

    public ListViewAdapter(Context context, ArrayList<HashMap<String, String>> arrayList) {
        this.context = context;
        data= arrayList;
        imageLoader = new ImageLoader(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View itemView = inflater.inflate(R.layout.activity_listview_results_items, parent, false);
        result = data.get(position);

        TextView tv_results_title = (TextView) itemView.findViewById(R.id.tv_results_title);
        ImageView iv_results_poster_path = (ImageView) itemView.findViewById(R.id.iv_results_poster_path);

        tv_results_title.setText(result.get(ResultsActivity.tagTitle));
        imageLoader.DisplayImage(result.get(ResultsActivity.tagPosterPath), iv_results_poster_path);
        String totalPages = result.get(ResultsActivity.tagTotalPages);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = data.get(position);
                Intent intent = new Intent(context, SingleItemView.class);
                intent.putExtra("tagAdult", result.get(ResultsActivity.tagAdult));
                intent.putExtra("tagTitle", result.get(ResultsActivity.tagTitle));
                intent.putExtra("tagOriginalTitle", result.get(ResultsActivity.tagOriginalTitle));
                intent.putExtra("tagPosterPath", result.get(ResultsActivity.tagPosterPath));
                intent.putExtra("tagOverview", result.get(ResultsActivity.tagOverview));
                intent.putExtra("tagPopularity", result.get(ResultsActivity.tagPopularity));
                intent.putExtra("tagReleaseDate", result.get(ResultsActivity.tagReleaseDate));
                intent.putExtra("tagVoteAverage", result.get(ResultsActivity.tagVoteAverage));

                context.startActivity(intent);
            }
        });

        return itemView;
    }
}