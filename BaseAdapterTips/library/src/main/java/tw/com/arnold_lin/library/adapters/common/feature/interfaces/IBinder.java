package tw.com.arnold_lin.library.adapters.common.feature.interfaces;

import java.util.ArrayList;

/**
 * Created by arnold_lin on 2016/4/7.
 */
public interface IBinder<T> {

    void addBinders(ArrayList<? extends T> binders);

    void addBinders(T binder);

    void setBinders(ArrayList<? extends T> binders);

    void setBinder(int pos, T binders);

    void deleteBinder(int pos);
}
