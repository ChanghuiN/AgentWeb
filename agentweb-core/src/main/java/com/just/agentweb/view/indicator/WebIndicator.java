/*
 * Copyright (C)  Justson(https://github.com/Justson/AgentWeb)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.just.agentweb.view.indicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;

import com.just.agentweb.AgentWebUtils;
import com.just.agentweb.LogUtils;

/**
 * @author cenxiaozhong
 * @since 1.0.0
 * <p>
 * 进度条
 */
public class WebIndicator extends BaseIndicatorView implements BaseIndicatorSpec {

    private ProgressBar mProgressBar;

    /**
     * 默认的高度
     */
    public static int WEB_INDICATOR_DEFAULT_HEIGHT = 3;

    public WebIndicator(Context context) {
        this(context, null);
    }

    public WebIndicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WebIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

        mProgressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);

        ShapeDrawable shapeDrawable = new ShapeDrawable();
        shapeDrawable.getPaint().setColor(Color.parseColor("#1aad19"));
        ClipDrawable clipDrawable = new ClipDrawable(shapeDrawable, Gravity.LEFT, ClipDrawable.HORIZONTAL);
//        mProgressBar.setIndeterminate(true);
        mProgressBar.setIndeterminateDrawable(clipDrawable);

        WEB_INDICATOR_DEFAULT_HEIGHT = AgentWebUtils.dp2px(context, 3);
        this.addView(mProgressBar, offerLayoutParams());
    }

    public void setColor(int color) {
        ShapeDrawable shapeDrawable = new ShapeDrawable();
        shapeDrawable.getPaint().setColor(color);
        ClipDrawable clipDrawable = new ClipDrawable(shapeDrawable, Gravity.LEFT, ClipDrawable.HORIZONTAL);
//        mProgressBar.setIndeterminate(true);
        mProgressBar.setIndeterminateDrawable(clipDrawable);
    }

    public void setColor(String color) {
        this.setColor(Color.parseColor(color));
    }

    @Override
    public void show() {
        this.setVisibility(View.VISIBLE);
    }

    @Override
    public void hide() {
        setVisibility(View.GONE);
    }

    @Override
    public void reset() {
        mProgressBar.setProgress(0);
    }

    @Override
    public void setProgress(int newProgress) {
        mProgressBar.setProgress(newProgress);
    }

    @Override
    public LayoutParams offerLayoutParams() {
        return new LayoutParams(-1, WEB_INDICATOR_DEFAULT_HEIGHT);
    }
}
