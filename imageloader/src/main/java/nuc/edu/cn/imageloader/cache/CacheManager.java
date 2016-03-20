package nuc.edu.cn.imageloader.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weifucheng on 2016/3/20.
 * 为了保证每个缓存模式，只有一个，使用通过CacheManager
 * 来存取Cache
 */
public class CacheManager {
    private static volatile Map<Class<? extends ImageCache>,ImageCache> CacheMap=new HashMap<>();
    public static ImageCache getCache(Class<? extends ImageCache> key) {
        if (!CacheMap.containsKey(key)) {
            synchronized (CacheManager.class) {
                if (!CacheMap.containsKey(key)) {
                    try {
                        //这里不需要同步，即使2和3发生指令重排序也不会影响
                        ImageCache imageCache=key.newInstance();
                        imageCache.init();
                        CacheMap.put(key, imageCache);
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return CacheMap.get(key);
    }

}
