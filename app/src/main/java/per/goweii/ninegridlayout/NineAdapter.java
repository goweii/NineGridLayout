package per.goweii.ninegridlayout;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import per.goweii.ninegridlayout.adapter.BaseHolder;
import per.goweii.ninegridlayout.adapter.BaseStateAdapter;
import per.goweii.ninegridlayout.ninegridlayout.adapter.BaseNineGridAdapter;
import per.goweii.ninegridlayout.ninegridlayout.NineGridLayout;
import per.goweii.ninegridlayout.utils.ImageLoader;

/**
 * 描述：
 *
 * @author Cuizhen
 * @date 2018/9/12
 */
public class NineAdapter extends BaseStateAdapter<NineBean, NineAdapter.NineHolder> {

    private final Context context;

    public NineAdapter(Context context) {
        this.context = context;
    }

    @Override
    protected NineHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new NineHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.test_rv_item_nine_photo_view, parent, false));
    }

    class NineHolder extends BaseHolder<NineBean> {

        private final TextView mTvTitle;
        private final NineGridLayout<View, NineBean.ImageBean> mIvImage;

        private BaseNineGridAdapter<View, NineBean.ImageBean> mNineGridAdapter;

        NineHolder(View itemView) {
            super(itemView);
            mTvTitle = getView(R.id.tv_title);
            mIvImage = getView(R.id.iv_image);
        }

        @Override
        protected void bindData(NineBean data) {
            mTvTitle.setText(data.getTitle());
            mNineGridAdapter = new BaseNineGridAdapter<View, NineBean.ImageBean>() {
                @Override
                public View onCreateChildView(Context context, NineGridLayout<View, NineBean.ImageBean> parent, int position) {
                    return LayoutInflater.from(context).inflate(R.layout.ngl_item_image, parent, false);
                }

                @Override
                public void onBindData(Context context, View childView, NineBean.ImageBean imageBean, int position) {
                    ImageView iv_image = childView.findViewById(R.id.iv_image);
                    TextView tv_title = childView.findViewById(R.id.tv_title);
                    NineBean data = getData(getAdapterPosition());
                    if (data.isBlur()) {
                        tv_title.setVisibility(View.VISIBLE);
                        if (imageBean.isBurn()) {
                            ImageLoader.imageBlur(context, iv_image, imageBean.getUrl(), 0.5F);
                            tv_title.setText("已销毁");
                        } else {
                            ImageLoader.imageBlur(context, iv_image, imageBean.getUrl(), 0.1F);
                            tv_title.setText("阅后即焚");
                        }
                    } else {
                        ImageLoader.image(context, iv_image, imageBean.getUrl());
                        tv_title.setVisibility(View.GONE);
                        tv_title.setText("");
                    }
                    childView.setOnTouchListener(new View.OnTouchListener() {
                        @SuppressLint("ClickableViewAccessibility")
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            switch (event.getAction()) {
                                default:
                                    break;
                                case MotionEvent.ACTION_DOWN:
                                    ObjectAnimator animSxBig = ObjectAnimator.ofFloat(iv_image, "scaleX", iv_image.getScaleX(), 1.2F);
                                    ObjectAnimator animSyBig = ObjectAnimator.ofFloat(iv_image, "scaleY", iv_image.getScaleY(), 1.2F);
                                    AnimatorSet setBig = new AnimatorSet();
                                    setBig.playTogether(animSxBig, animSyBig);
                                    setBig.setDuration(300);
                                    setBig.start();
                                    break;
                                case MotionEvent.ACTION_UP:
                                case MotionEvent.ACTION_CANCEL:
                                    ObjectAnimator animSxSmall = ObjectAnimator.ofFloat(iv_image, "scaleX", iv_image.getScaleX(), 1.0F);
                                    ObjectAnimator animSySmall = ObjectAnimator.ofFloat(iv_image, "scaleY", iv_image.getScaleY(), 1.0F);
                                    AnimatorSet setSmall = new AnimatorSet();
                                    setSmall.playTogether(animSxSmall, animSySmall);
                                    setSmall.setDuration(300);
                                    setSmall.start();
                                    data.getImages().get(position).setBurn();
                                    notifyDataSetChanged();
                                    break;
                            }
                            return true;
                        }
                    });
                }
            };
            mIvImage.setAdapter(mNineGridAdapter);
            mNineGridAdapter.setDataList(data.getImages());
        }
    }
}