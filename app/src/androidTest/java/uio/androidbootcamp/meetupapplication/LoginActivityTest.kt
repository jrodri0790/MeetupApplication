package uio.androidbootcamp.meetupapplication

import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers.*
import org.hamcrest.Matcher
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import uio.androidbootcamp.meetupapplication.views.LoginActivity


@RunWith(AndroidJUnit4::class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class LoginActivityTest {

    private lateinit var userName: String
    private lateinit var password: String

    @get:Rule
    var mActivityRule: ActivityTestRule<LoginActivity> = ActivityTestRule(LoginActivity::class.java)

    private fun withItemContent(expectedText: String): Matcher<Any> {
        checkNotNull(expectedText)
        return withItemContent(equalTo(expectedText).toString())
    }

    @Test
    fun a_testInsertSecondUserInMemory() {
        userName = "Dan"
        password = "Dan1"

        onView(withId(R.id.et_username))
                .perform(typeText(userName), closeSoftKeyboard())
        onView(withId(R.id.et_username))
                .check(matches(withText(userName)))


        onView(withId(R.id.et_password))
                .perform(typeText(password), closeSoftKeyboard())
        onView(withId(R.id.et_password))
                .check(matches(withText(password)))


        onView(withId(R.id.btn_validate)).perform(click())

        onView(withText("Campos válidos")).inRoot(withDecorView(not(`is`(mActivityRule.activity.window.decorView))))
                .check(matches(isDisplayed()))

        onView(withId(R.id.txt_user_name))
                .check(matches(withText("Dan")))

        onData(instanceOf(String::class.java))
                .inAdapterView(withId(android.R.id.list))
                .atPosition(0)
                .check(matches(hasDescendant(withText("Dan"))))
    }

    @Test
    fun b_testInsertUserInMemory() {
        userName = "Jorge"
        password = "Jorge1"

        onView(withId(R.id.et_username))
                .perform(typeText(userName), closeSoftKeyboard())
        onView(withId(R.id.et_username))
                .check(matches(withText(userName)))


        onView(withId(R.id.et_password))
                .perform(typeText(password), closeSoftKeyboard())
        onView(withId(R.id.et_password))
                .check(matches(withText(password)))


        onView(withId(R.id.btn_validate)).perform(click())

        onView(withText("Campos válidos")).inRoot(withDecorView(not(`is`(mActivityRule.activity.window.decorView))))
                .check(matches(isDisplayed()))

        onData(instanceOf(String::class.java))
                .inAdapterView(withId(android.R.id.list))
                .atPosition(0)
                .check(matches(hasDescendant(withText("Dan"))))

        onData(instanceOf(String::class.java))
                .inAdapterView(withId(android.R.id.list))
                .atPosition(1)
                .check(matches(hasDescendant(withText("Jorge"))))

    }


    @Test
    fun c_testShowErrorWhenUserAlreadyExists() {
        userName = "Jorge"
        password = "Jorge1"

        onView(withId(R.id.et_username))
                .perform(typeText(userName), closeSoftKeyboard())
        onView(withId(R.id.et_username))
                .check(matches(withText(userName)))


        onView(withId(R.id.et_password))
                .perform(typeText(password), closeSoftKeyboard())
        onView(withId(R.id.et_password))
                .check(matches(withText(password)))


        onView(withId(R.id.btn_validate)).perform(click())

        onView(withText("Usuario ya existe")).inRoot(withDecorView(not(`is`(mActivityRule.activity.window.decorView))))
                .check(matches(isDisplayed()))
    }
}