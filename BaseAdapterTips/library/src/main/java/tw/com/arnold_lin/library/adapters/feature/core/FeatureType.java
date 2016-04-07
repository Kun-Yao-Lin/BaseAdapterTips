package tw.com.arnold_lin.library.adapters.feature.core;

import android.view.View;

import java.lang.reflect.ParameterizedType;

/**
 * Created by arnold_lin on 2015/12/10.
 */
public class FeatureType<T extends View> implements FeatureTypeImp<T> {

    private final Class<T> cls;

    @SuppressWarnings("unchecked")
    public FeatureType() {
        cls = (Class<T>)
                ((ParameterizedType) getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[0];
    }

    @Override
    public Class<T> getViewCls() {
        return cls;
    }
}
