package com.wall.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import com.wall.R

/**
 * Created by wall on 2017/8/9.
 */
class TimeLineView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mMarkPaint: Paint = Paint()
    private var mLinePaint: Paint = Paint()
    private var mMarkRadius: Float = 10F
    private var mStartLineSize: Int = 60
    private var mEndLineSize: Int = 60
    private var mLinePadding: Float = 10F
    private var mStrokeWidth: Float = 5F
    private var mColor: Int = Color.BLUE
    private var mSecondColor: Int = Color.LTGRAY

    private var isMarked: Boolean = false
    private var isHideStartLine: Boolean = false
    private var isHideEndLine: Boolean = false
    private var relyResId: Int? = null

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.TimeLineView)
        relyResId = ta.getResourceId(R.styleable.TimeLineView_rely, 0)
        mColor = ta.getColor(R.styleable.TimeLineView_color, Color.BLUE)
        mSecondColor = ta.getColor(R.styleable.TimeLineView_second_color, Color.LTGRAY)
        isMarked = ta.getBoolean(R.styleable.TimeLineView_is_marked, false)
        mMarkRadius = ta.getDimension(R.styleable.TimeLineView_mark_radius, 10F)
        mStrokeWidth = ta.getDimension(R.styleable.TimeLineView_line_width, 5F)
        mLinePadding = ta.getDimension(R.styleable.TimeLineView_line_padding, 10F)
        ta.recycle()

        mMarkPaint.isAntiAlias = true
        mMarkPaint.color = mColor
        mMarkPaint.style = Paint.Style.FILL_AND_STROKE
        mMarkPaint.strokeWidth = mStrokeWidth

        mLinePaint.isAntiAlias = true
        mLinePaint.color = mSecondColor
        mLinePaint.style = Paint.Style.FILL_AND_STROKE
        mLinePaint.strokeWidth = mStrokeWidth

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //测量出被依赖者在父容器中的位置
        if (relyResId != 0 && parent != null) {
            val mParent = parent as ViewGroup
            val relyView = mParent.findViewById<View>(relyResId!!)
            relyView?.let {
                mStartLineSize = (relyView.y + relyView.measuredHeight / 2 - mMarkRadius).toInt()
                mEndLineSize = (mParent.measuredHeight - mStartLineSize + mMarkRadius).toInt()
            }
        }

        val w = resolveSize((mMarkRadius * 2 + mStrokeWidth).toInt() + paddingLeft + paddingRight, widthMeasureSpec)
        val h = resolveSize(mStartLineSize + mEndLineSize + (mMarkRadius * 2).toInt(), heightMeasureSpec)

        setMeasuredDimension(w, h)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawMark(canvas)
        drawStarLine(canvas)
        drawEndLine(canvas)
    }

    /**
     * 画头线
     */
    private fun drawStarLine(canvas: Canvas?) {
        if (!isHideStartLine) {
            canvas?.drawLine((width / 2).toFloat(), 0F, (width / 2).toFloat(), (mStartLineSize - mLinePadding).toFloat(), mLinePaint)
        }
    }

    /**
     * 画尾线
     */
    private fun drawEndLine(canvas: Canvas?) {
        if (!isHideEndLine) {
            canvas?.drawLine((width / 2).toFloat(), mStartLineSize + mMarkRadius * 2 + mLinePadding, (width / 2).toFloat(), mStartLineSize + mMarkRadius * 2 + mEndLineSize, mLinePaint)
        }
    }

    /**
     * 画标记
     */
    private fun drawMark(canvas: Canvas?) {
        if (isMarked) {
            mMarkPaint.color = mColor
        } else {
            mMarkPaint.color = mSecondColor
        }
        canvas?.drawCircle((width / 2).toFloat(), mStartLineSize + mMarkRadius, mMarkRadius, mMarkPaint)
    }

    /**
     * 是否标记
     */
    public fun setMarked(isMarked: Boolean) {
        this.isMarked = isMarked
        invalidate()
    }

    /**
     * 隐藏头线
     */
    public fun hideStartLine(hide: Boolean) {
        isHideStartLine = hide
        invalidate()
    }

    /**
     * 隐藏尾线
     */
    public fun hideEndLine(hide: Boolean) {
        isHideEndLine = hide
        invalidate()
    }
}