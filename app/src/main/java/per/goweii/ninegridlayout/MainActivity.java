package per.goweii.ninegridlayout;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Random;

import per.goweii.ninegridlayout.adapter.OnClickListener;
import per.goweii.ninegridlayout.utils.DemoDataGetter;

public class MainActivity extends AppCompatActivity {

    private NineAdapter mAdapter;

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvAdd = findViewById(R.id.tv_add);
        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                List<String> urls = DemoDataGetter.getImgUrlList(random.nextInt(8) + 1);
                if (urls.size() > 0) {
                    mAdapter.addData(new NineBean("这是第" + (count + 1) + "条数据", urls, random.nextBoolean()));
                    count++;
                }
            }
        });
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new NineAdapter(this);
        mAdapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                mAdapter.removeData(position);
            }
        }, R.id.tv_delete);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Glide.with(MainActivity.this).resumeRequests();
                } else {
                    Glide.with(MainActivity.this).pauseRequests();
                }
            }
        });
    }
}