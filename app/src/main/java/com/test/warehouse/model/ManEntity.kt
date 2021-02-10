package com.test.warehouse.model

enum class EntityState { idle,moving }

class ManEntity(x: Float, y: Float,speedRate:Int) :
    BaseEntity(x, y,speedRate ) {

}