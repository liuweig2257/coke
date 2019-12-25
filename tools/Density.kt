package com.example.base.utils

import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks
import android.content.res.Configuration

class Density {
    companion object {
        final val WIDTH = 750f//单位dp
        var appDensity = 0f
        var appScaleDensity = 0f
        //在activity的setContentView之前执行
        @JvmStatic
        fun setDensity(application: Application,activity: Activity){
            val displayMetrics = application.resources.displayMetrics
            if(appDensity == 0f){
                appDensity = displayMetrics.density
                appScaleDensity = displayMetrics.scaledDensity

                application.registerComponentCallbacks(object :ComponentCallbacks{
                    override fun onLowMemory() {

                    }

                    override fun onConfigurationChanged(newConfig: Configuration?) {
                        if(newConfig != null && newConfig.fontScale > 0){
                            appScaleDensity = application.resources.displayMetrics.scaledDensity
                        }
                    }
                })
            }

            //计算目标density
            var targetDensity = displayMetrics.widthPixels/ WIDTH
            var targetScaleDensity  = targetDensity*(appScaleDensity/ appDensity)
            var targetDensityDpi = (targetDensity*160).toInt()

            //设置数据
            val activityDis = activity.resources.displayMetrics
            activityDis.density = targetDensity
            activityDis.scaledDensity = targetScaleDensity
            activityDis.densityDpi = targetDensityDpi

        }
    }
}