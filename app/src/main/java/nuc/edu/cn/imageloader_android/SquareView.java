package nuc.edu.cn.imageloader_android;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by weifucheng on 2016/3/20.
 */
public class SquareView extends ImageView {
    public SquareView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
