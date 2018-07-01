package com.cipolat.deliveryya.base.UI.CustomView.Loader;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.cipolat.deliveryya.base.R;

/**
 * Created by sebastian oan 04/05/17.
 */

public class AraryImageLoader extends android.support.v7.widget.AppCompatImageView {
    private AnimationDrawable frameAnimation;
    private int arraySource;

    public AraryImageLoader(Context context) {
        super(context);
    }

    public AraryImageLoader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setUI(attrs);
    }

    public AraryImageLoader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUI(attrs);
    }

    public void setUI(AttributeSet attrs) {
        if (attrs != null) {
            /***Atributos**/
            TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.array_image_loader);
            arraySource = array.getResourceId(R.styleable.array_image_loader_setSource, -1);
        }
    }

    public void startAnim() {
        if (arraySource > 0) {
            this.setBackgroundResource(arraySource);
            frameAnimation = (AnimationDrawable) getBackground();
            // Start the animation (looped playback by default).
            frameAnimation.start();
        }
    }

    public void stopAnim() {
        frameAnimation.stop();
    }
}
