package tw.com.arnold_lin.tips.normal.wrappers;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

import tw.com.arnold_lin.tips.normal.basic.ColorAdapter;
import tw.com.arnold_lin.library.adapters.normal.wrappers.limitless.LimitLessListAdapter;

public class LimitLessAdapterActivity extends Activity {

    private Random rnd = new Random();

    private ListView listView;

    private ColorAdapter adapter;
    private LimitLessListAdapter limitLessListAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listView = new ListView(this);
        listView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,1));
        setContentView(listView);

        adapter = new ColorAdapter(this);
        limitLessListAdapter = new LimitLessListAdapter(this,adapter);
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
