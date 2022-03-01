package com.example.sample_4_13;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * @author: liuming
 * @date: 2022/2/23
 */
public class MyDrawThread extends Thread{

    boolean flag = true;
    boolean pauseFlag = false;
    MyView mv;
    SurfaceHolder mSurfaceHolder;

    public MyDrawThread(MyView mv){
        this.mv = mv;
        mSurfaceHolder = mv.getHolder();
    }

    @Override
    public void run() {
        super.run();
        Canvas c;
        while (flag){
            c = null;
            if(!pauseFlag){
                try{
                    c = mSurfaceHolder.lockCanvas();
                    synchronized (mSurfaceHolder){
                        mv.onDraw(c);
                    }
                }finally {
                    if(c != null){
                        mSurfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
            try{
                Thread.sleep(50);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
