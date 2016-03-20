package nuc.edu.cn.imageloader.config;

import android.content.Context;
import android.util.Log;

import nuc.edu.cn.imageloader.cache.CacheManager;
import nuc.edu.cn.imageloader.cache.ImageCache;
import nuc.edu.cn.imageloader.cache.MemoryCache;

/**
 * Created by weifucheng on 2016/3/20.
 * 建造者模式
 */
public class ImageLoaderConfig {
    private static final String TAG="ImageLoaderConfig";
    public ImageCache imageCache=new MemoryCache();
    public DisplayConfig displayConfig=new DisplayConfig();
    public int threadCount=Runtime.getRuntime().availableProcessors()+1;
    public Context context;
    public void setImageCache(Class<? extends ImageCache> imageCache) {
        ImageCache IC= CacheManager.getCache(imageCache);
        if(IC==null){
            Log.d(TAG,"IC为空============");
        }
        Log.d(TAG,IC.getClass().getName()+"====");
        IC.setContext(context);
        this.imageCache=IC;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = Math.max(threadCount,1);
    }

    public void setLoadingPlaceholder(int ResId){
        displayConfig.loadingResId=ResId;
    }
    public void setNotFoundPlaceholder(int ResId){
        displayConfig.failedResId=ResId;
    }
}
