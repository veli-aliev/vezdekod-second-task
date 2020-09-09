package com.travels.searchtravels

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.test.core.app.ApplicationProvider
import com.google.api.services.vision.v1.model.LatLng
import com.travels.searchtravels.api.OnVisionApiListener
import com.travels.searchtravels.api.VisionApi
import org.junit.Assert
import org.junit.Test
import java.io.IOException
import java.io.InputStream

class VisionApiUnitTest {
    private val API_KEY_VISION = "Enter API key"

    companion object {
        const val MIN_TIMEOUT = 120 * 1000L
    }

    @Test(timeout = MIN_TIMEOUT)
    fun visionApiOceanIsCorrect() {
        VisionApi.findLocation(getBitmapFromAsset(
            ApplicationProvider.getApplicationContext(),
            "estonian_forest"
        ),
            API_KEY_VISION, object : OnVisionApiListener {
                override fun onSuccess(latLng: LatLng?) {
                    Assert.assertTrue(true)
                }

                override fun onErrorPlace(category: String?) {
                    Assert.assertTrue(
                        category.equals("forest") || category.equals("sea") || category.equals(
                            "beach"
                        )
                    )
                }

                override fun onError() {
                    Assert.assertTrue(false)
                }
            })
    }

    @Test(timeout = MIN_TIMEOUT)
    fun visionApiShowMountainIsCorrect() {
        VisionApi.findLocation(getBitmapFromAsset(
            ApplicationProvider.getApplicationContext(),
            "snow_alpin"
        ),
            API_KEY_VISION, object : OnVisionApiListener {
                override fun onSuccess(latLng: LatLng?) {
                    Assert.assertTrue(true)
                }

                override fun onErrorPlace(category: String?) {
                    Assert.assertTrue(
                        category.equals("snow") || category.equals("sea") || category.equals(
                                "ocean"
                        )
                    )
                }

                override fun onError() {
                    Assert.assertTrue(false)
                }
            })
    }

     fun getBitmapFromAsset(context: Context, filePath: String?): Bitmap? {
        val assetManager: AssetManager = context.getAssets()
        val istr: InputStream
        var bitmap: Bitmap? = null
        try {
            istr = assetManager.open(filePath!!)
            bitmap = BitmapFactory.decodeStream(istr)
        } catch (e: IOException) {

        }
        return bitmap
    }
}
