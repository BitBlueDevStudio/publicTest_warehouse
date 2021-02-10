package com.test.warehouse.view

import android.graphics.Canvas
import android.view.SurfaceHolder


class DrawingThread(private val drawingSurface: DrawingSurface, private val surfaceHolder: SurfaceHolder) : Thread() {
    private var running = false
    override fun run() {
        var startTime = System.nanoTime()
        while (running) {
            var canvas: Canvas? = null
            try {
                canvas = surfaceHolder.lockCanvas()

                synchronized(canvas) {
                    drawingSurface.update()
                    drawingSurface.draw(canvas)
                }
            } catch (e: Exception) {

            } finally {
                if (canvas != null) {

                    surfaceHolder.unlockCanvasAndPost(canvas)
                }
            }
            val now = System.nanoTime()

            var waitTime = (now - startTime) / 1000000
            if (waitTime < 10) {
                waitTime = 10 // Millisecond.
            }
            print(" Wait Time=$waitTime")
            try {

                sleep(waitTime)
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