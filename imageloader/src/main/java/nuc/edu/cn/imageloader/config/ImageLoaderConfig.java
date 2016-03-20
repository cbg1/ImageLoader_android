package nuc.edu.cn.imageloader.config;

import nuc.edu.cn.imageloader.cache.ImageCache;
import nuc.edu.cn.imageloader.cache.MemoryCache;

/**
 * Created by weifucheng on 2016/3/20.
 * 这个类中的配置属性不应该public，可能会导致用户错误使用
 * 建造者模式
 */
public class ImageLoaderConfig {
    private ImageCache imageCache=new MemoryCache();
    private DisplayConfig displayConfig=new DisplayConfig();
    private int threadCount=Runtime.getRuntime().availableProcessors()+1;

    public ImageCache getImageCache() {
        return imageCache;
    }

    public void setImageCache(ImageCache imageCache) {
        if (imageCache!=null)
        this.imageCache = imageCache;
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
