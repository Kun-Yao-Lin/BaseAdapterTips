package tw.com.arnold_lin.tips.mix.adapters;

import java.util.ArrayList;

/**
 * Created by arnold_lin on 2015/12/18.
 */
public interface BinderImp<T> {

    public void addBinders(ArrayList<T> binders);

    public void addBinders(T binder);

    public void setBinders(ArrayList<T> binders);
}
