package com.test.warehouse.model

import com.test.warehouse.model.mans.IManEntity
import com.test.warehouse.model.mans.SilyManEntity
import com.test.warehouse.model.mans.StrongManEntity
import com.test.warehouse.model.products.HeavyProductEntity
import com.test.warehouse.model.products.LightProductEntity
import com.test.warehouse.model.products.IProductEntity
import com.test.warehouse.model.products.MediumProductEntity
import kotlin.random.Random

class FieldInteractor(Width:Int,Height:Int) {
    private var entityPool: MutableList<BaseEntity> = mutableListOf()
    private var width:Int=Width
    private var height:Int=Height
    val SPAWN_INTERVAL=10

    object ObjectFactory {
        private const val PRODUCT_BASE_SPEED=10

        fun createManRandomly(maxWidth:Int,maxHeight:Int): BaseEntity {
            return when (Random.nextInt(0,2)) {
                0 -> StrongManEntity(Random.nextInt(0, maxWidth).toFloat(), Random.nextInt(0, maxHeight).toFloat(), Random.nextInt(6, 10))
                else -> SilyManEntity(Random.nextInt(0, maxWidth).toFloat(), Random.nextInt(0, maxHeight).toFloat(), Random.nextInt(6, 10))
            }
        }

        fun createProductRandomly(maxWidth:Int,maxHeight:Int): BaseEntity {
            return when (Random.nextInt(0,3)) {
                1 -> MediumProductEntity(Random.nextInt(0, maxWidth).toFloat(), Random.nextInt(0, maxHeight).toFloat(), PRODUCT_BASE_SPEED / 2)
                2 -> HeavyProductEntity(Random.nextInt(0, maxWidth).toFloat(), Random.nextInt(0, maxHeight).toFloat(), PRODUCT_BASE_SPEED / 3)
                else -> LightProductEntity(Random.nextInt(0, maxWidth).toFloat(), Random.nextInt(0, maxHeight).toFloat(), PRODUCT_BASE_SPEED)
            }
        }
    }

    fun generateEntities(isStart:Boolean): MutableList<BaseEntity> {
        val tempEntityPool: MutableList<BaseEntity> = mutableListOf()
        for(i in 1..10) tempEntityPool.add(ObjectFactory.createProductRandomly(width,height))
        if (isStart) for(i in 1..10) tempEntityPool.add(ObjectFactory.createManRandomly(width,height))
        entityPool.addAll(tempEntityPool)
        return tempEntityPool
    }

}