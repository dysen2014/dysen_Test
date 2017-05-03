package com.dysen.lib.views.textView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by dy on 2016-12-07.
 */

public class MarqueeText extends TextView {

    public MarqueeText(Context context) {
        super(context);
    }

    public MarqueeText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueeText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;//获取到焦点
    }
}
