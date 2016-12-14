package br.com.movieclub;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseCreate extends SQLiteOpenHelper {

    public static final String database = "movieclub.db";
    public static final String table = "history";
    public static final String id = "id";
    public static final String search = "search";
    public static final String url = "descricao";
    public static final int version = 1;

    public DatabaseCreate(Context context) {
        super(context, database, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table " + table + " (" +
                id + " integer primary key autoincrement, " +
                search + " text, " +
                url + " text)";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "drop table if exists " + table;
        db.execSQL(query);

        onCreate(db);
    }
}
