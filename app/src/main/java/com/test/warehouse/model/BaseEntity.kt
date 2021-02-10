package com.test.warehouse.model
import kotlin.random.Random

enum class EntityState { Idle,Moving }

abstract class BaseEntity(override var x: Float, override var y: Float, private var speedRate:Int ):IEntity {
    override val speed:Int=speedRate

    override var state:EntityState= EntityState.Idle
    override lateinit var currentMovingVector: CordsObject

    fun moveToVector(movingVector: CordsObject) {
        currentMovingVector=movingVector
        state=EntityState.Moving
    }

    override fun collideWall() {
        moveToVector(CordsObject(currentMovingVector.x+Random.nextInt(-40, 40).toFloat(), currentMovingVector.y+Random.nextInt(-40, 40).toFloat()))
    }

}

