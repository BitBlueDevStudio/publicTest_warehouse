package com.test.warehouse.model
import android.graphics.Bitmap
import kotlin.random.Random

enum class EntityState { idle,moving }

abstract class BaseEntity(var x: Float, var y: Float, var speedRate:Int, ) {
    val speed:Int=speedRate

    var state:EntityState= EntityState.idle
    lateinit var currentMovingVector: CordsObject

    fun moveToVector(movingVector: CordsObject) {
        currentMovingVector=movingVector
        state=EntityState.moving
    }

    fun collideWall() {
        moveToVector(CordsObject(currentMovingVector.x+Random.nextInt(-40, 40).toFloat(), currentMovingVector.y+Random.nextInt(-40, 40).toFloat()))
    }

}

