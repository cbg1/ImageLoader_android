package nuc.edu.cn.imageloader.cache;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by weifucheng on 2016/3/20.
 */
public class DoubleCache implements ImageCache {
    ImageCache mMemoryCache=null;
    ImageCache mDiskCache=null;
    public DoubleCache(Context context){
        mMemoryCache=new MemoryCache();
        mDiskCache=new DiskCache(context);
    }

    @Override
    public void put(String url, Bitmap bmp) {
        mMemoryCache.put(url,bmp);
        mDiskCache.put(url,bmp);
    }

    @Override
    public Bitmap get(String url) {
        Bitmap bitmap=mMemoryCache.get(url);
        if(bitmap==null){
            bitmap=mDiskCache.get(url);
        }
        return bitmap;
    }
}
