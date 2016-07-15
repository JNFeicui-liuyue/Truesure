package contacts.feicui.edu.truesure.user;

import contacts.feicui.edu.truesure.user.login.LoginResult;
import contacts.feicui.edu.truesure.user.register.RegisterResult;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 将用户模块API，转为Java接口
 */
public interface UserApi {

    @POST("/Handler/UserHandler.ashx?action=register")
    Call<RegisterResult> register(@Body User user);//添加了Gson转换器，可自动转换

    @POST("/Handler/UserHandler.ashx?action=login")
    Call<LoginResult> login(@Body User user);
}
