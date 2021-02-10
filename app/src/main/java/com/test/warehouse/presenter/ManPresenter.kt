package com.test.warehouse.presenter

import android.graphics.Bitmap
import com.test.warehouse.model.CordsObject
import com.test.warehouse.model.mans.StrongManEntity


class ManPresenter(bitmap: Bitmap, x: Float, y: Float, speed:Int, fieldPresentr:FieldPresenter) :
    BaseObjectPresenter(bitmap,x,y, StrongManEntity(x,y,speed),fieldPresentr) {

    init {
        entity.moveToVector(CordsObject(20F,50F))
    }

}