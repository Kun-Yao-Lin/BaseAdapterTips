package tw.com.arnold_lin.tips.normal.basic;

import android.content.Context;
import android.graphics.Color;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Random;

import tw.com.arnold_lin.tips.normal.BasicAdapterActivity;

public class NormalAdapterActivity extends BasicAdapterActivity<Integer> {

    private Random rnd = new Random();

    @Override
    protected BaseAdapter getAdapter(Context context) {
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
