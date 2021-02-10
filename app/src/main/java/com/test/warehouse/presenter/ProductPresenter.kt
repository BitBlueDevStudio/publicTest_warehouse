package com.test.warehouse.presenter

import android.graphics.Bitmap
import com.test.warehouse.model.BaseEntity
import com.test.warehouse.model.CordsObject
import com.test.warehouse.model.IEntity
import com.test.warehouse.model.mans.IManEntity
import com.test.warehouse.model.mans.StrongManEntity
import com.test.warehouse.model.products.IProductEntity
import com.test.warehouse.model.products.LightProductEntity
import com.test.warehouse.model.products.MediumProductEntity
import kotlin.reflect.KClass


class ProductPresenter(bitmap: Bitmap, x: Float, y: Float, speed:Int, fieldPresentr:FieldPresenter,enty: BaseEntity) :
        BaseObjectPresenter(bitmap,x,y, enty,fieldPresentr) {
    private val fieldPresenter:FieldPresenter=fieldPresentr


    init {
        //this.drawingSurface = drawingSurface
        entity.moveToVector(CordsObject(20F,50F))
    }

    fun getEntity(): IProductEntity{
        return entity as IProductEntity
    }
}