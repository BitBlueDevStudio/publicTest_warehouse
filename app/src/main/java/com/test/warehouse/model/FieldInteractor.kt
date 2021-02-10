package com.test.warehouse.model

import com.test.warehouse.presenter.BaseObjectPresenter
import com.test.warehouse.presenter.ManPresenter
import com.test.warehouse.view.DrawingSurface
import kotlin.random.Random

class FieldInteractor() {
    var entityPool: MutableList<BaseEntity> = mutableListOf()

    object ObjectFactory {
        fun createManRandomly(): ManEntity{
            return ManEntity(Random.nextInt(-40, 40).toFloat(), Random.nextInt(-40, 40).toFloat(), Random.nextInt(6, 12))
        }

        //fun createProductRandomly(surface: DrawingSurface): ManPresenter {
        //return ManPresenter(surface, 2, 5, 10)
        //}
    }

    fun generateEntities(): MutableList<BaseEntity> {
        entityPool= mutableListOf()
        for(i in 1..5) entityPool.add(ObjectFactory.createManRandomly())
        return entityPool
    }

}