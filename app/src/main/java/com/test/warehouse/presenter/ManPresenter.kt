package com.test.warehouse.presenter

import android.graphics.Bitmap
import com.test.warehouse.model.BaseEntity
import com.test.warehouse.model.CordsObject
import com.test.warehouse.model.IEntity
import com.test.warehouse.model.mans.IManEntity
import com.test.warehouse.model.mans.StrongManEntity
import com.test.warehouse.model.products.LightProductEntity


class ManPresenter(bitmap: Bitmap, x: Float, y: Float, speed:Int, fieldPresentr:FieldPresenter, enty: BaseEntity) :
    BaseObjectPresenter(bitmap,x,y, enty,fieldPresentr) {

    init {
        entity.moveToVector(CordsObject(20F,50F))
    }

    fun collideProduct(product:ProductPresenter): Boolean {
        val s=entity as IManEntity
        return s.collidedProduct(product.getEntity())
    }

}