package com.telekommms.weatherapp.vm.vm_factory

interface Factory<T> {
    fun create(): T
}