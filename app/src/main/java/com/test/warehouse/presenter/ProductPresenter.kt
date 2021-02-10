package com.test.warehouse.presenter

import android.content.Context
import com.test.warehouse.view.DrawingSurface
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import com.test.warehouse.R
import com.test.warehouse.model.CordsObject
import com.test.warehouse.model.EntityState
import com.test.warehouse.model.ManEntity


class ProductPresenter(bitmap: Bitmap, x: Float, y: Float, speed:Int, fieldPresentr:FieldPresenter) :
        BaseObjectPresenter(bitmap,x,y,ManEntity(x,y,speed),fieldPresentr) {
    private val fieldPresenter:FieldPresenter=fieldPresentr


    /*override fun draw() {
        val bitmap = this.image
        //canvas.drawBitmap(bitmap, entity.x.toFloat(), entity.y.toFloat(), null)
        // Last draw time.
        lastDrawNanoTime = System.nanoTime()
    }*/

    init {
        //this.drawingSurface = drawingSurface
        entity.moveToVector(CordsObject(20F,50F))
    }
}