package com.test.warehouse.presenter

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import com.test.warehouse.R
import com.test.warehouse.model.FieldInteractor
import com.test.warehouse.model.mans.SilyManEntity
import com.test.warehouse.model.mans.StrongManEntity
import com.test.warehouse.model.products.HeavyProductEntity
import com.test.warehouse.model.products.LightProductEntity
import com.test.warehouse.model.products.MediumProductEntity
import com.test.warehouse.view.IDrawingInterface

class FieldPresenter(cnt:Context,fieldIntercator:FieldInteractor,fieldWith:Int,fieldHeight:Int, IDraw:IDrawingInterface) {

    var fieldIntercator:FieldInteractor=fieldIntercator
    var objectPool: MutableList<BaseObjectPresenter> = mutableListOf()
    var fieldWidth:Int=fieldWith
    var fieldHeigt:Int=fieldHeight
    var DrawInterface=IDraw
    private var currentTime:Long=-1
    private var lastSpawnTime:Long=-1

    init {
        spawnObjects(cnt,true)
    }

    private fun spawnObjects(cnt: Context,isStart:Boolean) {
        val entities=fieldIntercator.generateEntities(isStart)
        for (entity in entities) {
            when (entity) {
                is StrongManEntity -> objectPool.add(ManPresenter(BitmapFactory.decodeResource(cnt.resources, R.drawable.man2),entity.x,entity.y,entity.speed,this))
                is SilyManEntity -> objectPool.add(ManPresenter(BitmapFactory.decodeResource(cnt.resources, R.drawable.man),entity.x,entity.y,entity.speed,this))
                is LightProductEntity -> objectPool.add(ManPresenter(BitmapFactory.decodeResource(cnt.resources, R.drawable.product),entity.x,entity.y,entity.speed,this))
                is MediumProductEntity -> objectPool.add(ManPresenter(BitmapFactory.decodeResource(cnt.resources, R.drawable.product2),entity.x,entity.y,entity.speed,this))
                is HeavyProductEntity -> objectPool.add(ManPresenter(BitmapFactory.decodeResource(cnt.resources, R.drawable.product3),entity.x,entity.y,entity.speed,this))
            }
            lastSpawnTime=System.currentTimeMillis()
        }
    }

    fun updateObjects() {
        for (item in objectPool) item.update()
    }

    fun onDraw(canvas:Canvas) {
        DrawInterface.drawObjects(canvas,objectPool)
        currentTime=System.currentTimeMillis()
        if (currentTime>lastSpawnTime+10000) {
            spawnObjects(DrawInterface.getSurfaceContext(),false)
        }
    }

}