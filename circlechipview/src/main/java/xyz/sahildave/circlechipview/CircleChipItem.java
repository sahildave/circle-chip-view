package xyz.sahildave.circlechipview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * TODO: document your custom view class.
 */
public class CircleChipItem extends FrameLayout {
    private static final String TAG = "CircleChipItem";

    private String mLabelText;
    private int mLabelBackgroundColor;
    private int mLabelTextColor;
    private Drawable mIconDrawable;

    private TextView mLabelView;
    private ImageView mIcon;

    public CircleChipItem(Context context) {
        super(context);
        initAttrs(context, null, 0);
    }

    public CircleChipItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs, 0);
    }

    public CircleChipItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initAttrs(context, attrs, defStyle);
    }

    private void initAttrs(Context context, AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ccv_CircleChipItem, defStyle, 0);

        mLabelText = a.getString(R.styleable.ccv_CircleChipItem_ccv_label_text);
        mLabelBackgroundColor = a.getColor(R.styleable.ccv_CircleChipItem_ccv_label_bg_color, getDefaultLabelBgColor(context));
        mLabelTextColor = a.getColor(R.styleable.ccv_CircleChipItem_ccv_label_text_color, getDefaultLabelTextColor(context));
        mIconDrawable = a.getDrawable(R.styleable.ccv_CircleChipItem_ccv_icon_src);

        a.recycle();

        initView(context);
    }

    private void initView(Context context) {
        inflate(context, R.layout.ccv_layout_item, this);
        mLabelView = (TextView) findViewById(R.id.cvv_label);
        mIcon = (ImageView) findViewById(R.id.cvv_icon);

        if (TextUtils.isEmpty(mLabelText)) {
            Log.e(TAG, "Label text is empty");
        }

        int labelHeight = context.getResources().getDimensionPixelSize(R.dimen.ccv_label_height);
        int radius = labelHeight / 2;

        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadii(new float[]{radius, radius, radius, radius, radius, radius, radius, radius});
        shape.setColor(mLabelBackgroundColor);
        mLabelView.setBackgroundDrawable(shape);
        mLabelView.setText(mLabelText);
        mLabelView.setTextColor(mLabelTextColor);

        if (mIconDrawable != null) {
            mIcon.setImageDrawable(mIconDrawable);
            int leftPadding = context.getResources().getDimensionPixelSize(R.dimen.ccv_label_padding_left);
            mLabelView.setPadding(leftPadding + labelHeight, mLabelView.getPaddingTop(), mLabelView.getPaddingRight(), mLabelView.getPaddingBottom());
        }
    }

    private int getDefaultLabelBgColor(Context context) {
        return CircleChipUtil.getThemeColor(context, R.attr.colorAccent);
    }

    private int getDefaultLabelTextColor(Context context) {
        return CircleChipUtil.getThemeColor(context, android.R.attr.textColorSecondary);
    }

    public ImageView getIcon() {
        return mIcon;
    }

    public TextView getLabelView() {
        return mLabelView;
    }
}
