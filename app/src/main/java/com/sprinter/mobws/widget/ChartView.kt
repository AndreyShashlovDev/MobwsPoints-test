package com.sprinter.mobws.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.sprinter.mobws.R
import com.sprinter.mobws.repositories.models.Point
import kotlin.math.abs
import kotlin.math.ceil

class ChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val padding = context.resources
        .getDimensionPixelSize(R.dimen.margin_padding_16dp)
        .toFloat()
    private val textSize = context.resources
        .getDimensionPixelSize(R.dimen.text_size_14dp)
        .toFloat()

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var originalPoints = emptyList<Point>()

    fun setPoints(points: List<Point>) {
        originalPoints = points
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawChart(canvas)
    }

    private fun drawChart(canvas: Canvas) {
        if (originalPoints.isEmpty()) {
            return
        }

        var maxY = originalPoints[0].y

        val sortedByX = originalPoints.sortedBy {
            maxY = ceil(abs(it.y).coerceAtLeast(maxY))
            it.x
        }

        val maxX = ceil(abs(sortedByX[0].x).coerceAtLeast(abs(sortedByX[sortedByX.size - 1].x)))
        val halfWidth = width / 2f
        val halfHeight = height / 2f

        drawVectors(canvas, maxX, maxY)

        val normalizedDots = sortedByX.map {
            it.copy(
                x = halfWidth + ((halfWidth - padding) / maxX * it.x),
                y = halfHeight + ((halfHeight - padding) / maxY * -it.y)
            )
        }.toMutableList()

        val path = Path()
        paint.color = Color.BLUE
        path.moveTo(normalizedDots[0].x, normalizedDots[0].y)

        for (i in 1..normalizedDots.size) {
            val p1 = normalizedDots[i - 1]
            canvas.drawCircle(p1.x, p1.y, 5f, paint)

            if (i < normalizedDots.size) {
                val p2 = normalizedDots[i]
                val x = (p2.x + p1.x) / 2
                path.cubicTo(x, p1.y, x, p2.y, p2.x, p2.y)
            }
        }

        canvas.drawPath(path, paint)
    }

    private fun drawVectors(canvas: Canvas, maxX: Float, maxY: Float) {
        paint.strokeWidth = 3f
        paint.color = Color.BLUE

        val halfWidth = width / 2f
        val halfHeight = height / 2f
        val vectorWidth = width - padding
        val vectorHeight = height - padding

        paint.style = Paint.Style.STROKE

        paint.color = Color.GRAY

        canvas.drawRect(Rect(0, 0, width, height), paint)
        canvas.drawLine(halfWidth, padding, halfWidth, vectorHeight, paint)
        canvas.drawLine(padding, halfHeight, vectorWidth, halfHeight, paint)

        paint.textSize = textSize

        val maxXBounds = getTextBounds(paint, (maxX).toString())
        canvas.drawText((-maxX).toString(), padding, halfHeight, paint)
        canvas.drawText((maxX).toString(), vectorWidth - maxXBounds.width(), halfHeight, paint)

        val maxYBounds = getTextBounds(paint, (maxY).toString())
        canvas.drawText((maxY).toString(), halfWidth, padding + maxYBounds.height(), paint)
        canvas.drawText((-maxY).toString(), halfWidth, vectorHeight, paint)
    }

    private fun getTextBounds(paint: Paint, text: String): Rect {
        val bounds = Rect()
        paint.getTextBounds(text, 0, text.length, bounds)

        return bounds
    }
}
