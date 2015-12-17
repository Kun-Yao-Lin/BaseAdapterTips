package tw.com.arnold_lin.tips.mix.adapters.core;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import java.util.HashMap;
import java.util.Map;

import tw.com.arnold_lin.tips.mix.adapters.binders.Binder;

/**
 * Created by arnold_lin on 2015/12/3.
 */
public class FeatureBasicLayout extends FrameLayout implements ViewBinderImp<Binder>{
    private static final String TAG = "FeatureBasicLayout";
    private Map<Class<?extends ViewBinderImp>,View> viewBinders = new HashMap<>();

    private Class<?extends ViewBinderImp> currViewBinderCls = null;
    private View currBinderView = null;

    public FeatureBasicLayout(Context context) {
        super(context);
        initialize(context);
    }

    public FeatureBasicLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public FeatureBasicLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    private void initialize(Context context) {
//        setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public void init() {

    }

    @Override
    public void setBinder(Binder binder) {
        setBinderView(binder);
        if(currBinderView instanceof ViewBinderImp){
            ((ViewBinderImp) currBinderView).init();
            ((ViewBinderImp) currBinderView).setBinder(binder);
        }
    }

    @Override
    public View getView() {
        return currBinderView;
    }

    private void setBinderView(Binder binder){


        if(binder == null){
            throw new NullPointerException("Binder cannot be null.");
        }

        Class<?extends ViewBinderImp> viewBinderImpCls = binder.getViewBinderCls();
        if(viewBinderImpCls == null){
            throw new NullPointerException("ViewBinderImp cannot be null.");
        }

        if(viewBinders.containsKey(viewBinderImpCls) == false){
            throw new RuntimeException("This layout is not exist : "+binder.getClass().getSimpleName());
        }

        if(currViewBinderCls == viewBinderImpCls){
            return;
        }

        currViewBinderCls = viewBinderImpCls;
        if(currBinderView != null){
            currBinderView.setVisibility(GONE);
        }
        currBinderView = viewBinders.get(currViewBinderCls);
        currBinderView.setVisibility(VISIBLE);
    }

    public void addBinderView(ViewBinderImp binder){
        if(binder == null){
            return;
        }
        viewBinders.put(binder.getClass(),binder.getView());
        View view = binder.getView();
        view.setVisibility(GONE);
        addView(view);
    }
}
