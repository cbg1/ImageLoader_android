package nuc.edu.cn.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import nuc.edu.cn.imageloader.cache.ImageCache;
import nuc.edu.cn.imageloader.cache.MemoryCache;
import nuc.edu.cn.imageloader.config.ImageLoaderConfig;

/**
 * Created by weifucheng on 2016/3/19.
 */
public class ImageLoader {
    private ImageLoader(){}
    private ImageLoaderConfig mConfig;
    ImageCache mImageCache=new MemoryCache();
    ExecutorService mExecutorService= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    public void displayImage(final String url,final ImageView imageView){
        Bitmap bitmap=mImageCache.get(url);
        if(bitmap!=null){
            imageView.setImageBitmap(bitmap);
            return;
        }
        imageView.setTag(url);
        subitmLoadRequest(url, imageView);
    }
    public void setImageCache(ImageCache imageCache){
        this.mImageCache=imageCache;
    }
    private void subitmLoadRequest(final String url, final ImageView imageView) {
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap=downloadImage(url);
                if (bitmap==null){
                    return;
                }
                if(imageView.getTag().equals(url)){
                    imageView.setImageBitmap(bitmap);
                }
                mImageCache.put(url,bitmap);
            }
        });
    }

    public Bitmap downloadImage(String imageUrl){
        Log.d("download", imageUrl + "come from download");
        Bitmap bitmap=null;
        try {
            URL url=new URL(imageUrl);
            final HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            bitmap= BitmapFactory.decodeStream(conn.getInputStream());
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    public void applyConfig(ImageLoaderConfig imageLoaderConfig){
        this.mConfig=imageLoaderConfig;
    }
    public static ImageLoader getInstance(){
        return ImageLoaderHolder.sImageLoader;
    }
    private static class ImageLoaderHolder{
        private static final ImageLoader sImageLoader=new ImageLoader();
    }
    public static class Builder{
        ImageLoaderConfig iConfig=new ImageLoaderConfig();
        public ImageLoader.Builder setImageCache(ImageCache imageCache){
                iConfig.setImageCache(imageCache);
                return this;
        }
        public Builder setThreadCount(int threadCount) {
            iConfig.setThreadCount(threadCount);
            return this;
        }

        public Builder setLoadingPlaceholder(int ResId){
           iConfig.setLoadingPlaceholder(ResId);
            return this;
        }
        public Builder setNotFoundPlaceholder(int ResId){
            iConfig.setNotFoundPlaceholder(ResId);
            return this;
        }
        public ImageLoader create(){
            ImageLoader imageLoader=ImageLoader.getInstance();
            imageLoader.applyConfig(iConfig);
            return imageLoader;
        }

    }
}
