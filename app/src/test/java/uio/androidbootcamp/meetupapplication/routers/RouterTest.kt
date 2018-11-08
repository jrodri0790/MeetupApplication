package uio.androidbootcamp.meetupapplication.routers

import android.content.Intent
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.*
import uio.androidbootcamp.meetupapplication.models.User
import uio.androidbootcamp.meetupapplication.views.LoginActivity
import uio.androidbootcamp.meetupapplication.views.UsersActivity

class RouterTest{

    @Test
    fun shouldCallStartUsersActivityWithTheGivenData(){
        val mockActivity = mock(LoginActivity::class.java)
        val intent = Intent(mockActivity, UsersActivity::class.java)
        val router = Router()
        doNothing().`when`(mockActivity).startActivity(intent)
        val users = arrayListOf<User>()

        router.navigateToUsersActivity(mockActivity, users)

        verify(mockActivity, times(1)).startActivity(ArgumentMatchers.any(Intent::class.java))
    }

}