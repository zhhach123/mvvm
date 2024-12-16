package com.xdaoebike.smartbike.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class FleetingLiveData<T> : MutableLiveData<T>() {

    override fun setValue(value: T) {
        super.setValue(value)
        super.setValue(null)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, Observer {
            if (it == null) return@Observer
            observer.onChanged(it)
        })
    }

}