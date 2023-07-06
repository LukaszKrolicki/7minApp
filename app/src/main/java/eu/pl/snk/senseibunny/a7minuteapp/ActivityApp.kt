package eu.pl.snk.senseibunny.a7minuteapp

import android.app.Application

class ActivityApp: Application() {
    val db by lazy{
        ActivityDatabase.getInstance(this)
    }
}