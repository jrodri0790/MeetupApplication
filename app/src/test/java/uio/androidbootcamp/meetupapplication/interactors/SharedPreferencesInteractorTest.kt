package uio.androidbootcamp.meetupapplication.interactors

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*
import uio.androidbootcamp.meetupapplication.R
import uio.androidbootcamp.meetupapplication.models.User

class SharedPreferencesInteractorTest{

    private lateinit var context: Context
    private lateinit var key: String
    private lateinit var value: String
    private lateinit var username: String
    private lateinit var password: String
    private lateinit var emptyArray: String
    private lateinit var interactor: SharedPreferencesInteractor
    private lateinit var mockSharedPreferences: SharedPreferences
    private lateinit var mockEditor: SharedPreferences.Editor
    private lateinit var mockGson: Gson
    private lateinit var mockPresenter : SharedPreferencesInteractor.Presenter

    private fun getTestContext(): Context {
        val application: Application = Mockito.mock(Application::class.java)
        Mockito.`when`(application.getString(R.string.package_name)).thenReturn("any string")
        Mockito.`when`(application.getSharedPreferences(any(String :: class.java), any(Int :: class.java))).thenReturn(mockSharedPreferences)
        return application
    }

    @Before
    fun init(){
        mockSharedPreferences = mock(SharedPreferences::class.java)
        mockEditor = Mockito.mock(SharedPreferences.Editor::class.java)
        mockPresenter = mock(SharedPreferencesInteractor.Presenter::class.java)
        context = getTestContext()
        key = "users"
        value = "value1"
        username = "username"
        password = "password"
        emptyArray = "[]"
        mockGson = mock(Gson::class.java)
        interactor = SharedPreferencesInteractor(context, mockGson)
        interactor.presenter = mockPresenter

    }

    @Test
    fun shouldNotifyUserAlreadyExistsIfUserAlreadyExistsInSharedPreferences(){
        val user = User(username, password)
        val jsonifiedUsers = "[{username:$username, password:$password}]"
        `when`(mockSharedPreferences.getString(key, emptyArray)).thenReturn(jsonifiedUsers)
        val expectedUsers = listOf(User(username, password))
        val listType = object : TypeToken<List<User>>() {}.type
        `when`(mockGson.fromJson<List<User>>(jsonifiedUsers, listType)).thenReturn(expectedUsers)

        interactor.saveUser(user)

        verify(mockPresenter, times(1)).showUserAlreadyExists()
    }

    @Test
    fun shouldSaveUserIfUserIsNotInSharedPreferences(){
        val newUsername = "NameOne"
        val newPassword = "Password2"
        val userToAdd = User(newUsername, newPassword)
        val jsonifiedUsers = "[{username:$username, password:$password}]"
        `when`(mockSharedPreferences.getString(key, emptyArray)).thenReturn(jsonifiedUsers)
        val expectedUsers = listOf(User(username, password))
        val listType = object : TypeToken<List<User>>() {}.type
        `when`(mockGson.fromJson<List<User>>(jsonifiedUsers, listType)).thenReturn(expectedUsers)
        `when`(mockSharedPreferences.edit()).thenReturn(mockEditor)
        val users = listOf(User(username, password), userToAdd)
        val jsonifiedUsersToSave = "[{username:$username, password:$password}, {username:$newUsername, password:$newPassword}]"
        `when`(mockGson.toJson(users)).thenReturn(jsonifiedUsersToSave)

        interactor.saveUser(userToAdd)

        verify(mockEditor, times(1)).putString(key, jsonifiedUsersToSave)
        verify(mockEditor, times(1)).apply()
        verify(mockPresenter, times(1)).showUserSuccessfullySaved()
    }

    @Test
    fun shouldCallMethodToShowUsersWhenUsersAreRetrievedFromSharedPreferences(){
        val jsonifiedUsers = "[{username:$username, password:$password}]"
        `when`(mockSharedPreferences.getString(key, emptyArray)).thenReturn(jsonifiedUsers)
        val expectedUsers = listOf(User(username, password))
        val listType = object : TypeToken<List<User>>() {}.type
        `when`(mockGson.fromJson<List<User>>(jsonifiedUsers, listType)).thenReturn(expectedUsers)

        interactor.getUsers()

        verify(mockPresenter, times(1)).showUsers(expectedUsers)
    }
}