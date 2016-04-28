package tw.com.arnold_lin.tips.normal.basic;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import tw.com.arnold_lin.library.adapters.common.feature.interfaces.IBinder;


/**
 * Created by arnold_lin on 2015/11/20.
 */
public class ColorBarAdapter extends BaseAdapter implements IBinder<Integer> {
    private final Context context;
    private final List<Integer> colors = new ArrayList<>();
    private int firstPosition = -1;
    private int lastPosition = -1;

    private class ViewHolder{
        CardMsgView view;
    }

    public ColorBarAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return colors.size();
    }

    @Override
    public Object getItem(int i) {
        return colors.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            CardMsgView cardMsgView = new CardMsgView(context);
            view = cardMsgView;
            viewHolder.view = cardMsgView;
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)view.getTag();
        }
        int color = colors.get(i);
        viewHolder.view.setBackground(color);
        viewHolder.view.setNumber(i);
//        Animation animation;
//        int lastIndex = getCount() - 1;
//        if(i == lastIndex && lastPosition == 0){
////            animation = AnimationUtils.loadAnimation(context, R.anim.down_from_top);
//            animation = CardMsgView.createDownFromTop(context);
//        }else if(i == 0 && lastPosition == lastIndex){
////            animation = AnimationUtils.loadAnimation(context, R.anim.up_from_bottom);
//            animation = CardMsgView.createUpFromBottom(context);
//        }else {
//            animation = i > lastPosition ?  CardMsgView.createUpFromBottom(context) : CardMsgView.createDownFromTop(context);
//        }
//        viewHolder.view.startAnimation(animation);
//        lastPosition = i;

        return view;
    }

    @Override
    public void addBinders(Integer color){
        if(color == null){
            return;
        }
        this.colors.add(color);
        notifyDataSetChanged();
    }

    @Override
    public void setBinder(int pos, Integer binders) {
        if(binders == null){
            return;
        }
        this.colors.set(pos, binders);
        notifyDataSetChanged();
    }

    @Override
    public void addBinders(ArrayList<? extends Integer> binders) {
        if(binders == null){
            return;
        }
        this.colors.addAll((ArrayList<Integer>) binders.clone());
        notifyDataSetChanged();
    }

    @Override
    public void setBinders(ArrayList<? extends Integer> binders) {
        if(binders == null){
            return;
        }
        this.colors.clear();
        this.colors.addAll((ArrayList<? extends Integer>) binders.clone());
        notifyDataSetChanged();
    }

    @Override
    public void deleteBinder(int pos) {
        if(this.colors.size() >= pos){
            return;
        }
        this.colors.remove(pos);
        notifyDataSetChanged();
    }
}