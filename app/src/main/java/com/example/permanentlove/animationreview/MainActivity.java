package com.example.permanentlove.animationreview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_viewAnimation,btn_drawableAnimation,btn_propertyAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        initView();
        initListener();
    }

    private void initListener() {
        btn_viewAnimation.setOnClickListener(this);
        btn_drawableAnimation.setOnClickListener(this);
        btn_propertyAnimation.setOnClickListener(this);
    }

    private void initView() {
        btn_viewAnimation= (Button) findViewById(R.id.btn_viewAnimation);
        btn_drawableAnimation= (Button) findViewById(R.id.btn_drawableAnimation);
        btn_propertyAnimation= (Button) findViewById(R.id.btn_propertyAnimation);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_viewAnimation:
                startActivity(new Intent(this,ViewActivity.class));
                break;
            case R.id.btn_drawableAnimation:
                startActivity(new Intent(this,drawableActivity.class));
                break;
            case R.id.btn_propertyAnimation:
                startActivity(new Intent(this,PropertyActivity.class));
                break;
        }
    }
}
