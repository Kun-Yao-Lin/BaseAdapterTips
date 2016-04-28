package tw.com.arnold_lin.tips.normal;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import tw.com.arnold_lin.library.adapters.common.feature.interfaces.IBinder;
import tw.com.arnold_lin.tips.R;


/**
 * Created by arnold_lin on 2015/12/18.
 */
public abstract class BasicAdapterActivity<T> extends Activity implements View.OnClickListener {

    protected abstract BaseAdapter getAdapter(Context context);
    protected abstract ArrayList<T> getInitData();
    protected abstract T getSingleData();

    private LinearLayout linearLayout ;
    private ListView listView ;
    private Button button;

    private BaseAdapter adapter;
    private IBinder imp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= 11){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        }
        linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(linearLayout);
        listView = new ListView(this);
        listView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,1));
        linearLayout.addView(listView);

        button = new Button(this);
        button.setText(R.string.add);
        button.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        button.setOnClickListener(this);

        linearLayout.addView(button);

        adapter = getAdapter(this);
        listView.setAdapter(adapter);

        //初始化
        if(adapter instanceof IBinder){
            imp =  ((IBinder) adapter);
            imp.setBinders(getInitData());
        }
    }

    @Override
    public void onClick(View v) {
        if(imp != null){
            imp.addBinders(getSingleData());
        }
        listView.smoothScrollToPosition(adapter.getCount() - 1);
    }
}
