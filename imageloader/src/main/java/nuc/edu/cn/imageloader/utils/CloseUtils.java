package nuc.edu.cn.imageloader.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by weifucheng on 2016/3/20.
 */
public final class CloseUtils {
    private CloseUtils(){}
    public static void closeQuietly(Closeable closeable){
        if(null!=closeable){
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
