package per.goweii.ninegridlayout.ninegridlayout.adapter;

import android.content.Context;
import android.widget.ImageView;

import per.goweii.ninegridlayout.ninegridlayout.NineGridLayout;

/**
 * 描述：
 *
 * @author Cuizhen
 * @date 2018/9/11
 */
public abstract class BaseNineGridImageAdapter<E> extends BaseNineGridAdapter<ImageView, E> {

    @Override
    public ImageView onCreateChildView(Context context, NineGridLayout<ImageView, E> veNineGridLayout, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }
}