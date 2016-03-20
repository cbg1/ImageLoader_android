package nuc.edu.cn.imageloader.config;

import android.content.Context;

import nuc.edu.cn.imageloader.cache.ImageCache;
import nuc.edu.cn.imageloader.cache.MemoryCache;

/**
 * Created by weifucheng on 2016/3/20.
 * 建造者模式
 */
public class ImageLoaderConfig {
    public ImageCache imageCache=new MemoryCache();
    public DisplayConfig displayConfig=new DisplayConfig();
    public int threadCount=Runtime.getRuntime().availableProcessors()+1;
    public Context context;
    public ImageCache getImageCache() {
        return imageCache;
    }

    public void setImageCache(Class<? extends ImageCache> imageCache) {
        try {
            ImageCache IC=imageCache.newInstance();
            IC.setContext(context);
            this.imageCache=IC;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
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
