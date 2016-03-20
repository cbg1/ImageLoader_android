package nuc.edu.cn.imageloader.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import nuc.edu.cn.imageloader.utils.CloseUtils;

/**
 * Created by weifucheng on 2016/3/20.
 */
public class DiskCache implements ImageCache {
    private static final String TAG="DiskCache";
    private static String cacheDir=null;
    private Context mContext;
    @Override
    public void setContext(Context context){
         mContext=context;
    }
    private void initFile() {
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            cacheDir=mContext.getExternalCacheDir().getPath();
            Log.d(TAG,"have SD");
        }else {
            cacheDir=mContext.getCacheDir().getPath();
            Log.d(TAG," not have SD");
        }
        Log.d(TAG,cacheDir);
        File file=new File(cacheDir);
        if(!file.exists()) {
            Log.d(TAG, "file no exists");
            file.mkdirs();
        }
    }


    @Override
    public void put(String url, Bitmap bmp) {
        FileOutputStream fileOutputStream=null;
        try {
            fileOutputStream=new FileOutputStream(cacheDir+"/"+url);
            bmp.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
            Log.d(TAG,url+"pic diskcache success");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }  finally {
            CloseUtils.closeQuietly(fileOutputStream);
        }
    }

    @Override
    public Bitmap get(String url) {
        Log.d(TAG, cacheDir+url+"come from Disk");
        if(null==cacheDir) initFile();
        return BitmapFactory.decodeFile(cacheDir+"/"+url);
    }

}
