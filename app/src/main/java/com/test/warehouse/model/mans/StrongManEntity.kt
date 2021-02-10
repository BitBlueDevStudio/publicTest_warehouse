package com.test.warehouse.model.mans

import com.test.warehouse.model.BaseEntity

class StrongManEntity(x: Float, y: Float, speedRate:Int) :
    BaseEntity(x, y,speedRate ), IManEntity{

}