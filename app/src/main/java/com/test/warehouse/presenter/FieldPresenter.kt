package com.test.warehouse.presenter

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import com.test.warehouse.R
import com.test.warehouse.model.FieldInteractor
import com.test.warehouse.model.mans.IManEntity
import com.test.warehouse.model.products.IProductEntity
import com.test.warehouse.view.IDrawingInterface

class FieldPresenter(cnt:Context,fieldIntercator:FieldInteractor,fieldWith:Int,fieldHeight:Int, IDraw:IDrawingInterface) {

    var fieldIntercator:FieldInteractor=fieldIntercator
    var objectPool: MutableList<BaseObjectPresenter> = mutableListOf()
    var fieldWidth:Int=fieldWith
    var fieldHeigt:Int=fieldHeight
    var DrawInterface=IDraw

    init {
        val entities=fieldIntercator.generateEntities()
        for (entity in entities) {
            if (entity is IManEntity) objectPool.add(ManPresenter(BitmapFactory.decodeResource(cnt.resources, R.drawable.man),entity.x,entity.y,entity.speed,this))
            else if (entity is IProductEntity) objectPool.add(ManPresenter(BitmapFactory.decodeResource(cnt.resources, R.drawable.product),entity.x,entity.y,entity.speed,this))

        }
    }

    fun updateObjects() {
        for (item in objectPool) item.update()
    }

    fun onDraw(canvas:Canvas) {
        DrawInterface.drawObjects(canvas,objectPool)
    }

}