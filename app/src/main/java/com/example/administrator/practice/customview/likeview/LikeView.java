package com.example.administrator.practice.customview.likeview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import com.example.administrator.practice.R;

//点赞 view ：缩小放大切换状态
public class LikeView extends View {
    //点赞前图片
    private Bitmap beforeBitmap;
    //点赞后图片
    private Bitmap afterBitmap;

    //图片资源
    private int beforeRes;
    private int afterRes;

    private Context mContext;

    //view 大小
    private int mWidth;
    private int mHeight;

    private Paint mPaint;
    //是否为第一次加载
    private boolean isFirstLoading = true;
    //View 状态
    private boolean isLiked = false;
    //绘制区域
    private RectF rectF = new RectF();

    public LikeView(Context context) {
        this(context,null);
    }

    public LikeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LikeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    //初始化 /sdcard/wan-debug.apk
    private void init(Context context,AttributeSet attributeSet){
        mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.LikeView);
        beforeRes = typedArray.getResourceId(R.styleable.LikeView_bitmapResBefore,R.mipmap.ic_launcher_round);
        afterRes  = typedArray.getResourceId(R.styleable.LikeView_bitmapResAfter,R.mipmap.ic_launcher_round);
        typedArray.recycle();
        mPaint = new Paint();
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inScaled=false;
        beforeBitmap = BitmapFactory.decodeResource(context.getResources(),beforeRes,options);
        afterBitmap = BitmapFactory.decodeResource(context.getResources(),afterRes,options);
        //设置点击事件
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                beginAnim();
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        //处理 wrap_content 情况
        if(getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT && getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT){
            mWidth = beforeBitmap.getWidth();
            mHeight = beforeBitmap.getHeight();
        }else if(getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT){
            mWidth = beforeBitmap.getWidth();
        }else if (getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT){
            mHeight = beforeBitmap.getHeight();
        }
        setMeasuredDimension(mWidth,mHeight);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        rectF.left = 0;
        rectF.top = 0;
        rectF.right = mWidth;
        rectF.bottom = mHeight;
        if(!isLiked){
            canvas.drawBitmap(beforeBitmap,null,rectF,mPaint);
        }else {
            canvas.drawBitmap(afterBitmap,null,rectF,mPaint);
        }
        if(!isFirstLoading){
            loadAnim();
        }else {
            //第一次加载不需要动画
            isFirstLoading = false;
        }
    }

    //切换动画（放大）
    private void loadAnim() {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(this,"scaleX",0f,1f);
        animatorX.setDuration(700);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(this,"scaleY",0f,1f);
        animatorY.setDuration(700);
        animatorX.setInterpolator(new OvershootInterpolator());
        animatorY.setInterpolator(new OvershootInterpolator());
        animatorX.start();
        animatorY.start();
    }

    //切换动画（缩小）
    private void beginAnim() {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(this,"scaleX",1f,0f);
        animatorX.setDuration(500);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(this,"scaleY",1f,0f);
        animatorY.setDuration(500);
        animatorX.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                isLiked = !isLiked;
                postInvalidate();
            }
        });
        animatorX.start();
        animatorY.start();
    }

    public boolean isLiked() {
        return isLiked;
    }

    //外部可通过设置改变 view 的状态（如点击后网络请求失败，需恢复原来状态）
    public void setLiked(boolean liked) {
        isLiked = liked;
        isFirstLoading = true;
        postInvalidate();
    }

}
