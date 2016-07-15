package contacts.feicui.edu.truesure.NetClient;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by liuyue on 2016/7/15.
 */
public class NetClient {

    private static NetClient sNetClient;
    private final OkHttpClient mClient;

    private NetClient(){
        mClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .build();
    }

    public static NetClient getInstance(){
        if (sNetClient == null) {
            sNetClient = new NetClient();
        }
        return sNetClient;
    }

    public OkHttpClient getClient(){
        return mClient;
    }
}
