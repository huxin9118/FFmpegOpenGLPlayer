package com.example.ffmpegsdlplayer.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by h26376 on 2018/3/21.
 */

public class ImageUtils {
    public static String saveBitmap(Context context, Bitmap bitmap, String path, String name){
        if(context == null){
            return null;
        }
        if(bitmap == null){
            return null;
        }
        if(path == null){
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                path = context.getExternalCacheDir().getPath();
            }
            else{
                path = context.getCacheDir().getPath();
            }
        }
        if(name == null){
            name = UUID.randomUUID().toString();
        }
        File saveFile = new File(path,name+".png");
        FileOutputStream fos = null;
        if(!saveFile.exists()){
            saveFile.getParentFile().mkdirs();
        }
        else{
            return null;
        }

        try{
            fos = new FileOutputStream(saveFile);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fos);
            fos.flush();
            return saveFile.getPath();
        }
        catch (IOException e){
            e.printStackTrace();
            return null;
        }
        finally {
            if(fos != null) {
                try {
                    fos.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
