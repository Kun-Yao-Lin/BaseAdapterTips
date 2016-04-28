package tw.com.arnold_lin.tips.recycleview.basic;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

import tw.com.arnold_lin.tips.recycleview.BasicAdapterActivity;

public class NormalAdapterActivity extends BasicAdapterActivity<Integer> {

    private Random rnd = new Random();

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return null;
    }

    @Override
    protected RecyclerView.Adapter getAdapter(Context context) {
        return new ColorAdapter(this);
    }

    @Override
    protected ArrayList<Integer> getInitData() {
        ArrayList<Integer> colors = new ArrayList<>();
        for(int i = 0 ; i < 5; i++) {
            colors.add(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
        }
        return colors;
    }

    @Override
    protected Integer getSingleData() {
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}
