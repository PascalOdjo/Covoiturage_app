import com.aristide.UserPasswordEncoderListener
import com.aristide.CustomUserDetailsService

beans = {
    userPasswordEncoderListener(UserPasswordEncoderListener)
    userDetailsService(CustomUserDetailsService)
}
