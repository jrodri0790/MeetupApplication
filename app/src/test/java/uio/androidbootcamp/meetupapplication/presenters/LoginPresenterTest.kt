package uio.androidbootcamp.meetupapplication.presenters

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.runners.MockitoJUnitRunner
import uio.androidbootcamp.meetupapplication.views.LoginActivity

@RunWith(MockitoJUnitRunner::class)
class LoginPresenterTest {

    @Mock
    private lateinit var mockActivity: LoginActivity
    private lateinit var loginPresenter: LoginPresenter

    @Before
    fun init() {
        loginPresenter = LoginPresenter(mockActivity)
    }


    @Test
    fun test_is_valid_username_should_return_false_when_username_contains_numbers() {

        val userName = "Jorge1"
        val isValid = loginPresenter.isValidUserName(userName)
        assertThat(isValid, `is`(false))
    }

    @Test
    fun test_is_valid_username_should_return_true_when_username_not_contains_numbers() {
        val userName = "Jorge"
        val isValid = loginPresenter.isValidUserName(userName)
        assertThat(isValid, `is`(true))
    }

    @Test
    fun test_is_valid_password_should_return_false_when_password_not_contains_at_least_one_number() {
        val password = "Jorge"
        val isValid = loginPresenter.isValidPassword(password)
        assertThat(isValid, `is`(false))
    }

    @Test
    fun test_is_valid_password_should_return_true_when_password_contains_at_least_one_number() {
        val password = "Jorg1"
        val isValid = loginPresenter.isValidPassword(password)
        assertThat(isValid, `is`(true))
    }

    @Test
    fun test_validate_fields_should_call_show_error_message_if_validation_is_not_passed_for_user_name() {
        val userName = "Jorge1"
        val password = "Jorge1"
        loginPresenter.validateFields(userName, password)

        verify(mockActivity, times(1)).showErrorMessageOnUsernameEditText()
    }

    @Test
    fun test_validate_fields_should_not_call_show_error_message_if_validation_is_passed_for_user_name() {
        val userName = "Jorge"
        val password = "Jorge1"
        loginPresenter.validateFields(userName, password)

        verify(mockActivity, times(0)).showErrorMessageOnUsernameEditText()
    }

    @Test
    fun test_validate_fields_should_not_call_show_error_message_if_validation_is_passed_for_password() {
        val userName = "Jorge"
        val password = "Jorge1"
        loginPresenter.validateFields(userName, password)

        verify(mockActivity, times(0)).showErrorMessageOnPasswordEditText()
    }

    @Test
    fun test_validate_fields_should_call_show_error_message_if_validation_is_not_passed_for_password() {
        val userName = "Jorge"
        val password = "Jorge"
        loginPresenter.validateFields(userName, password)

        verify(mockActivity, times(1)).showErrorMessageOnPasswordEditText()
    }

    @Test
    fun test_validate_fields_should_return_true_if_user_name_and_password_are_valid() {
        val userName = "Jorge"
        val password = "Jorge1"

        val isValid = loginPresenter.validateFields(userName, password)

        assertThat(isValid, `is`(true))
    }

    @Test
    fun test_validate_fields_should_return_call_show_success_message_if_username_and_password_are_valid() {
        val userName = "Jorge"
        val password = "Jorge1"

        loginPresenter.validateFields(userName, password)

        verify(mockActivity, times(1)).showSuccessMessage()
    }

    @Test
    fun test_validate_fields_should_return_false_if_user_name_is_not_valid() {
        val userName = "Jorge1"
        val password = "Jorge1"

        val isValid = loginPresenter.validateFields(userName, password)

        assertThat(isValid, `is`(false))
    }

    @Test
    fun test_validate_fields_should_return_false_if_password_is_not_valid() {
        val userName = "Jorge"
        val password = "Jorge"

        val isValid = loginPresenter.validateFields(userName, password)

        assertThat(isValid, `is`(false))
    }


}