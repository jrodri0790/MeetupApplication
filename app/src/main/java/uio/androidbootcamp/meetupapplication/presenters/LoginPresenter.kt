package uio.androidbootcamp.meetupapplication.presenters

import uio.androidbootcamp.meetupapplication.interactors.SharedPreferencesInteractor
import uio.androidbootcamp.meetupapplication.models.User

class LoginPresenter(val view: LoginPresenterView, private val sharedPreferencesInteractor: SharedPreferencesInteractor) : SharedPreferencesInteractor.Presenter {
    private fun isValidUserName(username: String): Boolean {
        return username.matches(Regex("[a-zA-Z]+"))
    }

    private fun isValidPassword(password: String): Boolean {
        return password.matches(Regex(".*\\d+.*"))
    }

    private fun validateFields(username: String, password: String): Boolean {
        if (!isValidUserName(username)) {
            view.showErrorMessageOnUsernameEditText()
            return false
        }

        if (!isValidPassword(password)) {
            view.showErrorMessageOnPasswordEditText()
            return false
        }
        return true
    }

    fun saveUser(userName: String, password: String) {

        val areValidFields = validateFields(userName, password)
        if(areValidFields){
            val user = User(userName, password)
            sharedPreferencesInteractor.saveUser(user)
        }
    }

    override fun showUserAlreadyExists() {
        view.showUserExistsMessage()
    }

    override fun showUserSuccessfullySaved() {
        view.showSuccessMessage()
        sharedPreferencesInteractor.getUsers()
    }

    override fun showUsers(users: List<User>) {
        view.showUsersList(users)
    }

    interface LoginPresenterView{
        fun showErrorMessageOnUsernameEditText()
        fun showErrorMessageOnPasswordEditText()
        fun showSuccessMessage()
        fun showUserExistsMessage()
        fun showUsersList(users: List<User>)
    }

}
