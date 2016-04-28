package tw.com.arnold_lin.tips.recycleview.wrappers;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.tjerkw.slideexpandable.library.SlideExpandableListAdapter;

import java.util.ArrayList;
import java.util.Random;

import tw.com.arnold_lin.library.adapters.normal.wrappers.circular.CircularAdapter;
import tw.com.arnold_lin.tips.R;
import tw.com.arnold_lin.tips.recycleview.basic.ColorBarAdapter;

public class CircularAdapterActivity extends Activity {

    private Random rnd = new Random();

    private ListView listView;

    private ColorBarAdapter adapter;
    private CircularAdapter recipeAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources res = getResources();
        int dividerHeight = (int)res.getDimension(R.dimen.height_unit_half);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.BOTTOM);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(linearLayout);

        listView = new ListView(this);
        listView.setDividerHeight(- dividerHeight);
        listView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        linearLayout.addView(listView);

        adapter = new ColorBarAdapter(this);
//        recipeAdapter = new CircularListAdapter(adapter);

        SlideExpandableListAdapter slideExpandableListAdapter = new SlideExpandableListAdapter(
                adapter,
                R.id.expandable_toggle_button,
                R.id.expandable
        );

        listView.setAdapter(slideExpandableListAdapter);

        adapter.setBinders(getColors());
    }


    private ArrayList<Integer> getColors() {
        ArrayList<Integer> colors = new ArrayList<>();
        for(int i = 0 ; i < 20; i++) {
            colors.add(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
        }
        return colors;
    }

}
