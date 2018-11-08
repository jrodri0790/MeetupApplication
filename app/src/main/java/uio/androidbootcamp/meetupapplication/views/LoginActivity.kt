package uio.androidbootcamp.meetupapplication.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import uio.androidbootcamp.meetupapplication.R
import uio.androidbootcamp.meetupapplication.interactors.SharedPreferencesInteractor
import uio.androidbootcamp.meetupapplication.models.User
import uio.androidbootcamp.meetupapplication.presenters.LoginPresenter
import uio.androidbootcamp.meetupapplication.routers.Router
import uio.androidbootcamp.meetupapplication.utils.GsonCreator

class LoginActivity : AppCompatActivity(), LoginPresenter.LoginPresenterView {
    private val gsonObject = GsonCreator.create()

    private lateinit var presenter : LoginPresenter

    private lateinit var sharedPreferencesInteractor : SharedPreferencesInteractor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferencesInteractor = SharedPreferencesInteractor(this, gsonObject)
        presenter = LoginPresenter(this, sharedPreferencesInteractor)
        sharedPreferencesInteractor.presenter = presenter
        setContentView(R.layout.activity_login)
        btn_validate.setOnClickListener { presenter.saveUser(et_username.text.toString(), et_password.text.toString()) }
    }
    override fun showErrorMessageOnUsernameEditText() {
        et_username.error = "El nombre de usuario no puede contener números"
    }

    override fun showErrorMessageOnPasswordEditText() {
        et_password.error = "El password debe contener al menos un número"
    }

    override fun showSuccessMessage() {
        Toast.makeText(this, "Campos válidos", Toast.LENGTH_LONG).show()
    }

    override fun showUserExistsMessage() {
        Toast.makeText(this, "Usuario ya existe", Toast.LENGTH_LONG).show()
    }

    override fun showUsersList(users: List<User>) {
        val router = Router()
        val usersArray = arrayListOf<User>()
        usersArray.addAll(users)
        router.navigateToUsersActivity(this, usersArray)
    }
}
