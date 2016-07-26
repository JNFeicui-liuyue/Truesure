package contacts.feicui.edu.truesure;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import contacts.feicui.edu.truesure.commons.ActivityUtils;
import contacts.feicui.edu.truesure.user.login.LoginActivity;
import contacts.feicui.edu.truesure.user.register.RegisterActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityUtils mActivityUtils;

    public static final String ACTION_ENTER_HOME = "action.enter.home";

    //广播接收器（当登陆和注册成功后，将发送出广播）
    //接收到后，关闭当前页面
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityUtils = new ActivityUtils(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //注册本地广播接收器
        IntentFilter intentFilter = new IntentFilter(ACTION_ENTER_HOME);
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver,intentFilter);
    }

    @OnClick({R.id.btn_Login,R.id.btn_Register})
    public void click(View view){
        switch (view.getId()){
            case R.id.btn_Login:
                mActivityUtils.startActivity(LoginActivity.class);
                break;
            case R.id.btn_Register:
                mActivityUtils.startActivity(RegisterActivity.class);
                break;
        }
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
