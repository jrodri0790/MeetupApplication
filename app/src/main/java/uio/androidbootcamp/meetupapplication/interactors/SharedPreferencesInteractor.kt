package uio.androidbootcamp.meetupapplication.interactors

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uio.androidbootcamp.meetupapplication.R
import uio.androidbootcamp.meetupapplication.models.User


class SharedPreferencesInteractor(private val context: Context, private val gson: Gson) {


    lateinit var presenter:Presenter

    private fun saveStringInSharedPreferences(key: String, value:String) {
        val sharedPref = context.getSharedPreferences(
                context.getString(R.string.package_name),
                Context.MODE_PRIVATE)
        val editor =  sharedPref.edit()
        editor.putString(key, value)
        editor.apply()
    }

   private  fun getStringFromSharedPreferences(key: String) : String{
        val sharedPref = context.getSharedPreferences(
                context.getString(R.string.package_name),
                Context.MODE_PRIVATE)
        return sharedPref.getString(key, "[]")
    }

    private fun saveUsersInSharedPreferences(key: String, users: List<User>) {
        val jsonifiedUsers = gson.toJson(users)
        saveStringInSharedPreferences(key, jsonifiedUsers)
    }

    private fun obtainUsersFromSharedPreferences(key: String): List<User> {
        val listType = object : TypeToken<List<User>>() {}.type
        val gsonValue = getStringFromSharedPreferences(key)
        return gson.fromJson(gsonValue, listType)

    }

    fun saveUser(user: User) {
        val users = obtainUsersFromSharedPreferences("users")
        if(user in users){
            presenter.showUserAlreadyExists()
        }else{
            val  usersToAdd = users.plus(user)
            saveUsersInSharedPreferences("users", usersToAdd)
            presenter.showUserSuccessfullySaved()
        }
    }

    fun getUsers() {
        val users = obtainUsersFromSharedPreferences("users")
        presenter.showUsers(users)
    }

    interface Presenter{
        fun showUserAlreadyExists()
        fun showUserSuccessfullySaved()
        fun showUsers(users: List<User>)
    }


}