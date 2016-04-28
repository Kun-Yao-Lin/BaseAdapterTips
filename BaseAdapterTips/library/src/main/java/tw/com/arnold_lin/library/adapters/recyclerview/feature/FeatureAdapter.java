package tw.com.arnold_lin.library.adapters.recyclerview.feature;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tw.com.arnold_lin.library.adapters.common.feature.binders.Binder;
import tw.com.arnold_lin.library.adapters.common.feature.core.FeatureBasicLayout;
import tw.com.arnold_lin.library.adapters.common.feature.core.FeatureTypeImp;
import tw.com.arnold_lin.library.adapters.common.feature.core.FeatureViewFactory;
import tw.com.arnold_lin.library.adapters.common.feature.interfaces.IBinder;


/**
 * Created by arnold_lin on 2015/11/17.
 */
public class FeatureAdapter extends RecyclerView.Adapter<FeatureAdapter.ViewHolder> implements IBinder<Binder> {
    private final String TAG = "FeatureAdapter";
    private final Context context;
    private final Class<? extends FeatureTypeImp>[] types;
    private final List<Binder> binders = new ArrayList<Binder>();

    public FeatureAdapter(Context context, Class<? extends FeatureTypeImp>... types) {
        this.context = context;
        this.types = types;
    }

    @Override
    public int getItemCount() {
        return binders.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(FeatureViewFactory.create(context, types));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Binder binder = binders.get(position);
        binder.setIndex(position);
        holder.item.setBinder(binder);
        Log.d(TAG, "setBinder binder type : " + binder.getClass().getSimpleName());
    }

    @Override
    public void setBinder(int pos, Binder binders) {
        if (binders == null || this.binders.size() <= pos) {
            return;
        }
        this.binders.set(pos, binders);
        notifyDataSetChanged();
    }

    @Override
    public void addBinders(Binder binder) {

    }

    @Override
    public void setBinders(ArrayList<? extends Binder> binders) {
        if (binders == null) {
            return;
        }
        Log.d(TAG,"binders size : "+binders.size());
        this.binders.clear();
        this.binders.addAll((ArrayList) binders.clone());
        notifyDataSetChanged();
    }

    @Override
    public void addBinders(ArrayList<? extends Binder> binders) {
        if (binders == null) {
            return;
        }

        this.binders.addAll((ArrayList) binders.clone());
        notifyDataSetChanged();
    }

    @Override
    public void deleteBinder(int pos) {
        if(pos >= getItemCount()){
            return;
        }
        this.binders.remove(pos);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        FeatureBasicLayout item;
        public ViewHolder(View itemView) {
            super(itemView);
            item = (FeatureBasicLayout) itemView;
        }
    }

}
