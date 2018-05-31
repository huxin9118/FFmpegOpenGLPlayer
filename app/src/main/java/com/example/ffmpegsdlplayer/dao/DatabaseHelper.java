package com.example.ffmpegsdlplayer.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ffmpegsdlplayer.base.MyApplication;

/**
 * Created by huxin on 2017/2/27.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String URL =
            "create table url ( " +
                    "time TIMESTAMP NOT NULL,"+
                    "url VARCHAR(512) NOT NULL," +
                    "type VARCHAR(20) NOT NULL," +
                    "thumbnailPath VARCHAR(512) NULL)";

    private DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,name,factory,version);
    }

    /**
     * 饿汉单例模式：
     * 在类加载时就完成了初始化，所以类加载较慢，但获取对象的速度快,无法自定义参数
     */
    private static final DatabaseHelper instance = new DatabaseHelper(MyApplication.getContext(),
            "sdl_player.db",null,1);//静态私有成员，已初始化

    public static DatabaseHelper getInstance(){
        return instance;
    }


    /**
     * 懒汉单例模式：
     * 类加载时，不创建实例，因此类加载速度快，但运行时获取对象的速度慢(对象延迟加载 Lazy Loading)
     */
//    public static DatabaseHelper getInstance(){
//        return DatabaseHelperSingletonHolder.INSTANCE;
//    }
//
//    private static class DatabaseHelperSingletonHolder {
//        private static final DatabaseHelper INSTANCE = new DatabaseHelper(getContext(), "accountmanagement.db",null,1);
//    }

    /**
     * 双重校验锁（DCL）
     * 类加载时，不创建实例，因此类加载速度快，但运行时获取对象的速度慢(对象延迟加载 Lazy Loading)
     * 可传入参数
     */
//    private volatile static DatabaseHelper instance;
//    public static DatabaseHelper getInstance(Context context){
//        if(instance == null){
//            synchronized (DatabaseHelper.class){
//                if(instance == null){
//                    instance = new DatabaseHelper(context, "accountmanagement.db",null,1);
//                }
//            }
//        }
//        return instance;
//    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(URL);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
