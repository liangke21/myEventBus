package com.androidlk.myeventbus.eventbus

/**
 * 文件名: MessageEvent
 * 作者: 13967
 * 时间: 2021/2/7 17:41
 * 描述:事件对象
 */
class MessageEvent(val type:Int) {
    var stringValue:String=""//携带参数类型
    var intValue:Int=0
    var booleanValue:Boolean=false
}
