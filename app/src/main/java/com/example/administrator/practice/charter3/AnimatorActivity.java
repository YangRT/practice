package com.example.administrator.practice.charter3;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AnimationSet;
import android.widget.TextView;

import com.example.administrator.practice.R;

public class AnimatorActivity extends AppCompatActivity {
    private TextView mTextView;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
        mTextView = findViewById(R.id.animator_text);
        textView = findViewById(R.id.animator_text2);
        MyAnimView animView = findViewById(R.id.animator_my_anim_view);
        ObjectAnimator mAnimator = ObjectAnimator.ofObject(animView,"color",new ColorEvaluator(),"#0000FF","#FF0000");
        mAnimator.setDuration(5000);
        mAnimator.start();

        ObjectAnimator animator = ObjectAnimator.ofFloat(mTextView,"translationX",200);
        animator.setDuration(2000);
        animator.start();

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f,100f);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                    Float f = (Float) animation.getAnimatedValue();
                Log.d("TAG","current value is "+f);
            }
        });
        valueAnimator.start();
        //ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f,5f,3f,10f);

        //组合动画
        ObjectAnimator moveIn = ObjectAnimator.ofFloat(textView,"translationX",-500f,0f);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(textView,"rotation",0f,360f);
        ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(textView,"alpha",1f,0f,1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(rotate).with(fadeInOut).after(moveIn);
        animatorSet.setDuration(5000);
        animatorSet.start();
    }
}
