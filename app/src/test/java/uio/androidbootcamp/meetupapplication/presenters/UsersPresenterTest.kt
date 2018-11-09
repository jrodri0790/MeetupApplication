package uio.androidbootcamp.meetupapplication.presenters

import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.*
import uio.androidbootcamp.meetupapplication.views.UsersActivity

class UsersPresenterTest{


    @Test
    fun testShouldCallShowUsers(){
        val mockUsersActivity = mock(UsersActivity::class.java)
        val presenter = UsersPresenter(mockUsersActivity)

        presenter.initView()

        verify(mockUsersActivity, times(1)).showUsers()

    }
}