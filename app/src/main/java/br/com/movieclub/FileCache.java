package br.com.movieclub;

import java.io.File;
import android.content.Context;
import android.os.Environment;

public class FileCache {

    private File cache;

    public FileCache(Context context) {
        if (android.os.Environment.getExternalStorageState().equals(Environment.DIRECTORY_PICTURES)) {
            cache = new File(android.os.Environment.getExternalStorageDirectory(), "MovieClub");
        } else {
            cache = context.getCacheDir();
        }

        if (!cache.exists()) {
            cache.mkdirs();
        }
    }

    public File getFile(String url) {
        String fileName = String.valueOf(url.hashCode());
        File file = new File(cache, fileName);

        return file;
    }

    public void clear() {
        File[] files = cache.listFiles();

        if (files == null) {
            return;
        }

        for (File file : files) {
            file.delete();
        }
    }
}