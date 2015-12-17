package tw.com.arnold_lin.tips.mix.adapters.core;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by arnold_lin on 2015/12/2.
 */
public class FeatureViewFactory {
    private static String TAG = "FeatureViewFactory";

    private static final Map<Class<? extends FeatureTypeImpl>, Class<? extends View>> map = new HashMap<>();

    public static void register(@NonNull FeatureTypeImpl typeImpl){
        if(typeImpl == null){
            throw new NullPointerException("typeImpl cannot be null.");
        }

        Class<? extends View> viewCls = typeImpl.getViewCls();
        if(viewCls == null){
            throw new NullPointerException("ViewCls cannot be null.");
        }

        if(map.containsKey(typeImpl) == true){
            throw new RuntimeException("FeatureTypeImpl : "+typeImpl.getClass().getSimpleName() +" is exist.");
        }

        map.put(typeImpl.getClass(),viewCls);
    }

    public static FeatureBasicLayout create(Context context, Class<? extends FeatureTypeImpl>... typeClses){
        FeatureBasicLayout layout = new FeatureBasicLayout(context);

        for(Class typeCls : typeClses){
            ViewBinderImp viewBinder = createViewBinder(context,typeCls);
            if(viewBinder != null){
                layout.addBinderView(viewBinder);
            }
        }
        return layout;
    }

    private static ViewBinderImp createViewBinder(Context context, Class<? extends FeatureTypeImpl> typeImplCls){
        if(map.containsKey(typeImplCls) == false){
            throw new NullPointerException("FeatureTypeImpl : "+typeImplCls.getSimpleName() +" is not exist.");
        }
        ViewBinderImp viewBinder ;
        try {
            Class cls = map.get(typeImplCls);
            Constructor<ViewBinderImp> constructor = cls.getConstructor(Context.class);
            viewBinder = constructor.newInstance(context);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        return viewBinder;
    }


}
