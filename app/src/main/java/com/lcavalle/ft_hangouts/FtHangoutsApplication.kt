package com.lcavalle.ft_hangouts

import android.app.Application
import com.lcavalle.ft_hangouts.datasource.FtHangoutsDb

class FtHangoutsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FtHangoutsDb.init(this)
    }
}