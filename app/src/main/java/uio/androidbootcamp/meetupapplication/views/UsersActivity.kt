package uio.androidbootcamp.meetupapplication.views

import android.app.ListActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import uio.androidbootcamp.meetupapplication.R
import uio.androidbootcamp.meetupapplication.models.User
import uio.androidbootcamp.meetupapplication.presenters.UsersPresenter

class UsersActivity : ListActivity(), UsersPresenter.View {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_list)
        val presenter = UsersPresenter(this)
        presenter.initView()
    }

    override fun showUsers() {
        val users = intent.getSerializableExtra("users") as ArrayList<User>
        val userNames = users.map { it.username }
        val myAdapter =  ArrayAdapter <String>(this, R.layout.item_list_user, R.id.txt_user_name, userNames)
        listAdapter = myAdapter
    }
}
