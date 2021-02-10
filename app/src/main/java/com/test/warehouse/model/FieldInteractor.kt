package com.test.warehouse.model

import com.test.warehouse.model.mans.IManEntity
import com.test.warehouse.model.mans.StrongManEntity
import com.test.warehouse.model.products.HeavyProductEntity
import com.test.warehouse.model.products.LightProductEntity
import com.test.warehouse.model.products.IProductEntity
import com.test.warehouse.model.products.MediumProductEntity
import kotlin.random.Random

class FieldInteractor() {
    private var entityPool: MutableList<BaseEntity> = mutableListOf()

    object ObjectFactory {
        private const val PRODUCT_BASE_SPEED=10

        fun createManRandomly(): BaseEntity {
            return StrongManEntity(Random.nextInt(-40, 40).toFloat(), Random.nextInt(-40, 40).toFloat(), Random.nextInt(6, 12))
        }

        fun createProductRandomly(): BaseEntity {
            var chance=Random.nextInt(0,2)
            return when (chance) {
                0 -> LightProductEntity(Random.nextInt(-40, 40).toFloat(), Random.nextInt(-40, 40).toFloat(), PRODUCT_BASE_SPEED)
                1 -> MediumProductEntity(Random.nextInt(-40, 40).toFloat(), Random.nextInt(-40, 40).toFloat(), PRODUCT_BASE_SPEED / 2)
                2 -> HeavyProductEntity(Random.nextInt(-40, 40).toFloat(), Random.nextInt(-40, 40).toFloat(), PRODUCT_BASE_SPEED / 3)
                else -> LightProductEntity(Random.nextInt(-40, 40).toFloat(), Random.nextInt(-40, 40).toFloat(), PRODUCT_BASE_SPEED)
            }
        }
    }

    fun generateEntities(): MutableList<BaseEntity> {
        entityPool= mutableListOf()
        for(i in 1..5) entityPool.add(ObjectFactory.createProductRandomly())
        for(i in 1..5) entityPool.add(ObjectFactory.createManRandomly())
        return entityPool
    }

}