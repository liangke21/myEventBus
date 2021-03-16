# EventBus使用以及源码分析


[![](https://jitpack.io/v/liangke21/myEventBus.svg)](https://jitpack.io/#liangke21/myRecyclerView)

### 将其添加到存储库末尾的root build.gradle中
```java
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
### 添加依赖项
```java
	dependencies {
	        implementation 'com.github.liangke21:myEventBus:Tag'
            implementation 'org.greenrobot:eventbus:3.2.0'
	}

```


```java
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
```


<a data-fancybox title='' href='https://55-1251889734.cos.ap-beijing-1.myqcloud.com/2021-03-16-tjzh5e.png' >![](https://55-1251889734.cos.ap-beijing-1.myqcloud.com/2021-03-16-tjzh5e.png)</a>

## POSTING（默认的线程模式）：
该模式下，该事件在哪个线程发布出来的，事件处理函数就会在这个线程中运行，即：事件的发布和是事件的接收处理在同一个线程。
注意：1）在线程模型为POSTING的事件处理函数中尽量避免执行耗时操作，因为它会阻塞事件的传递，甚至有可能会引起ANR。
2）如果是在事件的接收时处理UI操作的话，这里可要注意了。如果是事件发布在子线程中的话，会导致UI不能更新。

设置方式：

@Subscribe  //默认
1
或者

@Subscribe(threadMode = ThreadMode.POSTING)
1
## MAIN:
该模式下，不管是事件发布在哪个线程上，事件的处理都会在UI线程中执行。事件处理时间不能太长，长了会ANR的。

设置方式：

@Subscribe(threadMode = ThreadMode.MAIN)
1
## BACKGROUND：
该模式下，不管是事件发布在哪个线程中，事件的处理都在子线程中开展。这里分为两种情况：如果事件是在UI线程中发布出来的，那么该事件处理函数就会在新的线程中运行，如果事件本来就是子线程中发布出来的，那么该事件处理函数直接在发布事件的线程中执行。在此事件处理函数中禁止进行UI更新操作。

设置方式：

@Subscribe(threadMode = ThreadMode.BACKGROUND)
1
## ASYNC：
该模式下，无论事件在哪个线程发布，该事件处理函数都会在新建的子线程中执行，同样，此事件处理函数中禁止进行UI更新操作。

设置方式：

@Subscribe(threadMode = ThreadMode.ASYNC)

