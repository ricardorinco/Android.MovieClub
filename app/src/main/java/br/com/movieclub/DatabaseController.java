package br.com.movieclub;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseController {
    private DatabaseCreate database;

    public DatabaseController (Context context) {
        database = new DatabaseCreate(context);
    }

    public String insertData(String search, String url) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseCreate.search, search);
        values.put(DatabaseCreate.url, url);
        long result = db.insert(DatabaseCreate.table, null, values);
        db.close();

        if (result == -1) {
            return "Erro ao inserir o registro.";
        } else {
            return "Registro inserido com sucesso";
        }
    }

    public Cursor loadData() {
        SQLiteDatabase db = database.getReadableDatabase();

        Cursor cursor = db.rawQuery("select " +
                database.id + " as _id, " +
                database.search + " " +
                "from " + database.table + " " +
                "order by _id desc", null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();

        return cursor;
    }

    public int deleteData() {
        SQLiteDatabase db = database.getWritableDatabase();
        int result = db.delete(database.table, null, null);
        db.close();

        return result;
    }

}
