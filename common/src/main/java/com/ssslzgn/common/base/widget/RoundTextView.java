package com.ssslzgn.common.base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;

import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.LogUtils;
import com.ssslzgn.common.R;


/**
 * 自定义圆角、点击TextView
 */
public class RoundTextView extends AppCompatTextView {

    private int strokeWidth;    // 边框线宽
    private int strokeColor;    // 边框颜色
    private int enableColor;    // 不可点击颜色
    private int contentColor;   // 背景颜色
    private int pressedColor;   // 按下背景颜色
    private int cornerRadius;   // 圆角半径
    private boolean mFollowTextColor; // 边框颜色是否跟随文字颜色
    //四方位，左上，右上，右下，左下
    private float topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius;

    private Paint mPaint = new Paint();                 // 画边框所使用画笔对象
    private RectF mRectF = new RectF();                 // 画边框要使用的矩形

    public RoundTextView(Context context) {
        super(context);
    }

    public RoundTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RoundTextView);
        contentColor = array.getColor(R.styleable.RoundTextView_tx_background_color, Color.TRANSPARENT);
        pressedColor = array.getColor(R.styleable.RoundTextView_tx_bg_pressed_color, contentColor);
        enableColor = array.getColor(R.styleable.RoundTextView_tx_bg_enable_false_Color, Color.parseColor("#FFFFFF"));
        strokeWidth = array.getDimensionPixelSize(R.styleable.RoundTextView_tx_stroke_width, 0);
        strokeColor = array.getColor(R.styleable.RoundTextView_tx_stroke_color, contentColor);
        cornerRadius = array.getDimensionPixelSize(R.styleable.RoundTextView_tx_radius, 0);
        LogUtils.d("圆角： " + cornerRadius);
        mFollowTextColor = array.getBoolean(R.styleable.RoundTextView_tx_follow_textColor, false);

        topLeftRadius = array.getDimension(R.styleable.RoundTextView_tx_topLeftRadius, 0);
        topRightRadius = array.getDimension(R.styleable.RoundTextView_tx_topRightRadius, 0);
        bottomRightRadius = array.getDimension(R.styleable.RoundTextView_tx_bottomRightRadius, 0);
        bottomLeftRadius = array.getDimension(R.styleable.RoundTextView_tx_bottomLeftRadius, 0);
        array.recycle();
        initView();
    }

    private void initView() {
        // 初始化画笔
        mPaint.setStyle(Paint.Style.STROKE);     // 空心效果
        mPaint.setAntiAlias(true);               // 设置画笔为无锯齿
        mPaint.setStrokeWidth(strokeWidth);      // 线宽
        // 设置边框线的颜色, 如果声明为边框跟随文字颜色且当前边框颜色与文字颜色不同时重新设置边框颜色
        if (mFollowTextColor && strokeColor != getCurrentTextColor())
            strokeColor = getCurrentTextColor();
        // 设置背景
        setBackground(getPressedSelector(enableColor, contentColor, pressedColor, cornerRadius));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 设置画笔颜色
        mPaint.setColor(strokeColor);
        // 画空心圆角矩形
        if (strokeWidth > 0) {
            mRectF.left = mRectF.top = 0.5f * strokeWidth;
            mRectF.right = getMeasuredWidth() - 0.5f * strokeWidth;
            mRectF.bottom = getMeasuredHeight() - 0.5f * strokeWidth;
            canvas.drawRoundRect(mRectF, cornerRadius, cornerRadius, mPaint);
        }
    }

    /**
     * 修改边框宽度
     *
     * @param rodeWidth 传值：px
     */
    public void setStrokeWidth(int rodeWidth) {
        try {
            strokeWidth = rodeWidth;
            invalidate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 修改边框颜色
     *
     * @param colorResource 传值：R.color.XXXX
     */
    public void setStrokeColor(@ColorRes int colorResource) {
        try {
            strokeColor = ContextCompat.getColor(getContext(), colorResource);
            invalidate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改背景颜色
     *
     * @param colorResource 传值：R.color.XXXX
     */
    public void setContentColorResource(@ColorRes int colorResource) {
        try {
            contentColor = ContextCompat.getColor(getContext(), colorResource);
            setBackground(getPressedSelector(enableColor, contentColor, contentColor, cornerRadius));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改圆角
     */
    public void setCornerRadius(int radius) {
        try {
            cornerRadius = radius;
            if (radius == 0) {
                topLeftRadius = 0;
                topRightRadius = 0;
                bottomRightRadius = 0;
                bottomLeftRadius = 0;
            }
            setBackground(getPressedSelector(enableColor, contentColor, contentColor, cornerRadius));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改圆角
     */
    public void setTopRightRadius(int rightRadius) {
        try {
            cornerRadius = 0;
            topRightRadius = rightRadius;
            setBackground(getPressedSelector(enableColor, contentColor, contentColor, cornerRadius));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改圆角
     */
    public void setBottomRightRadius(int rightRadius) {
        try {
            cornerRadius = 0;
            bottomRightRadius = rightRadius;
            setBackground(getPressedSelector(enableColor, contentColor, contentColor, cornerRadius));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 用java代码的方式动态生成状态选择器
     */
    private Drawable getPressedSelector(int enabledColor, int normalColor, int pressedColor, int radius) {
        Drawable enabled = createShape(enabledColor, radius);
        Drawable pressed = createShape(pressedColor, radius);
        Drawable normal = createShape(normalColor, radius);
        StateListDrawable drawable = new StateListDrawable();
        drawable.addState(new int[]{android.R.attr.state_pressed}, pressed);    // 按下状态 , 设置按下的图片
        drawable.addState(new int[]{android.R.attr.state_enabled}, normal);     // 默认状态,默认状态下的图片
        drawable.addState(new int[]{}, enabled);                                // 不可点击状态
        //设置状态选择器过度动画/渐变选择器/渐变动画
//        drawable.setEnterFadeDuration(500);
//        drawable.setExitFadeDuration(500);
        return drawable;
    }

    private GradientDrawable createShape(int color, int radius) {
        GradientDrawable drawable = new GradientDrawable();
        float outRect[] = new float[]{topLeftRadius, topLeftRadius, topRightRadius, topRightRadius, bottomRightRadius, bottomRightRadius, bottomLeftRadius, bottomLeftRadius};
        if (radius != 0) {
            drawable.setCornerRadius(radius);//设置4个角的弧度
        } else {
            drawable.setCornerRadii(outRect);
        }
        drawable.setColor(color);// 设置颜色
        return drawable;
    }
}
