package uio.androidbootcamp.meetupapplication.presenters

import uio.androidbootcamp.meetupapplication.views.LoginActivity

class LoginPresenter(val view: LoginActivity) {

    fun isValidUserName(username: String): Boolean {
        return username.matches(Regex("[a-zA-Z]+"))
    }

    fun isValidPassword(password: String): Boolean {
        return password.matches(Regex(".*\\d+.*"))
    }

    fun validateFields(username: String, password: String): Boolean {
        if (!isValidUserName(username)) {
            view.showErrorMessageOnUsernameEditText()
            return false
        }

        if (!isValidPassword(password)) {
            view.showErrorMessageOnPasswordEditText()
            return false
        }

        view.showSuccessMessage()
        return true
    }


}