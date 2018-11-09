package uio.androidbootcamp.meetupapplication.presenters

class UsersPresenter(private val view: View) {

    fun initView() {
        view.showUsers()
    }

    interface View {
        fun showUsers()

    }

}