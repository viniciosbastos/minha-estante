package com.aurelio.minhaestante.di

import android.app.Application

class MinhaEstanteApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }
}