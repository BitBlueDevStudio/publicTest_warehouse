package com.test.warehouse.model.mans

import com.test.warehouse.model.IEntity
import com.test.warehouse.model.products.IProductEntity

interface IManEntity: IEntity {
    fun collidedProduct(product: IProductEntity): Boolean
}