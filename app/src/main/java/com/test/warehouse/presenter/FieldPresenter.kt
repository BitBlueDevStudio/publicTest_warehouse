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

class FieldPresenter(private var fieldIntercator: FieldInteractor, fieldWith:Int, fieldHeight:Int, IDraw:IDrawingInterface) {

    var objectPool: MutableList<BaseObjectPresenter> = mutableListOf()
    var fieldWidth:Int=fieldWith
    var fieldHeigt:Int=fieldHeight
    private var IDrawSurface=IDraw
    private var lastSpawnTime:Long=-1
    var lastDrawNanoTime: Long = -1

    init {
        spawnObjects(IDrawSurface.getSurfaceContext(),true)
    }

    private fun spawnObjects(cnt: Context,isStart:Boolean) {
        val entities=fieldIntercator.generateEntities(isStart)
        for (entity in entities) {
            when (entity) {
                is StrongManEntity -> objectPool.add(ManPresenter(BitmapFactory.decodeResource(cnt.resources, R.drawable.man2),entity.x,entity.y,entity.speed,this))
                is SilyManEntity -> objectPool.add(ManPresenter(BitmapFactory.decodeResource(cnt.resources, R.drawable.man),entity.x,entity.y,entity.speed,this))
                is LightProductEntity -> objectPool.add(ProductPresenter(BitmapFactory.decodeResource(cnt.resources, R.drawable.product),entity.x,entity.y,entity.speed,this))
                is MediumProductEntity -> objectPool.add(ProductPresenter(BitmapFactory.decodeResource(cnt.resources, R.drawable.product2),entity.x,entity.y,entity.speed,this))
                is HeavyProductEntity -> objectPool.add(ProductPresenter(BitmapFactory.decodeResource(cnt.resources, R.drawable.product3),entity.x,entity.y,entity.speed,this))
            }
            lastSpawnTime=System.currentTimeMillis()
        }
    }

    fun updateObjects() {
        val now = System.nanoTime()

        if (lastDrawNanoTime == -1L) {
            lastDrawNanoTime = now
        }
        val deltaTime = ((now - lastDrawNanoTime) / 1000000).toInt()
        for (item in objectPool) item.update(deltaTime)
    }

    fun onDraw(canvas:Canvas) {
        IDrawSurface.drawObjects(canvas,objectPool)
        if (System.currentTimeMillis()>lastSpawnTime+(fieldIntercator.SPAWN_INTERVAL*1000)) {
            spawnObjects(IDrawSurface.getSurfaceContext(),false)
        }
    }

}