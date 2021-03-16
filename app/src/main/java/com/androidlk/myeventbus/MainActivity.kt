package com.androidlk.myeventbus

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.androidlk.myeventbus.eventbus.MessageEvent
import com.androidlk.myeventbus.eventbus.myEventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //组件之间的通讯
        myEventBus.register(this)//注册
        myEventBus.post(1, "蛮吉")
        myEventBus.post(2, 35)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MessageEvent) { /* Do something */
        when (event.type) {
            1 -> Log.i("你丫谁呀", event.stringValue)
            2 -> Log.i("你丫多大", "${event.intValue}")
        }
    }
}