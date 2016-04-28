package tw.com.arnold_lin.tips.normal.basic;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import tw.com.arnold_lin.tips.R;

/**
 * Created by arnold_lin on 2015/12/18.
 */
public class CardMsgView extends FrameLayout {

    private int raduis;
    private int height_2;
    private Paint paint;
    private TextView number;
    private TextView msg;

    public CardMsgView(Context context) {
        this(context, null);
    }

    public CardMsgView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CardMsgView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    private void initialize(Context context) {
        setWillNotDraw(false);

        Resources res = getResources();
        int height = (int)res.getDimension(R.dimen.height_unit);
        height_2 = (int)res.getDimension(R.dimen.height_unit_half);
        raduis = height_2 / 4;
        int padding = (int)res.getDimension(R.dimen.padding);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10);

        setId(R.id.expandable_toggle_button);

        LinearLayout layout = new LinearLayout(context);
        layout.setPadding(height_2, padding, height_2,  padding);
        layout.setBackgroundResource(android.R.color.transparent);
        layout.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        layout.setOrientation(LinearLayout.VERTICAL);
        addView(layout);

        LinearLayout title = new LinearLayout(context);
        title.setBackgroundResource(android.R.color.transparent);
        title.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        title.setOrientation(LinearLayout.HORIZONTAL);
        layout.addView(title);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(height, height);
        ImageView im = new ImageButton(context);
        im.setBackgroundResource(android.R.color.transparent);
        im.setClickable(false);
        im.setImageResource(R.mipmap.ic_launcher);
        im.setLayoutParams(params);
        title.addView(im);

        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
        params.setMargins(0, 0, 0, height_2);
        number = new TextView(context);
        number.setPadding(height_2, 0, 0, 0);
        number.setGravity(Gravity.CENTER | Gravity.LEFT);
        number.setTypeface(null, Typeface.BOLD);
        number.setTextColor(Color.WHITE);
        number.setLayoutParams(params);
        title.addView(number);

        LinearLayout content = new LinearLayout(context);
        content.setPadding(0, 0, 0, height);
        content.setBackgroundResource(android.R.color.transparent);
        content.setId(R.id.expandable);
        content.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        content.setOrientation(LinearLayout.VERTICAL);
        layout.addView(content);

        msg = new TextView(context);
        msg.setPadding(0, 0, 0, padding);
        msg.setGravity(Gravity.CENTER | Gravity.LEFT);
        msg.setTypeface(null, Typeface.BOLD);
        msg.setTextColor(Color.WHITE);
        msg.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        content.addView(msg);

        Button button = new Button(context);
        button.setTextColor(Color.WHITE);
        button.setText("填寫匯款單");
        button.setBackgroundResource(R.drawable.white_stroke);
        button.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));
        button.setGravity(Gravity.CENTER);
        content.addView(button);

        msg.setText("請親自到銀行或郵局，將上述費用匯入下列帳號：\n\n銀行代碼：700(中華郵政)\n銀行帳號：004124 141343\n戶名：許梅\n\n完成後請按下「填寫匯款單」以便我們進行對帳");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = roundedRect(0, 0, canvas.getWidth(), canvas.getHeight(), raduis, raduis, true);
        canvas.drawPath(path, paint);
    }

    public void setNumber(int number) {
        this.number.setText("臨櫃匯款 "+number);;
    }

    public void setBackground(int background){
        paint.setColor(background);
        invalidate();
    }

    public Path roundedRect(float left, float top, float right, float bottom, float rx, float ry, boolean conformToOriginalPost) {
        Path path = new Path();
        if (rx < 0) rx = 0;
        if (ry < 0) ry = 0;
        float width = right - left;
        float height = bottom - top;
        if (rx > width/2) rx = width/2;
        if (ry > height/2) ry = height/2;
        float widthMinusCorners = (width - (2 * rx));
        float heightMinusCorners = (height - (2 * ry));

        path.moveTo(right, top + ry);
        path.rQuadTo(0, -ry, -rx, -ry);//top-right corner
        path.rLineTo(-widthMinusCorners, 0);
        path.rQuadTo(-rx, 0, -rx, ry); //top-left corner
        path.rLineTo(0, heightMinusCorners);

        if (conformToOriginalPost) {
            path.rLineTo(0, ry);
            path.rLineTo(width, 0);
            path.rLineTo(0, -ry);
        } else {
            path.rQuadTo(0, ry, rx, ry);//bottom-left corner
            path.rLineTo(widthMinusCorners, 0);
            path.rQuadTo(rx, 0, rx, -ry); //bottom-right corner
        }

        path.rLineTo(0, -heightMinusCorners);

        path.close();//Given close, last lineto can be removed.

        return path;
    }

    public static Animation createUpFromBottom(Context context){
        AnimationSet animation = new AnimationSet(true);
        animation.setInterpolator(new DecelerateInterpolator());

        int height_2 = (int)context.getResources().getDimension(R.dimen.height_unit_half);

        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, height_2,
                Animation.ABSOLUTE, 0
                );
        translateAnimation.setDuration(800);
        animation.addAnimation(translateAnimation);
        return animation;
    }

    public static Animation createDownFromTop(Context context){
        AnimationSet animation = new AnimationSet(true);
        animation.setInterpolator(new DecelerateInterpolator());

        int height_2 = (int)context.getResources().getDimension(R.dimen.height_unit_half) / 2;

        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, -height_2,
                Animation.ABSOLUTE, 0
        );
        translateAnimation.setDuration(800);
        animation.addAnimation(translateAnimation);
        return animation;
    }
}
