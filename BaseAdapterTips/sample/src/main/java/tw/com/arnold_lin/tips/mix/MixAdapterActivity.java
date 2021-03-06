package tw.com.arnold_lin.tips.mix;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import tw.com.arnold_lin.library.adapters.feature.FeatureAdapter;
import tw.com.arnold_lin.library.adapters.feature.binders.Binder;
import tw.com.arnold_lin.tips.BasicAdapterActivity;
import tw.com.arnold_lin.tips.mix.views.BannerView;
import tw.com.arnold_lin.tips.mix.views.DateMsgView;
import tw.com.arnold_lin.tips.mix.views.UnvImageView;


public class MixAdapterActivity extends BasicAdapterActivity<Binder> {

    private int count = 0;
    private Random rnd = new Random();

    @Override
    protected BaseAdapter getAdapter(Context context) {
        return new FeatureAdapter(this,DateMsgView.Type.class,BannerView.Type.class, UnvImageView.Type.class);
    }

    @Override
    protected ArrayList<Binder> getInitData() {
        ArrayList<Binder> binders = new ArrayList<>();

        //banner
        String[] url = new String[]{
                "http://www9.pcmag.com/media/images/475399-android-marshmallow.jpg?thumb=y&width=740&height=426",
                "http://cdn.ndtv.com/tech/images/android_marshmallow.jpg",
                "http://core0.staticworld.net/images/article/2015/08/android-60-marshmallow-100608368-primary.idge.jpg",
        };
        binders.add(new BannerView.Binder(new ArrayList<String>(Arrays.asList(url))));

        //date msg
        for(int i = 0 ; i < 5; i++) {
            binders.add(new DateMsgView.Binder("Hello "+i));
        }

        //banner
        url = new String[]{
                "http://cdn.ndtv.com/tech/images/android_marshmallow.jpg",
                "http://core0.staticworld.net/images/article/2015/08/android-60-marshmallow-100608368-primary.idge.jpg",
        };

        binders.add(new BannerView.Binder(new ArrayList<String>(Arrays.asList(url))));

        //date msg
        for(int i = 0 ; i < 10; i++) {
            binders.add(new DateMsgView.Binder("Hello 2"+i));
        }

        return binders;
    }

    @Override
    protected Binder getSingleData() {
        int type = rnd.nextInt(3);
        Binder binder;
        switch (type){
            case 0:
                binder = new DateMsgView.Binder("new Data "+count);
                break;
            case 1:
                String[] urls = new String[]{
                        "http://cdn.ndtv.com/tech/images/android_marshmallow.jpg",
                        "http://core0.staticworld.net/images/article/2015/08/android-60-marshmallow-100608368-primary.idge.jpg",
                };
                binder = new BannerView.Binder(new ArrayList<String>(Arrays.asList(urls)));
                break;
            default:
                binder = new UnvImageView.Binder("http://core0.staticworld.net/images/article/2015/08/android-60-marshmallow-100608368-primary.idge.jpg");
                break;
        }
        count ++;
        return binder;
    }
}
