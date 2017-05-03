package com.dysen.lib.views.animator;

import android.animation.ObjectAnimator;
import android.view.animation.TranslateAnimation;

/**
 * Created by dy on 2017/1/10.
 * Animator 工具类
 */

public class AnimatorUtils {

    /**
     * 属性动画
     * @param target 动画目标
     * @param propertyName 动画方式
     * @param values    动画位移的位置
     * @return
     */
    public static ObjectAnimator getAnimator(Object target, String propertyName, long duration, float... values) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, propertyName, values);
        animator.setDuration(duration);
//        animator.start();
        return animator;
    }

    /**
     * animation 动画 只是纯粹的重绘动画，
     * 并没有改变view的位置
     * @return
     */
    public static TranslateAnimation getAnimation(float fromX, float toX, float fromY, float toY, long duration) {
        TranslateAnimation animation = new TranslateAnimation(fromX, toX, fromY, toY);
        animation.setDuration(duration);
        animation.setFillAfter(true);//停止在运动结束位置

        return animation;
    }
}
