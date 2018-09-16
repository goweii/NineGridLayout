package per.goweii.ninegridlayout.adapter;

import android.support.annotation.IdRes;
import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import per.goweii.ninegridlayout.R;

/**
 * @author Cuizhen
 * @date 2018/5/26-上午10:41
 */
public abstract class BaseStateAdapter<E, H extends BaseHolder<E>> extends RecyclerView.Adapter<BaseHolder<E>> {

    private static final String TAG = BaseStateAdapter.class.getSimpleName();
    private List<E> dataList = null;
    private boolean isNoMoreData = false;

    private OnClickListener onItemClickListener = null;
    private SparseArray<OnClickListener> onViewClickListeners = null;
    private RecyclerView recyclerView = null;

    public boolean isNoMoreData() {
        return isNoMoreData;
    }

    public void setNoMoreData(boolean isNoMoreData) {
        if (this.isNoMoreData == isNoMoreData) {
            return;
        }
        this.isNoMoreData = isNoMoreData;
        int lastIndex = getItemCount() - 1;
        if (dataList != null && !dataList.isEmpty() && lastIndex >= 1) {
            if (isNoMoreData) {
                notifyItemInserted(lastIndex);
            } else {
                notifyItemRemoved(lastIndex);
            }
        }
        requestLayout();
    }

    /**
     * 获取数据集合
     *
     * @return List<E>
     */
    public final List<E> getData() {
        return dataList;
    }

    /**
     * 设置新数据
     *
     * @param data List<E>
     */
    public final void setData(List<E> data) {
        this.dataList = data;
        if (this.dataList == null) {
            this.dataList = new ArrayList<>();
        }
        notifyDataSetChanged();
        requestLayout();
    }

    /**
     * 获取对应位置的数据
     *
     * @param position 位置
     * @return E
     */
    public final E getData(int position) {
        if (dataList == null) {
            return null;
        }
        if (position < 0) {
            return null;
        }
        if (position >= dataList.size()) {
            return null;
        }
        return dataList.get(position);
    }

    /**
     * 添加一个数据集合
     *
     * @param data List<E>
     */
    public final void addData(List<E> data) {
        if (dataList == null || dataList.size() == 0) {
            setData(data);
        } else {
            if (data != null && data.size() > 0) {
                int index = dataList.size();
                dataList.addAll(data);
                notifyItemRangeInserted(index, data.size());
                requestLayout();
            }
        }
    }

    /**
     * 添加一个数据
     *
     * @param index int
     * @param data  E
     */
    public final void addData(int index, E data) {
        if (dataList == null || dataList.size() == 0) {
            if (dataList == null) {
                dataList = new ArrayList<>();
            }
            if (data != null) {
                dataList.add(data);
            }
            notifyDataSetChanged();
        } else {
            if (data != null) {
                if (index < 0) {
                    index = 0;
                }
                int size = dataList.size();
                if (index > size) {
                    index = size;
                }
                dataList.add(index, data);
                notifyItemInserted(index);
            }
        }
        requestLayout();
    }

    /**
     * 添加一个数据
     *
     * @param data E
     */
    public final void addData(E data) {
        int index = 0;
        if (dataList != null) {
            index = dataList.size();
        }
        addData(index, data);
    }

    /**
     * 移除对应位置的数据
     *
     * @param position 位置
     */
    public final void removeData(int position) {
        if (position >= 0 && position < dataList.size()) {
            dataList.remove(position);
            notifyItemRemoved(position);
            requestLayout();
        }
    }

    /**
     * 清空数据并显示为空布局
     */
    public final void clearData() {
        if (dataList == null) {
            dataList = new ArrayList<>();
        } else {
            dataList.clear();
        }
        notifyDataSetChanged();
        requestLayout();
    }

    /**
     * 置空数据并显示为加载布局
     */
    public final void loadData() {
        this.dataList = null;
        notifyDataSetChanged();
        requestLayout();
    }

    private void requestLayout() {
        if (recyclerView != null && recyclerView.hasFixedSize()) {
            recyclerView.requestLayout();
        }
    }

    /**
     * 对itemView添加点击事件
     * 推荐使用{@link #addOnClickListener(OnClickListener, int...)}
     *
     * @param onItemClickListener OnClickListener
     */
    @Deprecated
    public final void setOnItemClickListener(@NonNull OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 给一个view添加点击事件
     * 推荐使用{@link #addOnClickListener(OnClickListener, int...)}
     *
     * @param viewId              添加点击事件的view的id
     * @param onViewClickListener OnClickListener
     */
    @Deprecated
    public final void addOnViewClickListener(@IdRes int viewId, @NonNull OnClickListener onViewClickListener) {
        if (onViewClickListeners == null) {
            onViewClickListeners = new SparseArray<>();
        }
        onViewClickListeners.put(viewId, onViewClickListener);
    }

    /**
     * 对itemView或者多个子view添加点击事件
     * viewIds为空或者其中的viewId为一个小于等于0的值则是对itemView添加点击事件
     * 在回调中可以这样判断使用：
     * <p>
     * ----switch (view.getId()) {
     * --------default: // itemView的点击事件
     * ------------break;
     * --------case R.id.a:
     * ------------break;
     * --------case R.id.b:
     * ------------break;
     * ----}
     *
     * @param onClickListener OnClickListener
     * @param viewIds         如不添加这个参数则对itemView添加点击事件，效果同{@link #setOnItemClickListener(OnClickListener)}
     */
    public final void addOnClickListener(@NonNull OnClickListener onClickListener, @IdRes int... viewIds) {
        if (viewIds == null || viewIds.length == 0) {
            onItemClickListener = onClickListener;
            return;
        }
        if (onViewClickListeners == null) {
            onViewClickListeners = new SparseArray<>();
        }
        for (int viewId : viewIds) {
            if (viewId > 0) {
                onViewClickListeners.put(viewId, onClickListener);
            } else {
                onItemClickListener = onClickListener;
            }
        }
    }

    /**
     * 你可以重写这个方法实现不同位置显示不同的布局
     *
     * @param position 位置
     * @return 返回一个类型常量标识，当然你可以去继承{@link Type}接口定义这些常量以方便管理
     */
    @IntRange(from = 0)
    protected int getHolderType(int position) {
        return Type.NORMAL;
    }

    /**
     * 依据holder类型的常量去创建一个ViewHolder
     *
     * @param parent     ViewGroup
     * @param holderType 类型常量标识，你需要重写这个方法返回{@link #getHolderType(int)}
     * @return H 你应该去继承{@link BaseHolder<E>}定义你的ViewHolder
     */
    protected abstract H getViewHolder(@NonNull ViewGroup parent, int holderType);

    protected BaseHolder<E> getLoadingViewHolder(@NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        int loadLayoutId = getLoadLayoutId();
        if (loadLayoutId <= 0) {
            loadLayoutId = R.layout.rv_state_stub;
        }
        return new DefaultHolder<>(inflater.inflate(loadLayoutId, parent, false));
    }

    protected BaseHolder<E> getEmptyViewHolder(@NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        int emptyLayoutId = getEmptyLayoutId();
        if (emptyLayoutId <= 0) {
            emptyLayoutId = R.layout.rv_state_stub;
        }
        return new DefaultHolder<>(inflater.inflate(emptyLayoutId, parent, false));
    }

    protected BaseHolder<E> getNoMoreViewHolder(@NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        int noMoreLayoutId = getNoMoreLayoutId();
        if (noMoreLayoutId <= 0) {
            noMoreLayoutId = R.layout.rv_state_stub;
        }
        return new DefaultHolder<>(inflater.inflate(noMoreLayoutId, parent, false));
    }

    /**
     * 定义加载中的布局文件
     *
     * @return 布局文件id
     */
    @LayoutRes
    protected int getLoadLayoutId() {
        return R.layout.rv_state_loading_def;
    }

    /**
     * 定义空布局文件
     *
     * @return 空布局id
     */
    @LayoutRes
    protected int getEmptyLayoutId() {
        return R.layout.rv_state_empty_def;
    }

    /**
     * 定义没有更多布局文件
     *
     * @return 没有更多布局id
     */
    @LayoutRes
    protected int getNoMoreLayoutId() {
        return R.layout.rv_state_no_more_def;
    }

    @NonNull
    @Override
    public final BaseHolder<E> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case State.LOADING:
                return getLoadingViewHolder(parent);
            case State.EMPTY:
                return getEmptyViewHolder(parent);
            case State.NO_MORE:
                return getNoMoreViewHolder(parent);
            default:
                H holder = getViewHolder(parent, viewType);
                holder.setOnItemClickListener(onItemClickListener);
                holder.setOnViewClickListener(onViewClickListeners);
                return holder;
        }
    }

    @Override
    public final void onBindViewHolder(@NonNull BaseHolder<E> holder, int position) {
        switch (holder.getItemViewType()) {
            case State.LOADING:
                holder.bindData(null);
                break;
            case State.EMPTY:
                holder.bindData(null);
                break;
            case State.NO_MORE:
                holder.bindData(null);
                break;
            default:
                try {
                    holder.bindData(dataList.get(holder.getAdapterPosition()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public final int getItemViewType(int position) {
        if (dataList == null) {
            return State.LOADING;
        } else if (dataList.size() == 0) {
            return State.EMPTY;
        } else {
            if (position >= dataList.size()) {
                return State.NO_MORE;
            } else {
                return getHolderType(position);
            }
        }
    }

    @Override
    public final int getItemCount() {
        if (dataList == null) {
            return 1;
        } else if (dataList.size() == 0) {
            return 1;
        } else {
            if (isNoMoreData) {
                return dataList.size() + 1;
            } else {
                return dataList.size();
            }
        }
    }

    /**
     * 用于在网格布局时重设加载中、空布局和无更多布局的宽度
     * 不支持流布局的设置
     */
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager != null) {
            if (layoutManager instanceof GridLayoutManager) {
                final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                final int spanCount = gridLayoutManager.getSpanCount();
                gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        switch (getItemViewType(position)) {
                            default:
                                return 1;
                            case State.LOADING:
                            case State.EMPTY:
                            case State.NO_MORE:
                                return spanCount;
                        }
                    }
                });
            }
        }
    }
}
