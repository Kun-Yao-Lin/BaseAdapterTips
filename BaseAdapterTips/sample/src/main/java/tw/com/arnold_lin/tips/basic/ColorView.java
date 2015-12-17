package tw.com.arnold_lin.tips.basic;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by arnold_lin on 2015/12/18.
 */
public class ColorView extends FrameLayout {

    private TextView number;

    public ColorView(Context context) {
        this(context,null);
    }

    public ColorView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ColorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    private void initialize(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        number = new TextView(context);
        number.setGravity(Gravity.CENTER);
        number.setTypeface(null, Typeface.BOLD);
        number.setTextSize(100);
        number.setTextColor(context.getResources().getColor(android.R.color.white));
        number.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,metrics.heightPixels/3));
        addView(number);
    }

    public void setNumber(int number) {
        this.number.setText(""+number);;
    }
}
