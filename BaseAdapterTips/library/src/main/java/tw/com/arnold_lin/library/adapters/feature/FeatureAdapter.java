package tw.com.arnold_lin.library.adapters.feature;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import tw.com.arnold_lin.library.adapters.feature.binders.Binder;
import tw.com.arnold_lin.library.adapters.feature.core.FeatureBasicLayout;
import tw.com.arnold_lin.library.adapters.feature.core.FeatureTypeImp;
import tw.com.arnold_lin.library.adapters.feature.core.FeatureViewFactory;
import tw.com.arnold_lin.library.adapters.interfaces.IBinder;


/**
 * Created by arnold_lin on 2015/11/17.
 */
public class FeatureAdapter extends BaseAdapter implements IBinder<Binder> {
    private final String TAG = "FeatureAdapter";
    private final Context context;
    private final Class<? extends FeatureTypeImp>[] types;
    private final List<Binder> binders = new ArrayList<Binder>();

    public FeatureAdapter(Context context, Class<? extends FeatureTypeImp>... types) {
        this.context = context;
        this.types = types;
    }

    @Override
    public int getCount() {
        return binders.size();
    }

    @Override
    public Object getItem(int position) {
        return binders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
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
    public void setBinders(ArrayList<Binder> binders) {
        if (binders == null) {
            return;
        }
        Log.d(TAG,"binders size : "+binders.size());
        this.binders.clear();
        this.binders.addAll((ArrayList) binders.clone());
        notifyDataSetChanged();
    }



    @Override
    public void addBinders(ArrayList<Binder> binders) {
        if (binders == null) {
            return;
        }

        this.binders.addAll((ArrayList) binders.clone());
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "getView");
        ViewHolder holder;
        if (convertView == null) {
            FeatureBasicLayout layout = FeatureViewFactory.create(context, types);
            holder = new ViewHolder();
            holder.item = layout;
            convertView = layout;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Binder binder = binders.get(position);
        holder.item.setBinder(binder);
        Log.d(TAG, "setBinder binder type : " + binder.getClass().getSimpleName());
        return convertView;
    }

    public static class ViewHolder {
        FeatureBasicLayout item;
    }

}
