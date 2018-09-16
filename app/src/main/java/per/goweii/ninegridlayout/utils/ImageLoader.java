package per.goweii.ninegridlayout.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import per.goweii.ninegridlayout.BlurTransformation;

/**
 * 图片加载，方便统一管理
 *
 * @author Cuizhen
 * @date 2018/5/7-下午5:52
 */
public class ImageLoader {

    public static void image(Context context, ImageView imageView, String url) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(imageView);
    }

    public static void imageBlur(Context context, ImageView imageView, String url, float percent) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
                        .dontAnimate()
                        .transform(new BlurTransformation(percent))
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(imageView);
    }
}
