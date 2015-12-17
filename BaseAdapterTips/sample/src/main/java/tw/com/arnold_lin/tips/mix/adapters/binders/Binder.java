package tw.com.arnold_lin.tips.mix.adapters.binders;

import tw.com.arnold_lin.tips.mix.adapters.core.ViewBinderImp;

/**
 * Created by arnold_lin on 2015/12/2.
 */
public interface Binder {
    public Class<?extends ViewBinderImp> getViewBinderCls();
}
