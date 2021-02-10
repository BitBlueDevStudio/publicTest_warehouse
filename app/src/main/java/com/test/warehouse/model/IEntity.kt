package com.test.warehouse.model

interface IEntity {
    val speed:Int
    var state:EntityState
    var currentMovingVector: CordsObject

    var x:Float
    var y:Float


    fun collideWall()

}