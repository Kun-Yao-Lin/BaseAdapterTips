package tw.com.arnold_lin.library.adapters.feature.binders;


import java.lang.reflect.ParameterizedType;

import tw.com.arnold_lin.library.adapters.feature.core.ViewBinderImp;

/**
 * Created by arnold_lin on 2015/12/2.
 */
public class Binder<T extends ViewBinderImp> implements BinderImp<T> {

    private final Class<T> cls;

    @SuppressWarnings("unchecked")
    public Binder() {
        cls = (Class<T>)
                ((ParameterizedType) getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[0];
    }

    @Override
    public Class<T> getViewBinderCls() {
        return cls;
    }

}
