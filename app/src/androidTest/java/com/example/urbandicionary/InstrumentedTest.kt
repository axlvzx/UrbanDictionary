package com.example.urbandicionary

import android.app.Instrumentation
import android.view.KeyEvent
import androidx.test.espresso.action.ViewActions.click
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.urbandicionary.ui.activity.MainActivity
import org.junit.runner.RunWith
import org.junit.Before
import org.junit.Rule
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.*

@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class InstrumentedTest {

    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun testInputNumeric() {
        Thread.sleep(500)
        val inst = Instrumentation()
        onView(withId(R.id.action_search)).perform(click())
        Thread.sleep(500)
        onView(withId(androidx.appcompat.R.id.search_src_text)).perform(typeText("home"))
        inst.sendKeyDownUpSync(KeyEvent.KEYCODE_SEARCH)
        Thread.sleep(1000)

    }


}
