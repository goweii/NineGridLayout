package per.goweii.ninegridlayout.utils;

import android.view.View;

/**
 * @author Cuizhen
 * @date 2018/5/7-下午4:40
 */
public abstract class OnClickListenerWithoutLogin implements View.OnClickListener {

    @Override
    public final void onClick(final View v) {
        ClickHelper.onlyFirstSameView(v, new ClickHelper.Callback() {
            @Override
            public void onClick(View view) {
                onClickWithoutLogin(view);
            }
        });
    }

    public abstract void onClickWithoutLogin(View v);
}
