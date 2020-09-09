package com.travels.searchtravels

import android.net.Uri
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import com.preview.planner.prefs.AppPreferences.setToken
import com.travels.searchtravels.activity.ChipActivity
import com.travels.searchtravels.activity.DetailsActivity
import com.travels.searchtravels.activity.MainActivity
import com.travels.searchtravels.utils.Constants
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File

@RunWith(AndroidJUnit4::class)
class InstrumentedTest {
    private val API_KEY_VISION = "Enter API key"

    companion object {
        const val MIN_TIMEOUT = 120 * 1000L
    }

    @Test(timeout = MIN_TIMEOUT)
    fun saintPetersburgCity() {
        val scenario = launch(MainActivity::class.java)
        ActivityLifecycleMonitorRegistry.getInstance()
            .addLifecycleCallback { activity, stage ->
                if (activity is MainActivity && stage == Stage.RESUMED) {
                    (activity as MainActivity).uploadImage(
                        Uri.fromFile(
                            File(
                                "src/main/assets/snow_spb.jpg"
                            )
                        )
                    )
                } else if (activity is DetailsActivity && stage == Stage.RESUMED) {
                    Assert.assertEquals(
                        "Saint Petersburg",
                        Constants.PICKED_CITY_EN
                    )
                }
            }
        scenario.moveToState(Lifecycle.State.STARTED)
        scenario.moveToState(Lifecycle.State.CREATED)
        setToken(
            ApplicationProvider.getApplicationContext(),
            API_KEY_VISION
        )
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test(timeout = MIN_TIMEOUT)
    fun blackSea() {
        val scenario = launch(MainActivity::class.java)
        ActivityLifecycleMonitorRegistry.getInstance()
            .addLifecycleCallback { activity, stage ->
                if (activity is MainActivity && stage == Stage.RESUMED) {
                    (activity as MainActivity).uploadImage(
                        Uri.fromFile(
                            File(
                                "src/main/assets/black_sea.jpg"
                            )
                        )
                    )
                } else if (activity is DetailsActivity && stage == Stage.RESUMED) {
                    Assert.assertEquals(
                        "Turkey",
                        Constants.PICKED_CITY_EN
                    )
                }
            }
        scenario.moveToState(Lifecycle.State.STARTED)
        scenario.moveToState(Lifecycle.State.CREATED)
        setToken(
            ApplicationProvider.getApplicationContext(),
            API_KEY_VISION
        )
        scenario.moveToState(Lifecycle.State.RESUMED)
    }


    @Test(timeout = MIN_TIMEOUT)
    fun snowMoscow() {
        val scenario = launch(MainActivity::class.java)
        ActivityLifecycleMonitorRegistry.getInstance()
            .addLifecycleCallback { activity, stage ->
                if (activity is MainActivity && stage == Stage.RESUMED) {
                    (activity as MainActivity).uploadImage(
                        Uri.fromFile(
                            File(
                                "src/main/assets/snow_moscow.jpg"
                            )
                        )
                    )
                } else if (activity is DetailsActivity && stage == Stage.RESUMED) {
                    Assert.assertEquals(
                        "Moscow",
                        Constants.PICKED_CITY_EN
                    )
                }
            }
        scenario.moveToState(Lifecycle.State.STARTED)
        scenario.moveToState(Lifecycle.State.CREATED)
        setToken(
            ApplicationProvider.getApplicationContext(),
            API_KEY_VISION
        )
        scenario.moveToState(Lifecycle.State.RESUMED)
    }


    @Test(timeout = MIN_TIMEOUT)
    fun checkSPbCityPrice() {
        Constants.PICKED_CITY_EN = "Saint Petersburg"
        ActivityLifecycleMonitorRegistry.getInstance()
            .addLifecycleCallback { activity, stage ->
                if (activity is DetailsActivity && stage == Stage.RESUMED) {
                    activity.findViewById<TextView>(R.id.airticketTV)
                        .addTextChangedListener(object : TextWatcher {
                            override fun afterTextChanged(s: Editable?) {}

                            override fun beforeTextChanged(
                                s: CharSequence?,
                                start: Int,
                                count: Int,
                                after: Int
                            ) {
                            }

                            override fun onTextChanged(
                                s: CharSequence?,
                                start: Int,
                                before: Int,
                                count: Int
                            ) {
                                Assert.assertEquals(
                                    s.toString(),
                                    activity.data["airplane price"].toString()
                                )
                            }

                        })
                }
                if (activity is ChipActivity && stage == Stage.CREATED) {
                    Assert.fail("Didn't show price.")
                }
            }
        val scenario = launch(DetailsActivity::class.java)
    }

    @Test(timeout = MIN_TIMEOUT)
    fun showMSKPriceSecondaryActivity() {
        Constants.PICKED_CITY_EN = "Moscow"
        ActivityLifecycleMonitorRegistry.getInstance()
            .addLifecycleCallback { activity, stage ->
                if (activity is ChipActivity && stage == Stage.RESUMED) {
                    activity.findViewById<TextView>(R.id.airticketTV)
                        .addTextChangedListener(object : TextWatcher {
                            override fun afterTextChanged(s: Editable?) {}

                            override fun beforeTextChanged(
                                s: CharSequence?,
                                start: Int,
                                count: Int,
                                after: Int
                            ) {
                            }

                            override fun onTextChanged(
                                s: CharSequence?,
                                start: Int,
                                before: Int,
                                count: Int
                            ) {
                                Assert.assertTrue(s.toString().matches(Regex(".*\\d.*")))
                            }

                        })
                }
            }
        val scenario = launch(ChipActivity::class.java)
    }
}
