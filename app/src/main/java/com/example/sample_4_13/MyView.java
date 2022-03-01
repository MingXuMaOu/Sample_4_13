package com.example.sample_4_13;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

/**
 * @author: liuming
 * @date: 2022/2/23
 */
public class MyView extends SurfaceView implements SurfaceHolder.Callback {

    MainActivity mActivity;
    MyDrawThread mvdt;
    Paint mPaint;
    float dx;
    float dy;
    float dz;
    float x;
    float y;
    float z;
    float rx;
    float ry;
    float rz;
    float juli2;
    float juli;

    public MyView(MainActivity activity){
        super(activity);
        mActivity = activity;
        getHolder().addCallback(this);
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(30);
        mPaint.setAntiAlias(true);
        mvdt = new MyDrawThread(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(getBitmap(mActivity.shang),105,0,mPaint);
        canvas.drawBitmap(getBitmap(mActivity.yuan),400,150,mPaint);
        canvas.drawBitmap(getBitmap(mActivity.zuo),0,0,mPaint);


        x = dx * 34;
        if(x > 200){
            x = 200;
        }
        if(x < -200){
            x = -200;
        }
        canvas.drawBitmap(mActivity.qiuzuo,10, 300 + x,mPaint);

        y = dy * 34;
        if(y > 500){
            y = 500;
        }
        if(y < -500){
            y = -500;
        }
        canvas.drawBitmap(mActivity.qiushang,610 + y,3,mPaint);

        juli = (float) Math.sqrt((dx * 34) * (dx * 34) + (dy * 34) * (dy * 34));
        juli2 = juli / 170;
        if(juli2 <= 1){
            rx = (dy * 34) / 170;
            ry = (dx * 34) / 170;
        }else{
            if(dy > 0){
                rx = (float) Math.sqrt(2 * dy * dy / (dx * dx + dy * dy));
            }else {
                rx = -(float) Math.sqrt(2 * dy * dy / (dx * dx + dy * dy));
            }
            ry = dx / dy * rx;
        }
        canvas.drawBitmap(mActivity.qiuzhong,630 + rx * 110,380 + ry * 110,mPaint);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        mvdt.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    private Bitmap getBitmap(Bitmap bitmap){
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(0.6f,0.6f);
        Bitmap bmp = Bitmap.createBitmap(bitmap,0,0,w,h,matrix,true);
        return bmp;
    }
}
