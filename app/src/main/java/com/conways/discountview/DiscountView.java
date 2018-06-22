package com.conways.discountview;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;


public class DiscountView extends View {

    private int bgColor = 0xfffc4a40;
    private int rotate = 45;
    private String discount = "9.5折扣";

    private Paint bgPaint;
    private Paint discountPaint;

    private int textSize;

    private int mWith;
    private int mHeight;


    private Rect rect;

    private int topBottomPadding;

    private Path path;


    public DiscountView(Context context) {
        super(context);
        init(context);
    }

    public DiscountView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DiscountView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DiscountView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    private void init(Context context) {
        bgPaint = new Paint();
        bgPaint.setAntiAlias(true);
//        bgPaint.setStyle(Paint.Style.FILL);
        bgPaint.setColor(bgColor);


        textSize = DensityUtil.sp2px(context, 12);

        discountPaint = new Paint();
        discountPaint.setAntiAlias(true);

        rect = new Rect();

        topBottomPadding = DensityUtil.dip2px(context, 0.5f);

        path=new Path();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMeasureMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMeasureMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMeasureMode == MeasureSpec.EXACTLY) {
            mWith = MeasureSpec.getSize(widthMeasureSpec);
        } else {
            mWith = 400;
        }
        if (heightMeasureMode == MeasureSpec.EXACTLY) {
            mHeight = MeasureSpec.getSize(heightMeasureSpec);
        } else {
            mHeight = 400;
        }
        setMeasuredDimension(mWith, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBg(canvas);
    }

    private void drawBg(Canvas canvas) {
        discountPaint.setTextSize(textSize);
        discountPaint.getTextBounds(discount, 0, discount.length(), rect);
        int tempHeight = rect.width() + 2 * topBottomPadding;

        path.reset();
        path.moveTo(0, 0);
        path.lineTo((float) (tempHeight / Math.sin(Math.PI * rotate / 180)), 0);
        path.lineTo(mWith, (float) (mHeight - tempHeight / Math.cos(Math.PI * rotate / 180)));
        path.lineTo(mWith, mHeight);
        path.lineTo(0, 0);
        path.close();
        canvas.drawPath(path, bgPaint);

    }

    private void drawText(Canvas canvas) {
    }


}
