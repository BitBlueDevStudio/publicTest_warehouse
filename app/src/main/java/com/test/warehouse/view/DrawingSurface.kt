package com.test.warehouse.view
import android.content.Context
import android.graphics.Canvas
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.test.warehouse.model.FieldInteractor
import com.test.warehouse.presenter.BaseObjectPresenter
import com.test.warehouse.presenter.FieldPresenter

class DrawingSurface(context: Context?) : SurfaceView(context),
    SurfaceHolder.Callback, IDrawingInterface {
    private var drawingThread: DrawingThread? = null
    private lateinit var fieldPresenter:FieldPresenter

    override fun update() {
        fieldPresenter.updateObjects()
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        fieldPresenter.onDraw(canvas)
    }

    override fun drawObjects(canvas: Canvas, objects: MutableList<BaseObjectPresenter>) {
        for (item in objects) {
            canvas.drawBitmap(item.image, item.x, item.y, null)
            fieldPresenter.lastDrawNanoTime = System.nanoTime()
        }
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        fieldPresenter=FieldPresenter(this.context, FieldInteractor(getWidth(),getHeight()),getWidth(),getHeight(),this)
        drawingThread = DrawingThread(this, holder)
        drawingThread!!.setRunning(true)
        drawingThread!!.start()
    }

    override fun getSurfaceContext(): Context {
        return this.context
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        var retry = true
        while (retry) {
            try {
                drawingThread?.setRunning(false)
                drawingThread?.join()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            retry = true
        }
    }

    init {

        this.isFocusable = true
        this.holder.addCallback(this)
    }
}

interface IDrawingInterface {

    fun drawObjects(canvas: Canvas, objects: MutableList<BaseObjectPresenter> = mutableListOf())

    fun update()

    fun getSurfaceContext(): Context


}