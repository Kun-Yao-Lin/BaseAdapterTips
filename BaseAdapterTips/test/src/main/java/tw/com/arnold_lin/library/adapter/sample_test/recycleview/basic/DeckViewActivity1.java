package tw.com.arnold_lin.library.adapter.sample_test.recycleview.basic;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.appeaser.deckview.views.DeckChildView;
import com.appeaser.deckview.views.DeckView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import tw.com.arnold_lin.library.adapter.sample_test.R;


public class DeckViewActivity1 extends Activity {

    private boolean isShowing = false;
    private View recycleView = null;

    private DeckView<CardMsgView.Data> mDeckView;
    private FrameLayout linearLayout;
    private ArrayList<CardMsgView.Data> mEntries;

    private ScrollView scrollView;
    private DeckChildView<CardMsgView.Data> childView;
//    private CardMsgView.Data curItem;

    int scrollToChildIndex = -1;
    private Drawable mDefaultHeaderIcon;
    // Callback implementation
    private DeckView.Callback<CardMsgView.Data> deckViewCallback = new DeckView.Callback<CardMsgView.Data>() {
        @Override
        public ArrayList<CardMsgView.Data> getData() {
            return mEntries;
        }

        @Override
        public void loadViewData(WeakReference<DeckChildView<CardMsgView.Data>> dcv, CardMsgView.Data item) {
            loadViewDataInternal(item, dcv.get());
        }

        @Override
        public void unloadViewData(CardMsgView.Data item) {
        }

        @Override
        public void onViewDismissed(CardMsgView.Data item) {
        }

        @Override
        public void onItemClick(final CardMsgView.Data item) {
            if(isShowing == true){
                return;
            }
            isShowing = true;
            recycleView = item.getView();
            int count = mDeckView.getChildCount();
            for(int i = 0 ; i < count; i ++){
                final View child = mDeckView.getChildAt(i);
                boolean isRecycleView = child.equals(recycleView);
                AnimationSet animationSet = new AnimationSet(true);
                animationSet.setFillAfter(true);
                animationSet.setDuration(500 + (isRecycleView ? 300 : 0));
                Animation animation = new TranslateAnimation(
                        Animation.RELATIVE_TO_PARENT, 0,
                        Animation.RELATIVE_TO_PARENT, 0,
                        Animation.RELATIVE_TO_PARENT, 0,
                        Animation.RELATIVE_TO_PARENT, 1.2f);
                if(isRecycleView ==  true){
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            mDeckView.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);

                            AnimationSet animationSet = new AnimationSet(true);
                            animationSet.setFillAfter(true);
                            animationSet.setDuration(500);
                            animation = new TranslateAnimation(
                                    Animation.RELATIVE_TO_PARENT, 0,
                                    Animation.RELATIVE_TO_PARENT, 0,
                                    Animation.RELATIVE_TO_PARENT, 1,
                                    Animation.RELATIVE_TO_PARENT, 0);
                            animationSet.addAnimation(animation);

                            animation = new ScaleAnimation(
                                    0.8f, 1f,
                                    1f, 1f,
                                    Animation.RELATIVE_TO_PARENT, 0.5f,
                                    Animation.RELATIVE_TO_PARENT, 0.5f);
                            animationSet.addAnimation(animation);
                            childView.onTaskBound(item);
                            loadViewDataInternal(item, childView, true, false);
                            scrollView.setBackgroundColor(item.color);
                            scrollView.startAnimation(animationSet);
                            Log.d("onItemClick","pop out");
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                }else {
                    Log.d("onItemClick","======================");
                    Log.d("onItemClick","recycleView : "+recycleView.hashCode());
                    Log.d("onItemClick","child : "+child.hashCode());
                }
                animationSet.addAnimation(animation);
                animation = new ScaleAnimation(
                        1f, 0.8f,
                        1f, 1f,
                        Animation.RELATIVE_TO_PARENT, 0.5f,
                        Animation.RELATIVE_TO_PARENT, 0.5f);
                animationSet.addAnimation(animation);

                child.startAnimation(animationSet);
            }
        }

        @Override
        public void onNoViewsToDeck() {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= 11){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        }

        int height2 = (int)getResources().getDimension(R.dimen.height_unit) * 2;
        mDefaultHeaderIcon = getResources().getDrawable(R.mipmap.default_header_icon);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        linearLayout = new FrameLayout(this);
        linearLayout.setPadding(0, height2, 0, 0);
        linearLayout.setLayoutParams(params);

        scrollView = new ScrollView(this);
        scrollView.setVisibility(View.GONE);
        scrollView.setLayoutParams(params);
        linearLayout.addView(scrollView);

        mDeckView = new DeckView<CardMsgView.Data>(this){
            @Override
            public boolean onInterceptTouchEvent(MotionEvent ev) {
                if(ev.getAction() == MotionEvent.ACTION_MOVE){
                    return true;
                }
                return super.onInterceptTouchEvent(ev);
            }

            @Override
            public DeckChildView<CardMsgView.Data> createView(Context context) {
                DeckChildView<CardMsgView.Data> childView = super.createView(context);
                insertCardMsgView(childView);
                return childView;
            }
        };

        linearLayout.addView(mDeckView);
        childView = (DeckChildView<CardMsgView.Data>)View.inflate(this, com.appeaser.deckview.R.layout.deck_child_view, null);
        childView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        insertCardMsgView(childView);
        scrollView.addView(childView);

        setContentView(linearLayout);

        initData();
        mDeckView.initialize(deckViewCallback);
    }

    private void initData(){
        if (mEntries == null) {
            mEntries = new ArrayList<>();

            int i = 0;
            Integer color = Color.argb(255, 107, 192, 9);
            mEntries.add(new CardMsgView.Data(color, i));

            i++;
            color = Color.argb(255, 46, 199, 127);
            mEntries.add(new CardMsgView.Data(color, i));

            i++;
            color = Color.argb(255, 28, 139, 206);
            mEntries.add(new CardMsgView.Data(color, i));

            i++;
            color = Color.argb(255, 144, 92, 225);
            mEntries.add(new CardMsgView.Data(color, i));

        }
    }

    private void loadViewDataInternal(final CardMsgView.Data data,
                              final DeckChildView<CardMsgView.Data> view, boolean showCross, boolean replace) {
        View dismissBtn = view.findViewById(R.id.dismiss_task);
        dismissBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
        if(showCross == false) {
            dismissBtn.setVisibility(View.GONE);
        }else {
            dismissBtn.setVisibility(View.VISIBLE);
        }


        if (view != null) {
            view.onDataLoaded(data, data.colorBitmap, mDefaultHeaderIcon, "臨櫃匯款 "+data.number, data.color);
            CardMsgView card = (CardMsgView)view.findViewById(R.id.card_);
            if(replace == true){
                data.setView(view);
            }
        }
    }

    private void reset(){
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setFillAfter(true);
        animationSet.setRepeatCount(0);
        animationSet.setDuration(500);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animation.cancel();
                scrollView.setVisibility(View.GONE);
                scrollView.clearAnimation();
                mDeckView.setVisibility(View.VISIBLE);
                int count = mDeckView.getChildCount();
                AnimationSet animationSet = new AnimationSet(true);
                animationSet.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        isShowing = false;
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                animationSet.setFillAfter(true);
                animationSet.setDuration(500);
                animation = new TranslateAnimation(
                        Animation.RELATIVE_TO_PARENT, 0,
                        Animation.RELATIVE_TO_PARENT, 0,
                        Animation.RELATIVE_TO_PARENT, 1.2f,
                        Animation.RELATIVE_TO_PARENT, 0);
                animationSet.addAnimation(animation);

                for(int i = 0 ; i < count; i++){
                    View child = mDeckView.getChildAt(i);
                    child.startAnimation(animationSet);
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        Animation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 1);
        animationSet.addAnimation(animation);
        animation = new ScaleAnimation(
                1f, 0.8f,
                1f, 1f,
                Animation.RELATIVE_TO_PARENT, 0.5f,
                Animation.RELATIVE_TO_PARENT, 0.5f);
        animationSet.addAnimation(animation);
        scrollView.startAnimation(animationSet);
    }

    private void insertCardMsgView(DeckChildView<CardMsgView.Data> childView){
        View view = childView.findViewById(R.id.task_view_content);
        if(view instanceof ViewGroup){
            CardMsgView card = new CardMsgView(this);
            card.setId(R.id.card_);
            ((ViewGroup) view).addView(card);
        }
    }

    private void loadViewDataInternal(final CardMsgView.Data data,
                              final DeckChildView<CardMsgView.Data> view) {
        loadViewDataInternal(data, view, false, true);
    }

    @Override
    public void onBackPressed() {
        if(isShowing == true){
            reset();
        }else {
            super.onBackPressed();
        }
    }
}
