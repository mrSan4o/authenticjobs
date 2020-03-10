package com.san4o.just4fun.authenticjobs.ui.view

import android.animation.ArgbEvaluator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class CustomLoaderView : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private fun halfWidth() = width / 2f
    private fun halfHeight() = height / 2f
    private val TAG = "Loader"
    private val rectSizeLength = 50
    private val offsetHor = 20
    var sizeLength = 0f
    var offsetX = 0f

    private val paint = Paint()
        .apply {
            color = Color.BLUE
        }
    private val startColor = Color.BLUE
    private val endColor = Color.RED

    private var animColor = startColor
    private var rotate = 0f
    private var animAlpha = 0
    private var moveOffset = 0f

    private val animationDuration = 2000L

    private val rect = RectF()

    private var startMoveX = offsetHor.toFloat()
    private var endMoveX = offsetHor.toFloat()

    private val matrixRect = Matrix()

    private fun startAnimation() {
        val offset = viewWidth(offsetHor)
        val rectSize = viewWidth(rectSizeLength)
        val moveStart = 0f
        val moveEnd = height.toFloat() + rectSize

        val moveProperty = "move"
        val rotateProperty = "rotate"
        val colorProperty = "color"
        val alphaProperty = "alpha"

        val valueAnimator = ValueAnimator().apply {
            setValues(
                PropertyValuesHolder.ofFloat(moveProperty, moveStart, moveEnd),
                PropertyValuesHolder.ofFloat(rotateProperty, 0f, 360f * 2),
                PropertyValuesHolder.ofObject(
                    colorProperty, ArgbEvaluator(),
                    startColor,
                    endColor
                ),
                PropertyValuesHolder.ofFloat(alphaProperty, 40f, 100f, 40f)
            )
            duration = animationDuration
            addUpdateListener {
                moveOffset = getAnimatedValue(moveProperty) as Float
                rotate = getAnimatedValue(rotateProperty) as Float
                animAlpha = (getAnimatedValue(alphaProperty) as Float).toInt()
                animColor = getAnimatedValue(colorProperty) as Int

                invalidate()
            }

            repeatMode = ValueAnimator.REVERSE
            repeatCount = -1

            //interpolator = BounceInterpolator()
        }

        valueAnimator.start()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        val centerX = halfWidth()
        val centerY = halfHeight()

        sizeLength = viewWidth(rectSizeLength)
        offsetX = viewWidth(offsetHor)
        val halfWidth = sizeLength / 2

        startMoveX = offsetX
        endMoveX = height - offsetX

        rect.set(
            0f + offsetX,
            centerY - halfWidth,
            sizeLength + offsetX,
            centerY + halfWidth
        )

        startAnimation()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) return
        rect.left = 0f + offsetX + moveOffset
        rect.right = sizeLength + offsetX + moveOffset

        paint.color = animColor
        paint.alpha = animAlpha

        canvas.rotate(rotate, rect.centerX(), rect.centerY())
        canvas.drawRect(rect, paint)
//        canvas.translate(viewWidth(moveOffset.toInt()) + viewWidth(10), halfHeight())

    }
}

private fun View.viewWidth(w: Int) = resources.displayMetrics.density * w