package cz.venjudev.fio

import android.app.Application
import cz.venjudev.fio.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class FioApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@FioApp)
            modules(appModule)
        }
    }
}