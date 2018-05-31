package com.example.ffmpegsdlplayer.dao;

import android.database.sqlite.SQLiteDatabase;

import com.example.ffmpegsdlplayer.bean.MediaInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by huxin on 2017/2/28.
 */

public class UrlService {
    private DatabaseManager databaseManager;

    public UrlService(){
        databaseManager = DatabaseManager.getInstance(DatabaseHelper.getInstance());
    }

    public boolean addOrUpdateTimeOrType(String url, long time, String type){
        SQLiteDatabase db = databaseManager.getWritableDatabase();
        UrlDao dao = new UrlDao(db);
        if(dao.insertOrUpdateTimeOrType(url,time,type)) {
            databaseManager.closeDatabase();
            return true;
        }
        else {
            databaseManager.closeDatabase();
            return false;
        }
    }

    public void updateThumbnailPath(String url, String thumbnailPath){
        SQLiteDatabase db = databaseManager.getWritableDatabase();
        UrlDao dao = new UrlDao(db);
        dao.insertThumbnailPath(url,thumbnailPath);
        databaseManager.closeDatabase();
    }

    public void updateUrl(String oldUrl, String newUrl){
        SQLiteDatabase db = databaseManager.getWritableDatabase();
        UrlDao dao = new UrlDao(db);
        dao.updateUrl(oldUrl,newUrl);
    }

    public long removeUrl(String url){
        SQLiteDatabase db = databaseManager.getWritableDatabase();
        UrlDao dao = new UrlDao(db);
        long id = dao.delete(url);
        databaseManager.closeDatabase();
        return id;
    }

    public List<MediaInfo> getMediaInfoList(){
        SQLiteDatabase db = databaseManager.getWritableDatabase();
        UrlDao dao = new UrlDao(db);
        List<MediaInfo> list = dao.getList();
        databaseManager.closeDatabase();
        return list;
    }
}

