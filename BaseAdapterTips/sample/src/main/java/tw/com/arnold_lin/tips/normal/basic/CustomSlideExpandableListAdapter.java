package tw.com.arnold_lin.tips.normal.basic;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.tjerkw.slideexpandable.library.SlideExpandableListAdapter;

/**
 * Created by arnold_lin on 2016/4/13.
 */
public class CustomSlideExpandableListAdapter extends SlideExpandableListAdapter {
    public CustomSlideExpandableListAdapter(ListAdapter wrapped) {
        super(wrapped);
    }

    public CustomSlideExpandableListAdapter(ListAdapter wrapped, int toggle_button_id, int expandable_view_id) {
        super(wrapped, toggle_button_id, expandable_view_id);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v = super.getView(position, view, viewGroup);



        return v;
    }
}
