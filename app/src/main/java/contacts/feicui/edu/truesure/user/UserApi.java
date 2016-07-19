package contacts.feicui.edu.truesure.user;

import contacts.feicui.edu.truesure.user.account.Update;
import contacts.feicui.edu.truesure.user.account.UpdateResult;
import contacts.feicui.edu.truesure.user.account.UploadResult;
import contacts.feicui.edu.truesure.user.login.LoginResult;
import contacts.feicui.edu.truesure.user.register.RegisterResult;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * 将用户模块API，转为Java接口
 */
public interface UserApi {

    @POST("/Handler/UserHandler.ashx?action=register")
    Call<RegisterResult> register(@Body User user);//添加了Gson转换器，可自动转换

    @POST("/Handler/UserHandler.ashx?action=login")
    Call<LoginResult> login(@Body User user);

    // 头像上传(是一个多部分请求)
    @Multipart
    @POST("/Handler/UserLoadPicHandler1.ashx")
    Call<UploadResult> upload(@Part MultipartBody.Part part);

    // 更新头像
    @POST("/Handler/UserHandler.ashx?action=update")
    Call<UpdateResult> update(@Body Update update);
}
