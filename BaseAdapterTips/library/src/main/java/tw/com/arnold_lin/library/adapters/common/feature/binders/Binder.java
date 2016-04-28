package tw.com.arnold_lin.library.adapters.common.feature.binders;


import java.lang.reflect.ParameterizedType;

import tw.com.arnold_lin.library.adapters.common.feature.core.ViewBinderImp;
import tw.com.arnold_lin.library.adapters.common.feature.interfaces.ISequence;

/**
 * Created by arnold_lin on 2015/12/2.
 */
public class Binder<T extends ViewBinderImp> implements BinderImp<T>, ISequence{

    private final Class<T> cls;
    private int index = 0;

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

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }
}