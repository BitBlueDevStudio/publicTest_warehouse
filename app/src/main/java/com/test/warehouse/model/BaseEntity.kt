package com.test.warehouse.model
import com.test.warehouse.model.mans.IManEntity
import com.test.warehouse.model.mans.SilyManEntity
import com.test.warehouse.model.mans.StrongManEntity
import com.test.warehouse.model.products.HeavyProductEntity
import com.test.warehouse.model.products.IProductEntity
import com.test.warehouse.model.products.LightProductEntity
import com.test.warehouse.model.products.MediumProductEntity
import kotlin.random.Random

enum class EntityState { idle,moving }

abstract class BaseEntity(override var x: Float, override var y: Float, var speedRate:Int, ):IEntity {
    override val speed:Int=speedRate

    override var state:EntityState= EntityState.idle
    override lateinit var currentMovingVector: CordsObject

    fun moveToVector(movingVector: CordsObject) {
        currentMovingVector=movingVector
        state=EntityState.moving
    }

    override fun collideWall() {
        moveToVector(CordsObject(currentMovingVector.x+Random.nextInt(-40, 40).toFloat(), currentMovingVector.y+Random.nextInt(-40, 40).toFloat()))
    }

}

