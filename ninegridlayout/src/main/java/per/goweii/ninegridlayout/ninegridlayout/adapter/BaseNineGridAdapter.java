package per.goweii.ninegridlayout.ninegridlayout.adapter;

import android.view.View;

import java.util.List;

import per.goweii.ninegridlayout.ninegridlayout.NineGridLayout;
import per.goweii.ninegridlayout.ninegridlayout.base.NineGridAdapter;

/**
 * 描述：
 *
 * @author Cuizhen
 * @date 2018/9/11
 */
public abstract class BaseNineGridAdapter<V extends View, E> implements NineGridAdapter<V, E> {

    private NineGridLayout<V, E> mNineGridLayout = null;

    private List<E> mDataList;

    public List<E> getDataList() {
        return mDataList;
    }

    public void setDataList(List<E> dataList) {
        this.mDataList = dataList;
        notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetChanged(){
        if (mNineGridLayout != null){
            mNineGridLayout.onLayoutPrepare();
        }
    }

    public void onAttachToLayout(NineGridLayout<V, E> layout){
        mNineGridLayout = layout;
    }
}