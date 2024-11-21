package com.telekommms.weatherapp.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.telekommms.weatherapp.vm.vm_factory.Factory
import java.util.concurrent.atomic.AtomicInteger

class WeatherViewModel(
    application: Application
): AndroidViewModel(application) {

    val atomic = AtomicInteger()
}

class WeatherViewModelFactory(
    private val application: Application
) : Factory<WeatherViewModel> {
    override fun create(): WeatherViewModel {
        return WeatherViewModel(
            application
        )
    }
}