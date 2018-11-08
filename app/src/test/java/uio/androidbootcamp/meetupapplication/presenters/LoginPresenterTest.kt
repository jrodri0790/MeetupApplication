package uio.androidbootcamp.meetupapplication.presenters

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.runners.MockitoJUnitRunner
import uio.androidbootcamp.meetupapplication.interactors.SharedPreferencesInteractor
import uio.androidbootcamp.meetupapplication.models.User
import uio.androidbootcamp.meetupapplication.views.LoginActivity

@RunWith(MockitoJUnitRunner::class)
class LoginPresenterTest {

    @Mock
    private lateinit var mockActivity: LoginActivity
    @Mock
    private lateinit var mockInteractor: SharedPreferencesInteractor
    private lateinit var loginPresenter: LoginPresenter

    @Before
    fun init() {
        loginPresenter = LoginPresenter(mockActivity, mockInteractor)
    }

    @Test
    fun testShouldCallSaveMethodFromInteractorWhenFieldsAreValid(){
        val userName = "User"
        val password = "User1"
        val user = User(userName,password)

        loginPresenter.saveUser(userName, password)

        verify(mockInteractor, times(1)).saveUser(user)
    }

    @Test
    fun testShouldCallShowUsernameErrorIfUsernameIsNotValid(){
        val userName = "User1"
        val password = "User1"

        loginPresenter.saveUser(userName, password)

        verify(mockActivity, times(1)).showErrorMessageOnUsernameEditText()
    }

    @Test
    fun testShouldCallShowPasswordErrorIfPasswordIsNotValid(){
        val userName = "User"
        val password = "User"

        loginPresenter.saveUser(userName, password)

        verify(mockActivity, times(1)).showErrorMessageOnPasswordEditText()
    }

    @Test
    fun testShouldCallSuccessMessageIfUserIsSuccessfullySaved(){
        loginPresenter.showUserSuccessfullySaved()

        verify(mockActivity, times(1)).showSuccessMessage()
    }

    @Test
    fun testShouldCallUserAlreadyExistsIfUserIsAlreadyRegistered(){
        loginPresenter.showUserAlreadyExists()

        verify(mockActivity, times(1)).showUserExistsMessage()
    }

    @Test
    fun testShouldCallNavigate(){
        loginPresenter.showUserAlreadyExists()

        verify(mockActivity, times(1)).showUserExistsMessage()
    }

    @Test
    fun testShouldCallShowUsersOnTheScreen(){
        val users = listOf<User>()

        loginPresenter.showUsers(users)

        verify(mockActivity, times(1)).showUsersList(users)
    }

    @Test
    fun testShouldCallGetUsers(){
        loginPresenter.showUserSuccessfullySaved()

        verify(mockInteractor, times(1)).getUsers()
    }

}