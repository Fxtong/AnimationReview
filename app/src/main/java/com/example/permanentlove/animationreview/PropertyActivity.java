package com.example.permanentlove.animationreview;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class PropertyActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_simple,btn_set,btn_xml,btn_get;
    private ImageView iv;
    private ObjectAnimator object;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);
        init();
    }

    private void init() {
        btn_simple= (Button) findViewById(R.id.btn_simple);
        btn_set= (Button) findViewById(R.id.btn_set);
        btn_xml= (Button) findViewById(R.id.btn_xml);
        btn_get= (Button) findViewById(R.id.btn_get);
        btn_get.setOnClickListener(this);
        btn_set.setOnClickListener(this);
        btn_simple.setOnClickListener(this);
        btn_xml.setOnClickListener(this);
        iv= (ImageView) findViewById(R.id.iv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_simple:
                ObjectAnimator.ofFloat(iv,"alpha",1f,0.2f,1f).setDuration(2000).start();
                //随便改变按钮背景
                object=ObjectAnimator.ofInt(btn_simple,"backgroundColor",0xffff8080,0xff8080FF);
                object.setEvaluator(new ArgbEvaluator());
                object.setDuration(5000);
                object.setRepeatCount(ValueAnimator.INFINITE);
                object.setRepeatMode(ValueAnimator.REVERSE);
                object.start();

                break;
            case R.id.btn_set:
                //使用Animator实现一系列动画
                AnimatorSet set=new AnimatorSet();
                //playTogether()同时进行,playSequentially()按顺序进行，
                set.playSequentially(
                        ObjectAnimator.ofFloat(iv,"alpha",1f,0.2f,1f),
                        ObjectAnimator.ofFloat(iv,"scaleX",1,1.5f),
                        ObjectAnimator.ofFloat(iv,"scaleY",1,0.5f),
                        ObjectAnimator.ofFloat(iv,"translationX",0,90),
                        ObjectAnimator.ofFloat(iv,"translationY",0,90),
                        ObjectAnimator.ofFloat(iv,"rotationX",0,360),
                        ObjectAnimator.ofFloat(iv,"rotationY",0,180)
                );
                set.setDuration(3000).start();


                break;
            case R.id.btn_xml:
                value();

                break;
            case R.id.btn_get:
                perform(btn_get,btn_get.getWidth(),100);
                break;
        }
    }

    private void perform(final View target,final int start,final int end) {
        ValueAnimator v=ValueAnimator.ofInt(1,100);

        v.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                //获得动画进度比例
                float fraction=animation.getAnimatedFraction();
                //调用整型估值器
                target.getLayoutParams().width=new IntEvaluator().evaluate(fraction,start,end);
                target.requestLayout();


            }
        });
        v.setDuration(2000).start();
    }

    private void value() {
        PointF startP = new PointF(600, 0);
        PointF endP = new PointF(20, 900);
        //TypeEvaluator支持泛类
        ValueAnimator valueAnimator=ValueAnimator.ofObject(new TypeEvaluator<PointF>() {
            @Override
            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
               //fraction进度，startValue开始值，endValue结束值
                PointF pointF = new PointF();
                pointF.x = (1 - fraction) * (1 - fraction) * startValue.x + 2 * fraction * (1 - fraction) * startValue.x + fraction * fraction * endValue.x;
                pointF.y = (1 - fraction) * (1 - fraction) * startValue.y + 2 * fraction * (1 - fraction) * endValue.y + fraction * fraction * endValue.y;
                return pointF;
            }


        },startP,endP);
        //addUpdateListener监听动画整个过程
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //获取进度值
                PointF f1 = (PointF) animation.getAnimatedValue();
                iv.setY(f1.y);
                iv.setX(f1.x);
                iv.invalidate();
            }

        });
        valueAnimator.setDuration(2000);
        //插值器
        valueAnimator.setInterpolator(new BounceInterpolator());
        valueAnimator.start();
        }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        object.clone();
    }
}
