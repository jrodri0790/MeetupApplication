package uio.androidbootcamp.meetupapplication.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import uio.androidbootcamp.meetupapplication.R
import uio.androidbootcamp.meetupapplication.presenters.LoginPresenter

class LoginActivity : AppCompatActivity() {

    val presenter = LoginPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_validate.setOnClickListener { presenter.validateFields(et_username.text.toString(), et_password.text.toString()) }
    }

    fun showErrorMessageOnUsernameEditText() {
        et_username.error = "El nombre de usuario no puede contener números"
    }

    fun showErrorMessageOnPasswordEditText() {
        et_password.error = "El password debe contener al menos un número"
    }

    fun showSuccessMessage() {
        Toast.makeText(this, "Campos válidos", Toast.LENGTH_LONG).show()
    }
}
