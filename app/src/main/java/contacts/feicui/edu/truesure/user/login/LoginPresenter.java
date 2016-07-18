package contacts.feicui.edu.truesure.user.login;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import contacts.feicui.edu.truesure.NetClient.NetClient;
import contacts.feicui.edu.truesure.user.User;
import contacts.feicui.edu.truesure.user.UserApi;
import contacts.feicui.edu.truesure.user.UserPrefs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 登录视图业务
 */
public class LoginPresenter extends MvpNullObjectBasePresenter<LoginView>{

    private Call<LoginResult> loginCall;

    /**
     * 本类核心业务
     */
    public void login(User user){

        UserApi userApi = NetClient.getInstance().getUserApi();
        if (loginCall != null) loginCall.cancel();
        // 执行登陆请求构建出call模型
        loginCall = userApi.login(user);
        // Call模型异步执行
        loginCall.enqueue(callback);

    }

    private Callback<LoginResult> callback = new Callback<LoginResult>() {
        @Override
        public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
            getView().hideProgress();
            // 是否成功
            if (response.isSuccessful()) {
                // 取出响应体(retrofit已加了gson转换器的,注意接口的定义)
                LoginResult result = response.body();
                if (result == null) {
                    getView().showMessage("unknown error");
                    return;
                }
                getView().showMessage(result.getMsg());
                // 登陆成功，保存登录信息
                if (result.getCode() == 1) {
                    UserPrefs.getInstance().setPhoto(NetClient.BASE_URL + result.getIconUrl());
                    UserPrefs.getInstance().setTokenid(result.getTokenId());
                    getView().navigateToHome();
                }
                return;
            }
            getView().showMessage("网络异常");
        }

        @Override
        public void onFailure(Call<LoginResult> call, Throwable t) {
            getView().hideProgress();
            getView().showMessage(t.getMessage());
        }
    };

    @Override public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (loginCall != null) {
            loginCall.cancel();
        }
    }


//    private User mUser;
//    private Call mCall;
//    private Gson mGson;
//
//
//    /**本类核心业务*/
//    public void login(User user){
//        mUser = user;
//        mGson = new Gson();
////       new LoginTask().execute();
//
//    }
//
//    private final class LoginTask extends AsyncTask<Void,Void,LoginResult>{
//
//        // 在doInBackground之前,UI线程来调用
//        @Override protected void onPreExecute() {
//            super.onPreExecute();
//            getView().showProgress();
//        }
//        // 在onPreExecute之后, 后台线程来调用
//        @Override
//        protected LoginResult doInBackground(Void... params) {
//
//            OkHttpClient client = NetClient.getInstance().getClient();
//
//            String content = mGson.toJson(mUser);
//            MediaType type = MediaType.parse("treasure/json");
//            //请求体（根据文档，{username:sss,password:mmm}）
//            RequestBody body = RequestBody.create(type,content);
//            //请求
//            Request request = new Request.Builder()
//                    .url("http://admin.syfeicuiedu.com/Handler/UserHandler.ashx?action=login")
//                    .post(body)
//                    .build();
//            if (mCall != null) mCall.cancel();
//            mCall = client.newCall(request);
//            try {
//                //执行（同步）得到响应
//                Response response = mCall.execute();
//                if (response == null){
//                    return null;
//                }
//                if (response.isSuccessful()){
//                    //取出响应体中的数据（json字符串）
//                    String strResult = response.body().string();
//                    //gson处理一下
//                    LoginResult result = mGson.fromJson(strResult,LoginResult.class);
//                    return result;
//                }
//            } catch (IOException e) {
//                return null;
//            }
//            return null;
//        }
//
//        // 在doInBackground之后,UI线程来调用
//        @Override
//        protected void onPostExecute(LoginResult result) {
//            super.onPostExecute(result);
//            if (result == null) {
//                getView().hideProgress();
//                if (result == null) {
//                    getView().showMessage("未知错误！");
//                    return;
//                }
//            }
//            getView().showMessage(result.getMsg());
//            if (result.getCode() == 1){
//                //存住result里的一些重要数据
//                getView().navigateToHome();
//            }
//
//        }
//    }
//
//    @Override
//    public void detachView(boolean retainInstance) {
//        super.detachView(retainInstance);
//        if (mCall != null){
//            mCall.cancel();
//        }
//    }

}
