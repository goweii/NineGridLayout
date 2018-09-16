package per.goweii.ninegridlayout.ninegridlayout.base;

import android.content.Context;
import android.view.View;

import per.goweii.ninegridlayout.ninegridlayout.NineGridLayout;

/**
 * 描述：
 *
 * @author Cuizhen
 * @date 2018/9/12
 */
public interface NineGridAdapter<V extends View, E> {

    V onCreateChildView(Context context, NineGridLayout<V, E> parent, int position);

    void onBindData(Context context, V childView, E data, int position);

    void notifyDataSetChanged();

}
