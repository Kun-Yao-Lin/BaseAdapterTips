package tw.com.arnold_lin.library.adapters.feature.binders;

import tw.com.arnold_lin.library.adapters.feature.core.ViewBinderImp;

/**
 * Created by arnold_lin on 2016/1/21.
 */
public interface BinderImp<T extends ViewBinderImp> {
    public Class<T> getViewBinderCls();
}

