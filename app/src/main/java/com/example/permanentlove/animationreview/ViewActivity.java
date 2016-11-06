package com.example.permanentlove.animationreview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class ViewActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_code,btn_src;
    private ImageView iv_AnimationSrc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        init();
    }

    private void init() {
        btn_code= (Button) findViewById(R.id.btn_code);
        btn_src= (Button) findViewById(R.id.btn_src);
        iv_AnimationSrc= (ImageView) findViewById(R.id.iv_AnimationSrc);
        btn_code.setOnClickListener(this);
        btn_src.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_code:

                AnimationSet set=new AnimationSet(true);
                //创建AlphaAnimation实例,透明度
               AlphaAnimation alphaAnimation=new AlphaAnimation(0.1f,1f);
                alphaAnimation.setDuration(2000);
                alphaAnimation.setFillBefore(false);
                alphaAnimation.setInterpolator(new AccelerateInterpolator(0.5f));
                set.addAnimation(alphaAnimation);


                //创建translationAnimation实例，位移
                TranslateAnimation translateAnimation=new TranslateAnimation(100,100,300,300);
                translateAnimation.setDuration(2000);
                set.addAnimation(translateAnimation);

                //创建scaleAnimation实例，缩放
                ScaleAnimation scaleAnimation=new ScaleAnimation(0,0,1,1,0.5f,0.5f);
                scaleAnimation.setDuration(2000);
               // set.addAnimation(scaleAnimation);
                //创建rotationAnimation实例，旋转
                RotateAnimation rotateAnimation=new RotateAnimation(0,360,200,200);
                rotateAnimation.setDuration(2000);
                //循环
                rotateAnimation.setRepeatCount(Animation.INFINITE);

                set.addAnimation(rotateAnimation);
                iv_AnimationSrc.startAnimation(set);

                break;
            case R.id.btn_src:
                Animation mAnimation= AnimationUtils.loadAnimation(this,R.anim.anim);
                iv_AnimationSrc.startAnimation(mAnimation);
                break;
        }
    }
}
