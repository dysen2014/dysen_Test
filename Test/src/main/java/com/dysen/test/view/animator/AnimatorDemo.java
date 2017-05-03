package com.dysen.test.view.animator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.dysen.lib.base.BaseActivity;
import com.dysen.test.R;

import java.util.ArrayList;
import java.util.List;

public class AnimatorDemo extends BaseActivity{

    Button btn;
    ImageView iv;

    int[] resIds = new int[]{R.id.iv_0_animator, R.id.iv_1_animator, R.id.iv_2_animator, R.id.iv_3_animator, R.id.iv_4_animator};
    List<ImageView> imagviewList = new ArrayList<ImageView>();
    private boolean flagClose = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_demo);

        init();
        logic();
    }

    @Override
    public void doClick(View view) {
        super.doClick(view);

        switch (view.getId()){
            case R.id.iv_animator:
                showLong("imageview is click", 1);
                break;
            case R.id.btn_animator:
//                iv.startAnimation(AnimatorUtils.getAnimation(0f, 200f, 0f, 0f, 2000));
//                AnimatorUtils.getAnimator(iv, "rotation",2000, 0f, 270f).start();
                startAnimator();
                break;
            case R.id.iv_0_animator:
                if (flagClose){
                    openAnimator();
                }else {
                    closeAnimator();
                }
            break;
            case R.id.iv_1_animator:
                showShort("1", 1);
                break;
            case R.id.iv_2_animator:
                showShort("2", 1);
                break;
            case R.id.iv_3_animator:
                showShort("3", 1);
                break;
            case R.id.iv_4_animator:
                showShort("4", 1);
                break;
            case R.id.btn_value_animator:
                startValueAnimator(view);
                break;
            default:
                break;
        }
    }

    private void startValueAnimator2(View view) {

        final Button btn = (Button) view;

        ValueAnimator valueAnimator = ValueAnimator.ofObject(new TypeEvaluator<PointF>() {
            @Override//fraction 时间因子 (0---1)
            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
                btn.setText("fraction="+fraction+"\tstartValue"+startValue+"\nendValue"+endValue);
                return null;
            }
        });
        valueAnimator.setDuration(5000);
        valueAnimator.start();
    }

    private void startValueAnimator(View view) {

        final Button btn = (Button) view;

        ValueAnimator valueAnimator = ValueAnimator.ofInt(10, 0);
        valueAnimator.setDuration(10000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                btn.setText("剩余 "+animatedValue+"S");
            }
        });

        valueAnimator.start();
    }

    private void closeAnimator() {
        for (int i = 1; i <resIds.length ; i++) {
            ObjectAnimator animator = AnimatorUtils.getAnimator(imagviewList.get(i), "translationY", 500, i*150f, 0f);
            ObjectAnimator animator2 = AnimatorUtils.getAnimator(imagviewList.get(i), "translationX", 500, i*100f, 0f);
            ObjectAnimator animator3 = AnimatorUtils.getAnimator(imagviewList.get(i), "rotation", 500, 360f, 0f);
            animator.setStartDelay(i*300);
            animator.setInterpolator(new BounceInterpolator());
//            animator.start();
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(animator3, animator2, animator);
            animatorSet.start();
            flagClose = true;
            showShort("close", 1);
        }
    }

    private void openAnimator() {
        for (int i = 1; i <resIds.length ; i++) {
            ObjectAnimator animator = AnimatorUtils.getAnimator(imagviewList.get(resIds.length-i), "translationY", 500, 0f, (resIds.length-i)*150f);
            ObjectAnimator animator2 = AnimatorUtils.getAnimator(imagviewList.get(resIds.length-i), "translationX", 500, 0f, (resIds.length-i)*100f);
            ObjectAnimator animator3 = AnimatorUtils.getAnimator(imagviewList.get(i), "rotation", 500, 0f, 360f);
            animator.setStartDelay(i*300);
            animator.setInterpolator(new BounceInterpolator());
//            animator.start();
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(animator, animator2, animator3);
            animatorSet.start();
            flagClose = false;
            showShort("open", 1);
        }
    }

    /**
     * 启动动画（动画属性）
     * play 可根据 需求设定动画的执行顺序
     * playSequentially 按照给定的动画顺序执行
     * playTogether 同时执行给定的动画
     */
    public void startAnimator(){

        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.play(AnimatorUtils.getAnimator(iv, "translationX", 2000, 0f, 300f)).with(AnimatorUtils.getAnimator(iv, "translationY", 2000, 0f, 300f));
//        animatorSet.play(AnimatorUtils.getAnimator(iv, "rotation", 5000, 0f, 360f)).after(AnimatorUtils.getAnimator(iv, "translationX", 2000, 0f, 300f));
        animatorSet.playSequentially(AnimatorUtils.getAnimator(iv, "translationX", 2000, 0f, 300f), AnimatorUtils.getAnimator(iv, "translationY", 2000, 0f, 150f)
                , AnimatorUtils.getAnimator(iv, "rotation", 5000, 0f, 360f), AnimatorUtils.getAnimator(iv, "alpha", 2000, 1f, .5f),
                AnimatorUtils.getAnimator(iv, "alpha", 2000, .5f, 1f), AnimatorUtils.getAnimator(iv, "scale", 5000, 0.0f, 1.4f, 0.0f, 1.4f,
                        1f, 0.5f, 1f, 0.5f));
//        animatorSet.playTogether(AnimatorUtils.getAnimator(iv, "translationX", 2000, 0f, 300f), AnimatorUtils.getAnimator(iv, "translationY", 2000, 0f, 300f)
//        , AnimatorUtils.getAnimator(iv, "rotation", 5000, 0f, 360f));
        animatorSet.setStartDelay(300);
        animatorSet.setInterpolator(new BounceInterpolator());
        animatorSet.start();
    }

    @Override
    protected void logic() {
        super.logic();

        for (int i = 0; i < resIds.length; i++) {
            ImageView imageView = bindView(resIds[i]);
            imagviewList.add(imageView);
        }
    }

    @Override
    protected void init() {
        super.init();

        iv = bindView(R.id.iv_animator);
        btn = bindView(R.id.btn_animator);
    }
}
