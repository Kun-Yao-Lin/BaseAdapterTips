package tw.com.arnold_lin.library.adapters;

import android.view.View;

/**
 * Created by arnold_lin on 2016/4/22.
 */
public interface OnItemClickListener<T> {
    void onItemClick(View v, int position, T binder);
}
