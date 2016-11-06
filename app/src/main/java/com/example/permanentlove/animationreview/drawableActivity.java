package com.example.permanentlove.animationreview;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class drawableActivity extends AppCompatActivity{

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable);
        imageView= (ImageView) findViewById(R.id.id_drawable);

        imageView.setImageResource(R.drawable.animationlist);
        AnimationDrawable ad= (AnimationDrawable) imageView.getDrawable();
        ad.start();

    }


}
