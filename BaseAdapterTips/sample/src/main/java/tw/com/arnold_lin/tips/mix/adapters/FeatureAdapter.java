package tw.com.arnold_lin.tips.mix.adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import tw.com.arnold_lin.tips.mix.adapters.binders.Binder;
import tw.com.arnold_lin.tips.mix.adapters.core.FeatureBasicLayout;
import tw.com.arnold_lin.tips.mix.adapters.core.FeatureTypeImpl;
import tw.com.arnold_lin.tips.mix.adapters.core.FeatureViewFactory;

/**
 * Created by arnold_lin on 2015/11/17.
 */
public class FeatureAdapter extends BaseAdapter implements BinderImp<Binder> {
    private final String TAG = "FeatureAdapter";
    private final Context context;
    private final Class<? extends FeatureTypeImpl>[] types;
    private final List<Binder> binders = new ArrayList<>();

    public FeatureAdapter(Context context, Class<? extends FeatureTypeImpl>... types) {
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
    public void addBinders(Binder binder) {
        this.binders.add(binder);
        notifyDataSetChanged();
    }

    @Override
    public void addBinders(ArrayList<Binder> binders) {
        if(binders == null){
            return;
        }

        this.binders.addAll((ArrayList)binders.clone());
        notifyDataSetChanged();
    }

    @Override
    public void setBinders(ArrayList<Binder> binders) {
        if(binders == null){
            return;
        }

        this.binders.clear();
        this.binders.addAll((ArrayList)binders.clone());
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder ;
        if(convertView == null){
            FeatureBasicLayout layout = FeatureViewFactory.create(context,types);
            holder = new ViewHolder();
            holder.item = layout;
            convertView = layout;
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        Binder binder = binders.get(position);
        holder.item.setBinder(binder);
        Log.d(TAG,"setBinder binder type : "+binder.getClass().getSimpleName());
        return convertView;
    }

    public static class ViewHolder {
        FeatureBasicLayout item;
    }

}
