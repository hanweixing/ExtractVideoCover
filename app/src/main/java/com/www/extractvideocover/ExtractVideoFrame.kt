package com.www.extractvideocover

import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.net.URL
import java.util.concurrent.Executors

/**
 * 视频处理单例
 */
object ExtractVideoFrame {
    // This function retrieves the first frame of a video from a URL
    fun getFirstFrameFromVideoUrl(videoUrl: String, callback: (String?) -> Unit) {
        // Use a background thread to avoid blocking the main thread
        Executors.newSingleThreadExecutor().execute {
            val retriever = MediaMetadataRetriever()
            try {
                // Set the video URL as the data source
                retriever.setDataSource(videoUrl, HashMap())

                // Retrieve the first frame (at time 0 microseconds)
                val firstFrame: Bitmap? = retriever.getFrameAtTime(0)

                // If the frame is successfully retrieved
                if (firstFrame != null) {
                    // Convert the Bitmap to a Base64-encoded string
                    val outputStream = ByteArrayOutputStream()
                    firstFrame.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                    val byteArray = outputStream.toByteArray()

                    // Encode the byte array to a Base64 string
                    val base64Image = Base64.encodeToString(byteArray, Base64.NO_WRAP)

                    val value = base64Image.replace("\n", "")

                    // Pass the Base64 string to the callback
                    callback(value)
                } else {
                    // In case the frame could not be retrieved, pass null
                    callback(null)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                callback(null)
            } finally {
                // Release the MediaMetadataRetriever
                retriever.release()
            }
        }
    }
}
