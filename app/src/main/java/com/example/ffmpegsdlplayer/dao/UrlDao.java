package com.example.ffmpegsdlplayer.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.ffmpegsdlplayer.bean.MediaInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by huxin on 2017/2/28.
 */

public class UrlDao {
    private SQLiteDatabase db;
    public UrlDao(SQLiteDatabase db){
        this.db = db;
    }

    public boolean insertOrUpdateTimeOrType(String url, long time, String type){
        Cursor cs = db.query("url", null, "url = ?", new String[]{url}, null, null,null, null);
        if(cs.moveToNext()){
            //以键值对的形式保存要存入数据库的数据
            ContentValues cv = new ContentValues();
            cv.put("time", time);
            cv.put("type", type);
            cv.put("thumbnailPath", cs.getString(cs.getColumnIndex("thumbnailPath")));
            db.update("url", cv,"url = ?", new String[]{url});
            return false;
        }
        else {
            //以键值对的形式保存要存入数据库的数据
            ContentValues cv = new ContentValues();
            cv.put("url", url);
            cv.put("time", time);
            cv.put("type", type);
            db.insert("url",null,cv);
            return true;
        }
    }

    public void insertThumbnailPath(String url, String thumbnailPath){
        Cursor cs = db.query("url", null, "url = ?", new String[]{url}, null, null,null, null);
        if(cs.moveToNext()){
            //以键值对的形式保存要存入数据库的数据
            ContentValues cv = new ContentValues();
            cv.put("thumbnailPath", thumbnailPath);
            db.update("url", cv,"url = ?", new String[]{url});
        }
    }

    public void updateUrl(String oldUrl, String newUrl){
        //以键值对的形式保存要存入数据库的数据
        ContentValues cv = new ContentValues();
        cv.put("url", newUrl);
        db.update("url", cv,"url = ?", new String[]{oldUrl});
    }


    public long delete(String url){
        //返回值是影响的行数
        return db.delete("url","url = ?", new String[]{url});
    }

    public List<MediaInfo> getList(){
        Cursor cs = db.query("url", null, null, null, null, null, "time desc", null);
        List<MediaInfo> mediaInfoList = new ArrayList<>();
        while(cs.moveToNext()){
            //获取指定列的索引值
            MediaInfo mediaInfo = new MediaInfo();
            mediaInfo.setTime(cs.getLong(cs.getColumnIndex("time")));
            mediaInfo.setUrl(cs.getString(cs.getColumnIndex("url")));
            mediaInfo.setType(cs.getString(cs.getColumnIndex("type")));
            mediaInfo.setThumbnailPath(cs.getString(cs.getColumnIndex("thumbnailPath")));
            mediaInfoList.add(mediaInfo);
        }
        cs.close();
        return mediaInfoList;
    }
}
