package contacts.feicui.edu.truesure.user.register;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import java.io.IOException;

import contacts.feicui.edu.truesure.NetClient.NetClient;
import contacts.feicui.edu.truesure.user.User;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 注册相关业务，怎么和视图结合？
 */
public class RegisterPresenter extends MvpNullObjectBasePresenter<RegisterView>{

    private Handler mHandler = new Handler(Looper.getMainLooper());

    private Call mCall;
    private Gson mGson;

    public RegisterPresenter(){
        mGson = new Gson();
    }

    /**本类核心业务*/
    public void register(User user){
        OkHttpClient client = NetClient.getInstance().getClient();

        RequestBody body = RequestBody.create(null,mGson.toJson(user));

        //构建模式
        Request request = new Request.Builder()
                .url("http://admin.syfeicuiedu.com/Handler/UserHandler.ashx?action=register")
                .post(body)
                .build();

        mCall = client.newCall(request);
        mCall.enqueue(mCallback);

    }

    private Callback mCallback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            failure(e.getMessage());
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {

            if (response.isSuccessful()){
                String jsonStr = response.body().string();
                RegisterResult result = mGson.fromJson(jsonStr,RegisterResult.class);
                if (result.getCode() == 1){
                    success(result.getMsg());
                    return;
                }
                failure(result.getMsg());
                return;
            }
            failure("unknown error");
        }
    };

    public void failure(final String msg){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                getView().hideProgress();
                getView().showMessage(msg);
            }
        });
    }

    public void success(final String msg){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                getView().hideProgress();
                getView().showMessage(msg);
                getView().navigateToHome();
            }
        });
    }

//    private final class RegisterTask extends AsyncTask<Void,Void,Integer> {
//        // 在doInBackground之前,UI线程来调用
//        @Override protected void onPreExecute() {
//            super.onPreExecute();
//            getView().showProgress();
//        }
//        // 在onPreExecute之后, 后台线程来调用
//        @Override protected Integer doInBackground(Void... params) {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                return 0;
//            }
//            return 1;
//        }
//        // 在doInBackground之后,UI线程来调用
//        @Override protected void onPostExecute(Integer aVoid) {
//            super.onPostExecute(aVoid);
//            if (aVoid == 0) {
//                getView().showMessage("未知错误");
//                getView().hideProgress();
//                return;
//            }
//            getView().navigateToHome();
//            getView().hideProgress();
//        }
//    }
}
