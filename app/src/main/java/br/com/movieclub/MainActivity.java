package br.com.movieclub;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt_main_search = (Button) findViewById(R.id.bt_main_search);
        Button bt_main_history = (Button) findViewById(R.id.bt_main_history);

        bt_main_search.setOnClickListener(this);
        bt_main_history.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_main_search:
                EditText et_main_search = (EditText) findViewById(R.id.et_main_search);

                if (et_main_search.getText().toString().isEmpty()) {
                    AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                    alertDialog.setTitle("OPS!");
                    alertDialog.setMessage("Informe o nome de um filme para efetuar a busca! ;)");
                    alertDialog.setIcon(getResources().getDrawable(android.R.drawable.ic_dialog_alert));
                    alertDialog.show();

                    et_main_search.requestFocus();
                }
                else {
                    boolean connected = false;
                    ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                    if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                        connected = true;
                    }
                    else {
                        connected = false;
                    }

                    if (connected) {
                        Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
                        intent.putExtra("et_main_search", et_main_search.getText().toString());
                        startActivity(intent);
                    } else {
                        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                        alertDialog.setTitle("OPS!");
                        alertDialog.setMessage("Verifique a sua conexão com a internet! :(");
                        alertDialog.setIcon(getResources().getDrawable(android.R.drawable.ic_dialog_alert));
                        alertDialog.show();

                        et_main_search.requestFocus();
                    }
                }
                break;
            case R.id.bt_main_history:
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
                break;
        }
    }
}
