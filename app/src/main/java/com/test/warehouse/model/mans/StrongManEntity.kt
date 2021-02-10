package com.test.warehouse.model.mans

import com.test.warehouse.model.BaseEntity
import com.test.warehouse.model.products.HeavyProductEntity
import com.test.warehouse.model.products.IProductEntity
import com.test.warehouse.model.products.LightProductEntity
import com.test.warehouse.model.products.MediumProductEntity

class StrongManEntity(x: Float, y: Float, speedRate:Int) :
    BaseEntity(x, y,speedRate ), IManEntity{

    override fun collidedProduct(product: IProductEntity): Boolean {
        return product is HeavyProductEntity || product is MediumProductEntity
    }
}