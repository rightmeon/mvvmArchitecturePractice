package com.example.mvvmpj

import android.hardware.SensorManager
import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mvvmpj.api.NaverBookAPI

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import javax.inject.Inject

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Inject
    lateinit var api : NaverBookAPI
    @Test
    fun useAppContext() {
    }
}
