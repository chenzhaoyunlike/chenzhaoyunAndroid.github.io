package com.example.animecomic

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animecomic.ui.theme.AnimeComicTheme
import kotlin.reflect.KProperty

/**
 * 我的
 *
 * @author chenzhaoyun
 * @date 2021/8/26
 */
class MineActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //定义布局
        setContent {
            AnimeComicTheme {
                Conversation(message = conversationSample)
            }
        }
    }

    val conversationSample = listOf( Message( "Colleague", "Test...Test...Test..." ),
        Message( "Colleague", "List of Android versions:\n" + "Android KitKat (API 19)\n" + "Android Lollipop (API 21)\n" + "Android Marshmallow (API 23)\n" + "Android Nougat (API 24)\n" + "Android Oreo (API 26)\n" + "Android Pie (API 28)\n" + "Android 10 (API 29)\n" + "Android 11 (API 30)\n" + "Android 12 (API 31)\n" ),
        Message( "Colleague", "I think Kotlin is my favorite programming language.\n" + "It's so much fun!" ),
        Message( "Colleague", "Searching for alternatives to XML layouts..." ),
        Message( "Colleague", "Hey, take a look at Jetpack Compose, it's great!\n" + "It's the Android's modern toolkit for building native UI." + "It simplifies and accelerates UI development on Android." + "Less code, powerful tools, and intuitive Kotlin APIs :)" ),
        Message( "Colleague", "It's available from API 21+ :)" ),
        Message( "Colleague", "Writing Kotlin for UI seems so natural, Compose where have you been all my life?" ),
        Message( "Colleague", "Android Studio next version's name is Arctic Fox" ),
        Message( "Colleague", "Android Studio Arctic Fox tooling for Compose is top notch ^_^" ),
        Message( "Colleague", "I didn't know you can now run the emulator directly from Android Studio" ),
        Message( "Colleague", "Compose Previews are great to check quickly how a composable layout looks like" ),
        Message( "Colleague", "Previews are also interactive after enabling the experimental setting" ),
        Message( "Colleague", "Have you tried writing build.gradle with KTS?" ), )

    @Composable
    fun Conversation(message: List<Message>) {
        //垂直滚动列表
        LazyColumn {
            //条目
            items(message) { msg ->
                MessageCard(msg = msg)
            }
        }
    }

    @Preview
    @Composable
    fun PreviewConversation(){
        AnimeComicTheme {
            Conversation(message = conversationSample)
        }
    }


    @Composable
    fun MessageCard(msg: Message) {
        // Add padding around our message
        @Composable
        fun MessageCard(msg: Message) {
            Row(modifier = Modifier.padding(all = 8.dp)) {
                Image(
                    painter = painterResource(R.drawable.ic_naughty),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .border(1.5.dp, MaterialTheme.colors.secondaryVariant, CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))

                // We toggle the isExpanded variable when we click on this Column
                Column() {
                    Text(
                        text = msg.author,
                        color = MaterialTheme.colors.secondaryVariant,
                        style = MaterialTheme.typography.subtitle2
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Surface(
                        shape = MaterialTheme.shapes.medium,
                        elevation = 1.dp,
                        // surfaceColor color will be changing gradually from primary to surface
                        // animateContentSize will change the Surface size gradually
                        modifier = Modifier.animateContentSize().padding(1.dp)
                    ) {
                        Text(
                            text = msg.body,
                            modifier = Modifier.padding(all = 4.dp),
                            // If the message is expanded, we display all its content
                            // otherwise we only display the first line
                            style = MaterialTheme.typography.body2
                        )
                    }
                }
            }
        }
    }

    /**
     * data class算是Kotlin中一大闪光点，data class就是一个类中只包含一些数据字段，类似于vo,pojo,java bean。
     * 一般而言，我们在Java中定义了这个数据类之后要重写一下toString，equals等方法。要生成get,set方法。
    然而在Kotlin中这些都不在需要自己手动去敲了，编译器在背后默默给我们生成了如下的东西：
     */
    data class Message(
        //val 只读变量  这种声明变量的方式相当于java中的final变量。一个val创建的时候必须初始化，因为以后不能被改变
        //var 可变变量，和 java里面声明变量一样
        val author: String,
        val body: String
    )

}
