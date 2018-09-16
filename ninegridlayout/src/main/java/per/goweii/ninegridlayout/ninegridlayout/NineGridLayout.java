package per.goweii.ninegridlayout.ninegridlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import per.goweii.ninegridlayout.ninegridlayout.adapter.BaseNineGridAdapter;
import per.goweii.ninegridlayout.ninegridlayout.base.LayoutManager;
import per.goweii.ninegridlayout.ninegridlayout.manager.LinearLayoutManager;

/**
 * 描述：
 *
 * @author Cuizhen
 * @date 2018/9/11
 */
public class NineGridLayout<V extends View, E> extends ViewGroup {

    public static final int MODE_LINEAR = 0;
    public static final int MODE_GRID = 1;

    private int mode;
    private int maxChildCount;
    private int gridSpace;
    private float singleChildPercent;
    private float singleChildRatio;
    private float fourChildPercent;

    private int columnCount;
    private int rowCount;
    private int gridWidth;
    private int gridHeight;

    private final List<V> mChildViews;
    private List<E> mDataList;
    private BaseNineGridAdapter<V, E> mAdapter = null;
    private LayoutManager mLayoutManager = null;

    public NineGridLayout(Context context) {
        this(context, null);
    }

    public NineGridLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NineGridLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mChildViews = new ArrayList<>();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NineGridLayout);
        gridSpace = (int) typedArray.getDimension(R.styleable.NineGridLayout_ngl_space, 0);
        maxChildCount = typedArray.getInteger(R.styleable.NineGridLayout_ngl_max_child_count, 9);
        singleChildPercent = typedArray.getFloat(R.styleable.NineGridLayout_ngl_single_width_percent, 0.5F);
        singleChildRatio = typedArray.getFloat(R.styleable.NineGridLayout_ngl_single_height_ration, 1.0F);
        mode = typedArray.getInt(R.styleable.NineGridLayout_ngl_mode, MODE_LINEAR);
        fourChildPercent = typedArray.getFloat(R.styleable.NineGridLayout_ngl_four_width_percent, 1.0F / 3.0F);
        typedArray.recycle();
    }

    private boolean isDataEmpty(){
        return mDataList == null || mDataList.size() == 0;
    }

    public void setLayoutManager(LayoutManager layoutManager) {
        mLayoutManager = layoutManager;
    }

    public LayoutManager getLayoutManager() {
        if (mLayoutManager == null){
            mLayoutManager = new LinearLayoutManager();
        }
        return mLayoutManager;
    }

    public void setAdapter(@NonNull BaseNineGridAdapter<V, E> adapter) {
        mAdapter = adapter;
        mAdapter.onAttachToLayout(this);
    }

    public void onLayoutPrepare(){
        List<E> dataList = mAdapter.getDataList();
        if (dataList == null || dataList.isEmpty()) {
            return;
        }
        int size = dataList.size();
        if (maxChildCount > 0 && size > maxChildCount) {
            dataList = dataList.subList(0, maxChildCount);
            size = dataList.size();
        }
        //保证View的复用，避免重复创建
        if (mDataList == null) {
            for (int i = 0; i < size; i++) {
                View childView = getChildView(i);
                if (childView == null) {
                    return;
                }
                addView(childView);
            }
        } else {
            int oldViewCount = mDataList.size();
            if (oldViewCount > size) {
                removeViews(size, oldViewCount - size);
            } else if (oldViewCount < size) {
                for (int i = oldViewCount; i < size; i++) {
                    View childView = getChildView(i);
                    if (childView == null) {
                        return;
                    }
                    addView(childView);
                }
            }
        }
        mDataList = dataList;
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = 0;
        if (!isDataEmpty()) {
            int count = mDataList.size();
            int usableWidth = width - getPaddingLeft() - getPaddingRight();

            if (count == 1) {
                gridWidth = (int) (usableWidth * singleChildPercent);
                gridHeight = (int) (gridWidth / singleChildRatio);
            } else {
                gridWidth = gridHeight = (usableWidth - gridSpace * 2) / 3;
            }

            //默认是3列显示，行数根据图片的数量决定
            columnCount = count < 3 ? count % 3 : 3;
            rowCount = count / 3 + (count % 3 == 0 ? 0 : 1);
            //grid模式下，显示4张使用2X2模式
            if (mode == MODE_GRID) {
                if (count == 4) {
                    rowCount = 2;
                    columnCount = 2;
                }
            }
            width = gridWidth * columnCount + gridSpace * (columnCount - 1) + getPaddingLeft() + getPaddingRight();
            height = gridHeight * rowCount + gridSpace * (rowCount - 1) + getPaddingTop() + getPaddingBottom();

            int[] size = getLayoutManager().getParentSize(mDataList.size(), gridSpace);

        }
        setMeasuredDimension(width, height);

        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            int wSpec = MeasureSpec.makeMeasureSpec(gridWidth, MeasureSpec.EXACTLY);
            int hSpec = MeasureSpec.makeMeasureSpec(gridHeight, MeasureSpec.EXACTLY);
            getChildAt(i).measure(wSpec, hSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (isDataEmpty()) {
            return;
        }
        final int childCount = mDataList.size();
        for (int i = 0; i < childCount; i++) {
            V childView = (V) getChildAt(i);

            Place childPlace = getLayoutManager().getChildPosition(i, columnCount, gridWidth, gridHeight, gridSpace);

            int rowNum = i / columnCount;
            int columnNum = i % columnCount;
            int left = (gridWidth + gridSpace) * columnNum + getPaddingLeft();
            int top = (gridHeight + gridSpace) * rowNum + getPaddingTop();
            int right = left + gridWidth;
            int bottom = top + gridHeight;
            childView.layout(left, top, right, bottom);

            if (mAdapter != null) {
                mAdapter.onBindData(getContext(), childView, mDataList.get(i), i);
            }
        }
    }

    private void layoutViewAt(View view, int l, int t, int r, int b){
        int parentPaddingLeft = getPaddingLeft();
        int parentPaddingTop = getPaddingTop();
        int padding = gridSpace / 2;
        int left = l + padding;
        int top = t + padding;
        int right = r + padding;
        int bottom = b + padding;
        view.layout(left, top, right, bottom);
    }

    private V getChildView(final int position) {
        V childView;
        if (position < mChildViews.size()) {
            childView = mChildViews.get(position);
        } else {
            childView = mAdapter.onCreateChildView(getContext(), this, position);
            mChildViews.add(childView);
        }
        return childView;
    }
}