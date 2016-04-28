package tw.com.arnold_lin.tips.recycleview.wrappers;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Random;

import tw.com.arnold_lin.library.adapters.recyclerview.wrappers.limitless.LimitLessListAdapter;
import tw.com.arnold_lin.tips.recycleview.basic.ColorAdapter;

public class LimitLessAdapterActivity extends Activity {

    private Random rnd = new Random();
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(linearLayout);
        add(LinearLayoutManager.VERTICAL, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1));
        add(LinearLayoutManager.HORIZONTAL, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
    }

    private void add(int orientation,ViewGroup.LayoutParams params){
        RecyclerView listView = new RecyclerView(this);
        listView.setLayoutManager(new LinearLayoutManager(this, orientation, false));
        listView.setLayoutParams(params);
        linearLayout.addView(listView);

        final ColorAdapter adapter = new ColorAdapter(this);
        LimitLessListAdapter limitLessListAdapter = new LimitLessListAdapter(this,adapter);
        limitLessListAdapter.setLoadMoreListener(new LimitLessListAdapter.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                adapter.addBinders(getColors());
            }
        });

        listView.setAdapter(limitLessListAdapter);
        adapter.setBinders(getColors());
    }

    private ArrayList<Integer> getColors() {
        ArrayList<Integer> colors = new ArrayList<>();
        for(int i = 0 ; i < 10; i++) {
            colors.add(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
        }
        return colors;
    }

}
