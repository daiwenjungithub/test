package com.example.mrs.t;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RotateDrawable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.AccelerateDecelerateInterpolator;



public class ClearAllEditText extends AppCompatEditText
{
    private static final int DRAWABLE_LEFT = 0;
    private static final int DRAWABLE_TOP = 1;
    private static final int DRAWABLE_RIGHT = 2;
    private static final int DRAWABLE_BOTTOM = 3;
    private Drawable mClearDrawable;

    public ClearAllEditText(Context context) {
        super(context);
        init();
    }

    public ClearAllEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClearAllEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mClearDrawable = getResources().getDrawable(R.mipmap.delete2);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        setClearIconVisible(hasFocus() && text.length() > 0);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        setClearIconVisible(focused && length() > 0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                Drawable drawable = getCompoundDrawables()[DRAWABLE_RIGHT];
                if (drawable != null && event.getX() <= (getWidth() - getPaddingRight())
                        && event.getX() >= (getWidth() - getPaddingRight() - drawable.getBounds().width())) {
                    setText("");
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private void setClearIconVisible(boolean visible) {
        setCompoundDrawablesWithIntrinsicBounds(getCompoundDrawables()[DRAWABLE_LEFT], getCompoundDrawables()[DRAWABLE_TOP],
                visible ? mClearDrawable : null, getCompoundDrawables()[DRAWABLE_BOTTOM]);
    }





















//
//
//
//    private RotateDrawable drawableRotate;
//    //从无到有
//    private ValueAnimator alphaAnimator = ValueAnimator.ofInt(0, 255);//透明度变化
//    private ValueAnimator rotateAnimator = ValueAnimator.ofInt(0, 10000);//旋转角度
//    //从有到无
//    private ValueAnimator alphaAnimator2 = ValueAnimator.ofInt(255, 0);//通明度变化
//    private ValueAnimator rotateAnimator2 = ValueAnimator.ofInt(10000, 0);//旋转角度
//
//    public ClearAllEditText(Context context) {
//        super(context);
//    }
//
//    public ClearAllEditText(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public ClearAllEditText(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//
//        setIconVisible(false, getCompoundDrawables());
//    }
//
//
//    /**
//     * 设置右侧删除图标
//     *
//     * @param isShow 判断是否显示删除图标 true显示
//     * @param compoundDrawables
//     */
//    private void setIconVisible(boolean isShow, Drawable[] compoundDrawables) {
//        if (isShow) {
//            //左上右下
//            //若同时xml和java代码中都设置了，则以java中设置为准
//            setCompoundDrawablesWithIntrinsicBounds(compoundDrawables[0], compoundDrawables[1],
//                    getResources().getDrawable(R.mipmap.delete), compoundDrawables[3]);
//            //getCompoundDrawables()返回的是一个drawable数组，长度是4,对应的图标位置是左上右下，
//            // 即使你没有设置任何drawable，这时的四个值都为null。
//            drawableRotate = (RotateDrawable) getCompoundDrawables()[2];
//        } else {
//            setCompoundDrawablesWithIntrinsicBounds(compoundDrawables[0], compoundDrawables[1], null, compoundDrawables[3]);
//        }
//    }
//
//    /**
//     * 当内容发生变化时
//     *
//     * @param text
//     * @param start
//     * @param lengthBefore
//     * @param lengthAfter
//     */
//    @Override
//    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
//        super.onTextChanged(text, start, lengthBefore, lengthAfter);
//        if (text.length() == 0 && lengthBefore > 0) {
//            //从有文字删除到无文字的时候
//            startAnimatorSetResver();
//            setAnimator(alphaAnimator2, rotateAnimator2);
//            return;
//        }
//        if (start == 0 && text.length() > 0) {
//            //从无文字到有文字
//            setIconVisible(true, getCompoundDrawables());
//            setAnimator(alphaAnimator, rotateAnimator);
//            startAnimatorSet();
//        }
//    }
//
//    /**
//     * 从有文字删除到无文字的时候
//     */
//    private void startAnimatorSetResver() {
//        AnimatorSet setVisible = new AnimatorSet();
//        setVisible.playTogether(alphaAnimator2, rotateAnimator2);
//        setVisible.start();
//        setVisible.addListener(new Animator.AnimatorListener() {
//            //开始
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//            }
//
//            //结束
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                setIconVisible(false, getCompoundDrawables());
//            }
//
//            //取消
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            //重复
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//
//        });
//
//
//    }
//
//    /**
//     * 将透明度 旋转角度 放在动画集合
//     * 同时启动两个动画
//     */
//    private void startAnimatorSet() {
//        AnimatorSet setVisible = new AnimatorSet();
//        setVisible.playTogether(alphaAnimator, rotateAnimator);
//        setVisible.start();
//    }
//
//    /**
//     * 动画效果
//     *
//     * @param alphaAnimator  透明度变化
//     * @param rotateAnimator 旋转角度变化
//     */
//    private void setAnimator(ValueAnimator alphaAnimator, ValueAnimator rotateAnimator) {
//        //设置透明度变化
//        alphaAnimator.setDuration(1000);
//        alphaAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
//        alphaAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                //setAlpha(),这个alpha就是透明度，范围是0-255。
//                drawableRotate.setAlpha((Integer) animation.getAnimatedValue());
//            }
//        });
//
//        //设置旋转角度变化
//        rotateAnimator.setDuration(1000);
//        rotateAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
//        rotateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                //setLevel(),这个level就是设置的旋转角度，范围是1-10000
//                // 假如你是用的是ScaleDrawable，这个level控制就是你的图片的大小
//                drawableRotate.setLevel((Integer) animation.getAnimatedValue());
//            }
//        });
//    }
//
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_UP:
//                //在手指离开屏幕时是否正在图片上的判断，然后将内容设置为空
//                //判断手指离开屏幕的位置的方法是这样的:
//                // api中有这样的方法：getWidth返回的是控件的宽度，
//                // getTotalPadingRight返回的是空间右边的padding，
//                // 包含了drawable，getPaddingRight返回的是view右边的padding，
//                // 要是包含滚动条，滚动条的宽度也在pading内。
//                //这里方便的话，自己找张纸按照这个思路画个草图就会一目了然 根据什么判断删除按钮的范围的
//                if (getCompoundDrawables()[2] != null) {
//                    if (getWidth() - getTotalPaddingRight() < event.getX() &&
//                            getWidth() - getPaddingRight() > event.getX()) {
//                        this.setText("");
//                        Log.i("点到了删除图片", "删除图标");
//
//                    }
//                }
//                break;
//            default:
//                break;
//        }
//        return super.onTouchEvent(event);
//    }

}
