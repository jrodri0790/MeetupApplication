package uio.androidbootcamp.meetupapplication.routers

import android.content.Intent
import uio.androidbootcamp.meetupapplication.models.User
import uio.androidbootcamp.meetupapplication.views.LoginActivity
import uio.androidbootcamp.meetupapplication.views.UsersActivity

class Router {
    fun navigateToUsersActivity(originActivity: LoginActivity, users: ArrayList<User>) {
        val intent = Intent(originActivity, UsersActivity::class.java)
        intent.putExtra("users", users)
        originActivity.startActivity(intent)
    }
}