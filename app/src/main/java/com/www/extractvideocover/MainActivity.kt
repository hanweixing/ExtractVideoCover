package com.www.extractvideocover

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.www.extractvideocover.ui.theme.ExtractVideoCoverTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExtractVideoCoverTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        val videoUrl = "https://testcdn.connectfriendsapp.com/user/10004621/8dd036843fe94d898a784832ce20e012.mp4"
        ExtractVideoFrame.getFirstFrameFromVideoUrl(videoUrl) { cover ->
            if (cover != null) {
                println("封面是：" + cover)
            } else {
                println("封面获取失败!")
            }
        }
        println("测试一下")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExtractVideoCoverTheme {
        Greeting("Android")
    }
}
