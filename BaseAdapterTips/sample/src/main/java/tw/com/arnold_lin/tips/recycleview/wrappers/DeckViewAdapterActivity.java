package tw.com.arnold_lin.tips.recycleview.wrappers;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import tw.com.arnold_lin.library.adapters.OnItemClickListener;
import tw.com.arnold_lin.library.adapters.recyclerview.wrappers.deck.DeckLayoutManger;
import tw.com.arnold_lin.tips.recycleview.basic.ColorAdapter;

public class DeckViewAdapterActivity extends Activity {

    private Random rnd = new Random();
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(linearLayout);
        add(LinearLayoutManager.VERTICAL, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    private void add(int orientation,ViewGroup.LayoutParams params){
        RecyclerView listView = new RecyclerView(this);
//        RecyclerView.LayoutManager layoutManager = new CustomLayoutManager(orientation, 2);
        RecyclerView.LayoutManager layoutManager = new DeckLayoutManger();
        listView.setLayoutManager(layoutManager);
        listView.setLayoutParams(params);
        linearLayout.addView(listView);

        final ColorAdapter adapter = new ColorAdapter(this);
        adapter.setOnItemClickListener(new OnItemClickListener<Integer>() {
            @Override
            public void onItemClick(View v, int position, Integer binder) {
                Toast.makeText(DeckViewAdapterActivity.this, "hello"+position, Toast.LENGTH_SHORT).show();
            }
        });
        listView.setAdapter(adapter);
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
