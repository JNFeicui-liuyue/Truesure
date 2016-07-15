package contacts.feicui.edu.truesure.NetClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import contacts.feicui.edu.truesure.user.UserApi;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 1.添加依赖
 * 2.实例化Retrofit
 * 3.将用户模块API，转为Java接口
 * 4.通过Call拿到对象
 */
public class NetClient {

    public static String BASE_URL = "http://admin.syfeicuiedu.com";
    private static NetClient sNetClient;
    private final OkHttpClient mClient;
    private final Retrofit mRetrofit;

    private final Gson gson;

    private NetClient(){
        // 非严格模式
        gson = new GsonBuilder().setLenient().create();

        mClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                //添加Gson转换器（要添加依赖）
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(mClient)
                .build();
    }

    public static NetClient getInstance(){
        if (sNetClient == null) {
            sNetClient = new NetClient();
        }
        return sNetClient;
    }

    private UserApi userApi;

    /**
     * 获取用户模型API对象
     */
    public UserApi getUserApi() {
        if (userApi == null) {
            // retrofit核心代码
            // 将http api转化的java接口进行代码构建(根据注解等)
            userApi = mRetrofit.create(UserApi.class);
        }
        return userApi;
    }

    public OkHttpClient getClient(){
        return mClient;
    }
}
