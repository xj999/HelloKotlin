package com.example.luxj.hellokotlin.image.glide

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapResource


/**
 * @date create 2017/6/1
 * @author Luxj
 * @description
 * @version
 */
class RoundedCornersTransformation : Transformation<Bitmap> {

    enum class CornerType {
        ALL,
        TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT,
        TOP, BOTTOM, LEFT, RIGHT,
        OTHER_TOP_LEFT, OTHER_TOP_RIGHT, OTHER_BOTTOM_LEFT, OTHER_BOTTOM_RIGHT,
        DIAGONAL_FROM_TOP_LEFT, DIAGONAL_FROM_TOP_RIGHT
    }

    var mBitmapPool: BitmapPool? = null
    var mRadius: Float = 0.toFloat()
    var mDiameter: Float = 0.toFloat()
    var mMargin: Float = 0.toFloat()
    var mCornerType: CornerType? = null
    var borderWidth: Float = 0.toFloat()
    var borderColor: Int = 0
    var mBorderPaint: Paint? = null


    constructor(context: Context, radius: Int, margin: Int,
                cornerType: CornerType, width: Float, color: Int) : this(Glide.get(context).bitmapPool, radius, margin, cornerType, width, color)

    constructor(pool: BitmapPool, radius: Int, margin: Int,
                cornerType: CornerType, width: Float, color: Int) {
        mBitmapPool = pool
        mRadius = radius.toFloat()
        mDiameter = mRadius * 2
        mMargin = margin.toFloat()
        mCornerType = cornerType
        borderColor = color
        borderWidth = width
        borderWidth = Resources.getSystem().displayMetrics.density * width

        if (color != 0) {
            mBorderPaint = Paint()
            mBorderPaint!!.isDither = true
            mBorderPaint!!.isAntiAlias = true
            mBorderPaint!!.color = borderColor
            if (width == 0f) {
                borderWidth = Resources.getSystem().displayMetrics.density * 2
            }
        }


    }

    constructor(pool: BitmapPool, radius: Int, margin: Int,
                cornerType: CornerType) {
        mBitmapPool = pool
        mRadius = radius.toFloat()
        mDiameter = mRadius * 2
        mMargin = margin.toFloat()
        mCornerType = cornerType
    }

    override fun transform(resource: Resource<Bitmap>, outWidth: Int, outHeight: Int): Resource<Bitmap> {
        val source = resource.get()

        val width = source.width
        val height = source.height

        var bitmap: Bitmap? = mBitmapPool!!.get(width, height, Bitmap.Config.ARGB_8888)
        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        }

        val canvas = Canvas(bitmap)
        val paint = Paint()
        paint.isAntiAlias = true
        if (mBorderPaint != null) {
            val rectf = RectF(0f, 0f, width.toFloat(), height.toFloat())
            canvas.drawRoundRect(rectf, mRadius, mRadius, mBorderPaint)
        }
        paint.shader = BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        drawRoundRect(canvas, paint, width.toFloat(), height.toFloat())
        return BitmapResource.obtain(bitmap, mBitmapPool)
    }

    private fun drawRoundRect(canvas: Canvas, paint: Paint, width: Float, height: Float) {
        val right = width - mMargin
        val bottom = height - mMargin

        when (mCornerType) {
            RoundedCornersTransformation.CornerType.ALL -> canvas.drawRoundRect(RectF(borderWidth, borderWidth, width - borderWidth, height - borderWidth), mRadius, mRadius, paint)
            RoundedCornersTransformation.CornerType.TOP_LEFT -> drawTopLeftRoundRect(canvas, paint, right, bottom)
            RoundedCornersTransformation.CornerType.TOP_RIGHT -> drawTopRightRoundRect(canvas, paint, right, bottom)
            RoundedCornersTransformation.CornerType.BOTTOM_LEFT -> drawBottomLeftRoundRect(canvas, paint, right, bottom)
            RoundedCornersTransformation.CornerType.BOTTOM_RIGHT -> drawBottomRightRoundRect(canvas, paint, right, bottom)
            RoundedCornersTransformation.CornerType.TOP -> drawTopRoundRect(canvas, paint, right, bottom)
            RoundedCornersTransformation.CornerType.BOTTOM -> drawBottomRoundRect(canvas, paint, right, bottom)
            RoundedCornersTransformation.CornerType.LEFT -> drawLeftRoundRect(canvas, paint, right, bottom)
            RoundedCornersTransformation.CornerType.RIGHT -> drawRightRoundRect(canvas, paint, right, bottom)
            RoundedCornersTransformation.CornerType.OTHER_TOP_LEFT -> drawOtherTopLeftRoundRect(canvas, paint, right, bottom)
            RoundedCornersTransformation.CornerType.OTHER_TOP_RIGHT -> drawOtherTopRightRoundRect(canvas, paint, right, bottom)
            RoundedCornersTransformation.CornerType.OTHER_BOTTOM_LEFT -> drawOtherBottomLeftRoundRect(canvas, paint, right, bottom)
            RoundedCornersTransformation.CornerType.OTHER_BOTTOM_RIGHT -> drawOtherBottomRightRoundRect(canvas, paint, right, bottom)
            RoundedCornersTransformation.CornerType.DIAGONAL_FROM_TOP_LEFT -> drawDiagonalFromTopLeftRoundRect(canvas, paint, right, bottom)
            RoundedCornersTransformation.CornerType.DIAGONAL_FROM_TOP_RIGHT -> drawDiagonalFromTopRightRoundRect(canvas, paint, right, bottom)
            else -> canvas.drawRoundRect(RectF(mMargin, mMargin, right, bottom), mRadius, mRadius, paint)
        }
    }

    private fun drawTopLeftRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
        canvas.drawRoundRect(RectF(mMargin, mMargin, (mMargin + mDiameter), (mMargin + mDiameter)),
                mRadius, mRadius, paint)
        canvas.drawRect(RectF(mMargin, (mMargin + mRadius), (mMargin + mRadius), bottom), paint)
        canvas.drawRect(RectF((mMargin + mRadius), mMargin, right, bottom), paint)
    }

    private fun drawTopRightRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
        canvas.drawRoundRect(RectF(right - mDiameter, mMargin, right, (mMargin + mDiameter)), mRadius,
                mRadius, paint)
        canvas.drawRect(RectF(mMargin, mMargin, right - mRadius, bottom), paint)
        canvas.drawRect(RectF(right - mRadius, (mMargin + mRadius), right, bottom), paint)
    }

    private fun drawBottomLeftRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
        canvas.drawRoundRect(RectF(mMargin, bottom - mDiameter, (mMargin + mDiameter), bottom),
                mRadius, mRadius, paint)
        canvas.drawRect(RectF(mMargin, mMargin, (mMargin + mDiameter), bottom - mRadius), paint)
        canvas.drawRect(RectF((mMargin + mRadius), mMargin, right, bottom), paint)
    }

    private fun drawBottomRightRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
        canvas.drawRoundRect(RectF(right - mDiameter, bottom - mDiameter, right, bottom), mRadius,
                mRadius, paint)
        canvas.drawRect(RectF(mMargin, mMargin, right - mRadius, bottom), paint)
        canvas.drawRect(RectF(right - mRadius, mMargin, right, bottom - mRadius), paint)
    }

    private fun drawTopRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
        canvas.drawRoundRect(RectF(mMargin, mMargin, right, (mMargin + mDiameter)), mRadius, mRadius,
                paint)
        canvas.drawRect(RectF(mMargin, (mMargin + mRadius), right, bottom), paint)
    }

    private fun drawBottomRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
        canvas.drawRoundRect(RectF(mMargin, bottom - mDiameter, right, bottom), mRadius, mRadius,
                paint)
        canvas.drawRect(RectF(mMargin, mMargin, right, bottom - mRadius), paint)
    }

    private fun drawLeftRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
        canvas.drawRoundRect(RectF(mMargin, mMargin, (mMargin + mDiameter), bottom), mRadius, mRadius,
                paint)
        canvas.drawRect(RectF((mMargin + mRadius), mMargin, right, bottom), paint)
    }

    private fun drawRightRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
        canvas.drawRoundRect(RectF(right - mDiameter, mMargin, right, bottom), mRadius, mRadius,
                paint)
        canvas.drawRect(RectF(mMargin, mMargin, right - mRadius, bottom), paint)
    }

    private fun drawOtherTopLeftRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
        canvas.drawRoundRect(RectF(mMargin, bottom - mDiameter, right, bottom), mRadius, mRadius,
                paint)
        canvas.drawRoundRect(RectF(right - mDiameter, mMargin, right, bottom), mRadius, mRadius,
                paint)
        canvas.drawRect(RectF(mMargin, mMargin, right - mRadius, bottom - mRadius), paint)
    }

    private fun drawOtherTopRightRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
        canvas.drawRoundRect(RectF(mMargin, mMargin, (mMargin + mDiameter), bottom), mRadius, mRadius,
                paint)
        canvas.drawRoundRect(RectF(mMargin, bottom - mDiameter, right, bottom), mRadius, mRadius,
                paint)
        canvas.drawRect(RectF((mMargin + mRadius), mMargin, right, bottom - mRadius), paint)
    }

    private fun drawOtherBottomLeftRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
        canvas.drawRoundRect(RectF(mMargin, mMargin, right, (mMargin + mDiameter)), mRadius, mRadius,
                paint)
        canvas.drawRoundRect(RectF(right - mDiameter, mMargin, right, bottom), mRadius, mRadius,
                paint)
        canvas.drawRect(RectF(mMargin, (mMargin + mRadius), right - mRadius, bottom), paint)
    }

    private fun drawOtherBottomRightRoundRect(canvas: Canvas, paint: Paint, right: Float,
                                              bottom: Float) {
        canvas.drawRoundRect(RectF(mMargin, mMargin, right, (mMargin + mDiameter)), mRadius, mRadius,
                paint)
        canvas.drawRoundRect(RectF(mMargin, mMargin, (mMargin + mDiameter), bottom), mRadius, mRadius,
                paint)
        canvas.drawRect(RectF((mMargin + mRadius), (mMargin + mRadius), right, bottom), paint)
    }

    private fun drawDiagonalFromTopLeftRoundRect(canvas: Canvas, paint: Paint, right: Float,
                                                 bottom: Float) {
        canvas.drawRoundRect(RectF(mMargin, mMargin, (mMargin + mDiameter), (mMargin + mDiameter)),
                mRadius, mRadius, paint)
        canvas.drawRoundRect(RectF(right - mDiameter, bottom - mDiameter, right, bottom), mRadius,
                mRadius, paint)
        canvas.drawRect(RectF(mMargin, (mMargin + mRadius), right - mDiameter, bottom), paint)
        canvas.drawRect(RectF((mMargin + mDiameter), mMargin, right, bottom - mRadius), paint)
    }

    private fun drawDiagonalFromTopRightRoundRect(canvas: Canvas, paint: Paint, right: Float,
                                                  bottom: Float) {
        canvas.drawRoundRect(RectF(right - mDiameter, mMargin, right, (mMargin + mDiameter)), mRadius,
                mRadius, paint)
        canvas.drawRoundRect(RectF(mMargin, bottom - mDiameter, (mMargin + mDiameter), bottom),
                mRadius, mRadius, paint)
        canvas.drawRect(RectF(mMargin, mMargin, right - mRadius, bottom - mRadius), paint)
        canvas.drawRect(RectF((mMargin + mRadius), (mMargin + mRadius), right, bottom), paint)
    }

    override fun getId(): String {
        return "RoundedTransformation(radius=$mRadius, margin=$mMargin, diameter="
        (+mDiameter).toString() + ", cornerType=" + mCornerType!!.name + ")"
    }

}