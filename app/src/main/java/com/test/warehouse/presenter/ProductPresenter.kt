package com.test.warehouse.presenter

import android.graphics.Bitmap
import com.test.warehouse.model.BaseEntity
import com.test.warehouse.model.CordsObject
import com.test.warehouse.model.products.IProductEntity


class ProductPresenter(bitmap: Bitmap, x: Float, y: Float, speed:Int, fieldPresentr:FieldPresenter,enty: BaseEntity) :
        BaseObjectPresenter(bitmap,x,y, enty,fieldPresentr) {


    init {
        //this.drawingSurface = drawingSurface
        entity.moveToVector(CordsObject(20F,50F))
    }

    fun getEntity(): IProductEntity{
        return entity as IProductEntity
    }
}