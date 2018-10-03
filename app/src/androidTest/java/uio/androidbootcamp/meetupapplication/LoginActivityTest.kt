package uio.androidbootcamp.meetupapplication

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uio.androidbootcamp.meetupapplication.views.LoginActivity


@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginActivityTest {

    private lateinit var userName: String
    private lateinit var password: String

    @get:Rule
    var mActivityRule: ActivityTestRule<LoginActivity> = ActivityTestRule(LoginActivity::class.java)

    @Before
    fun initValidString() {
    }

    @Test
    fun changeText_sameActivity() {
        userName = "Jorge"
        password = "Jorge1"
        onView(withId(R.id.et_username))
                .perform(typeText(userName), closeSoftKeyboard())
        onView(withId(R.id.et_password))
                .perform(typeText(password), closeSoftKeyboard())
        onView(withId(R.id.btn_validate)).perform(click())

        // Check that the text was changed.
        onView(withId(R.id.et_username))
                .check(matches(withText(userName)))
        onView(withId(R.id.et_password))
                .check(matches(withText(password)))
        onView(withText("Campos válidos")).inRoot(withDecorView(not(`is`(mActivityRule.activity.window.decorView))))
                .check(matches(isDisplayed()))
    }
}