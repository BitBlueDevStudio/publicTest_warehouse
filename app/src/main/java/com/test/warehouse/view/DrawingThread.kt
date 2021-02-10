package com.test.warehouse.view

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceHolder


class DrawingThread(drawingSurface: DrawingSurface, surfaceHolder: SurfaceHolder) : Thread() {
    private var running = false
    private val drawingSurface: DrawingSurface = drawingSurface
    private val surfaceHolder: SurfaceHolder = surfaceHolder
    override fun run() {
        var startTime = System.nanoTime()
        while (running) {
            var canvas: Canvas? = null
            try {
                // Get Canvas from Holder and lock it.
                canvas = surfaceHolder.lockCanvas()

                // Synchronized
                synchronized(canvas) {
                    drawingSurface.update()
                    drawingSurface.draw(canvas)
                }
            } catch (e: Exception) {
                // Do nothing.
            } finally {
                if (canvas != null) {
                    // Unlock Canvas.
                    surfaceHolder.unlockCanvasAndPost(canvas)
                }
            }
            val now = System.nanoTime()
            // Interval to redraw game
            // (Change nanoseconds to milliseconds)
            var waitTime = (now - startTime) / 1000000
            if (waitTime < 10) {
                waitTime = 10 // Millisecond.
            }
            print(" Wait Time=$waitTime")
            try {
                // Sleep.
                Thread.sleep(waitTime)
            } catch (e: InterruptedException) {
            }
            startTime = System.nanoTime()
            print(".")
        }
    }

    fun setRunning(running: Boolean) {
        this.running = running
    }

}