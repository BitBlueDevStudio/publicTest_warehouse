package com.test.warehouse.presenter

import android.graphics.Bitmap
import com.test.warehouse.model.BaseEntity
import com.test.warehouse.model.CordsObject
import com.test.warehouse.model.EntityState

abstract class BaseObjectPresenter(img: Bitmap,X:Float,Y:Float, enty:BaseEntity,fieldPresentr: FieldPresenter) {

    protected var entity: BaseEntity = enty
    private val fieldPresenter:FieldPresenter=fieldPresentr
    var state: EntityState = EntityState.idle
    var x:Float
        get() = entity.x
        set(value) {entity.x =value}
    var y:Float
        get() = entity.y
        set(value) {entity.y=value}
    var speed:Float
        get() =entity.speed.toFloat()
        set(value) {}
    var currentMovingVector: CordsObject
        get() = entity.currentMovingVector
        set(value) {entity.currentMovingVector=value}


    var image:Bitmap=img
    val width: Int = image.width
    val height: Int = image.height

    open fun update(deltaTime: Int) {

        if(entity.state==EntityState.moving) {

            val distance = speed/12 * deltaTime
            val movingVectorLength =
                    Math.sqrt((currentMovingVector.x * entity.currentMovingVector.x + entity.currentMovingVector.y * entity.currentMovingVector.y).toDouble())


            this.x += (distance * currentMovingVector.x / movingVectorLength).toInt()
            this.y += (distance * currentMovingVector.y / movingVectorLength).toInt()

            if (this.x < 0) {
                this.x = 0F
                currentMovingVector.x = -entity.currentMovingVector.x
                entity.collideWall()
            } else if (this.entity.x > fieldPresenter.fieldWidth - width) {
                this.x = fieldPresenter.fieldWidth.toFloat() - width.toFloat()
                currentMovingVector.x = -entity.currentMovingVector.x
                entity.collideWall()
            }
            if (this.y < 0) {
                this.y = 0F
                currentMovingVector.y = -entity.currentMovingVector.y
                entity.collideWall()
            } else if (this.entity.y > fieldPresenter.fieldHeigt - height) {
                this.y = fieldPresenter.fieldHeigt.toFloat() - height.toFloat()
                currentMovingVector.y = -entity.currentMovingVector.y
                entity.collideWall()
            }
        }

    }

}