package per.goweii.ninegridlayout;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

import per.goweii.ninegridlayout.utils.bitmap.blur.BlurUtils;

/**
 * 描述：
 *
 * @author Cuizhen
 * @date 2018/9/13
 */
public class BlurTransformation extends BitmapTransformation {

    private static final String ID = BlurTransformation.class.getName();

    private static float PERCENT = 0.1F;

    private float percent;

    public BlurTransformation() {
        this(PERCENT);
    }

    public BlurTransformation(float percent) {
        this.percent = percent;
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        return BlurUtils.blurByPercent(toTransform, percent, false);
    }

    @Override
    public String toString() {
        return "BlurTransformation(percent=" + percent + ")";
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof BlurTransformation && ((BlurTransformation) o).percent == percent;
    }

    @Override
    public int hashCode() {
        return (ID.hashCode() + (int) (percent * 1000));
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update((ID + percent).getBytes(CHARSET));
    }
}
