package tw.com.arnold_lin.tips.recycleview.basic;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tw.com.arnold_lin.library.adapters.OnItemClickListener;
import tw.com.arnold_lin.library.adapters.common.feature.interfaces.IBinder;


/**
 * Created by arnold_lin on 2015/11/20.
 */
public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ViewHolder> implements IBinder<Integer> {
    private OnItemClickListener<Integer> onItemClickListener = null;
    private final Context context;
    private final List<Integer> colors = new ArrayList<>();

    public class ViewHolder extends RecyclerView.ViewHolder{
        ColorView view;
        public ViewHolder(View itemView) {
            super(itemView);
            view = (ColorView) itemView;
        }
    }

    public ColorAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return colors.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {
        final int color = colors.get(position);
        final ColorView view = holder.view;
        view.setBackgroundColor(color);
        view.setNumber(position);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(view, position, color);
                }
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(new ColorView(context));
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

    public void setOnItemClickListener(OnItemClickListener<Integer> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}