package tw.com.arnold_lin.library.adapter.sample_test.recycleview.basic;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import tw.com.arnold_lin.library.adapter.sample_test.R;


/**
 * Created by arnold_lin on 2015/12/18.
 */
public class CardMsgView extends LinearLayout {

    private int raduis;
    private int height_2;
    private LinearLayout linearLayout;
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

    private void initialize(final Context context) {
        setFocusable(false);
        setFocusableInTouchMode(true);
        setWillNotDraw(false);
        Resources res = getResources();
//        int height = (int)res.getDimension(R.dimen.height_unit);
        int height = (int)res.getDimension(R.dimen.deck_child_header_bar_height);

        height_2 = (int)res.getDimension(R.dimen.height_unit_half);
        raduis = height_2 / 4;
        int padding = (int)res.getDimension(R.dimen.padding);

        setPadding(0, height, 0,  0);
        setBackgroundResource(android.R.color.transparent);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setOrientation(LinearLayout.VERTICAL);

        linearLayout = new LinearLayout(context);
        linearLayout.setPadding(height_2, padding, height_2, padding);
        linearLayout.setBackgroundResource(android.R.color.transparent);
        linearLayout.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        addView(linearLayout);

        msg = new TextView(context);
        msg.setPadding(0, 0, 0, padding);
        msg.setGravity(Gravity.CENTER | Gravity.LEFT);
        msg.setTypeface(null, Typeface.BOLD);
        msg.setTextColor(Color.WHITE);
        msg.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(msg);

        Button button = new Button(context);
        button.setTextColor(Color.WHITE);
        button.setText("title");
        button.setBackgroundResource(R.drawable.white_stroke);
        button.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));
        button.setGravity(Gravity.CENTER);
        linearLayout.addView(button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"title",Toast.LENGTH_LONG).show();
            }
        });

        String m = "title content";
        String text = m;
        for(int i = 0 ; i < 3 ; i++){
            text += m;
        }
        msg.setText(text);
    }

    public void setBackground(int background){
        linearLayout.setBackgroundColor(background);
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

    public static class Data {
        public Integer color;
        public Bitmap colorBitmap;
        public Integer number;
        private View view;

        public Data(Integer color, Integer number) {
            this.color = color;
            this.colorBitmap = createColorBitmap(color);
            this.number = number;
        }

        public View getView() {
            return view;
        }

        public void setView(View view) {
            this.view = view;
        }

        private Bitmap createColorBitmap(int color) {
            Bitmap image = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_4444);
            image.eraseColor(color);
            return image;
        }
    }
}
