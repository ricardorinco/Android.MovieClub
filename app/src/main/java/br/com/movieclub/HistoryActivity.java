package br.com.movieclub;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class HistoryActivity extends Activity implements View.OnClickListener {

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_history);

        Button bt_listview_history_delete = (Button) findViewById(R.id.bt_listview_history_delete);
        bt_listview_history_delete.setOnClickListener(this);

        DatabaseController crud = new DatabaseController(getBaseContext());
        Cursor cursor = crud.loadData();

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getBaseContext(),
                R.layout.activity_listview_history_items,
                cursor,
                new String[] {  DatabaseCreate.search },
                new int[] { R.id.tv_history_search });

        ListView listView = (ListView) findViewById(R.id.lv_listview_history_search);
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        DatabaseController crud = new DatabaseController(getBaseContext());
        int delete = crud.deleteData();

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        if (delete > 0) {
            alertDialog.setTitle("Yeah!");
            alertDialog.setMessage("Seu histórico foi apagado com sucesso! ;)");
            alertDialog.setIcon(getResources().getDrawable(android.R.drawable.ic_dialog_info));
            alertDialog.show();

            Intent intent = new Intent(HistoryActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            alertDialog.setTitle("OOOOOPS!");
            alertDialog.setMessage("Tivemos um problema ao tentar apagar seu histórico! :(");
            alertDialog.setIcon(getResources().getDrawable(android.R.drawable.ic_dialog_info));
            alertDialog.show();
        }
    }
}