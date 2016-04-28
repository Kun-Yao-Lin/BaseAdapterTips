package tw.com.arnold_lin.library.adapters.common.feature.core;

import android.view.View;

/**
 * Created by arnold_lin on 2015/12/10.
 */
public interface FeatureTypeImp<T extends View> {
    public Class<T> getViewCls();
}
