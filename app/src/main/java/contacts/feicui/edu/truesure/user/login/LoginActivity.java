package contacts.feicui.edu.truesure.user.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import contacts.feicui.edu.truesure.MainActivity;
import contacts.feicui.edu.truesure.R;
import contacts.feicui.edu.truesure.commons.ActivityUtils;
import contacts.feicui.edu.truesure.commons.RegexUtils;
import contacts.feicui.edu.truesure.home.HomeActivity;
import contacts.feicui.edu.truesure.user.User;

/**
 * 登录视图
 * 我们的登录业务，是不是只要针对LoginView来做就行了
 */
public class LoginActivity extends MvpActivity<LoginView,LoginPresenter> implements LoginView{

    @Bind(R.id.et_Password)EditText etPassword;
    @Bind(R.id.et_Username) EditText etUsername;
    @Bind(R.id.btn_Login) Button btnLogin;

    private String userName; // 用来存储用户名
    private String passWord; // 用来存储密码

    private ActivityUtils mActivityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mActivityUtils = new ActivityUtils(this);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        etPassword.addTextChangedListener(mTextWAtcher);
        etUsername.addTextChangedListener(mTextWAtcher);

    }

    @NonNull
    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    private final TextWatcher mTextWAtcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            userName = etUsername.getText().toString();
            passWord = etPassword.getText().toString();
            boolean canLogin = !(TextUtils.isEmpty(userName) || TextUtils.isEmpty(passWord));
            // 默认情况下Login按钮是未激活，不可点的
            btnLogin.setEnabled(canLogin);
        }
    };

    @OnClick(R.id.btn_Login)
    public void login(){
        // 用户名是否有效
        if (RegexUtils.verifyUsername(userName) != RegexUtils.VERIFY_SUCCESS) {
            mActivityUtils.showToast(R.string.username_rules);
            return;
        }
        // 密码是否有效
        if (RegexUtils.verifyPassword(passWord) != RegexUtils.VERIFY_SUCCESS) {
            mActivityUtils.showToast(R.string.password_rules);
            return;
        }
        //当前Activity已经实现了接口
//        new LoginPresenter().login();
        //分析用例----业务逻辑上的
        //分析你要干嘛
        //抽出接口（要做些什么）

        // 执行业务
        getPresenter().login(new User(userName,passWord));
        //1.提出一个视图接口
        //   当前Activity来实现这个接口
        /**
         * 执行网络连接
         * 连接网络
         * 如果出错-显示错误视图（View相关代码）
         * 读取数据
         *     如果出错-显示错误视图（View相关代码）
         * 完成
         *     成功
         *     数据交到视图上来显示（View相关代码）
         */
    }

    private ProgressDialog mProgressDialog;
    @Override
    public void showProgress() {

        //隐藏键盘
        mActivityUtils.hideSoftKeyboard();
        mProgressDialog = ProgressDialog.show(this, "", "登陆中,请稍后...");
    }

    @Override
    public void hideProgress() {

        if (mProgressDialog != null){
            mProgressDialog.dismiss();
        }

    }

    @Override
    public void showMessage(String msg) {

        mActivityUtils.showToast(msg);
    }

    @Override
    public void navigateToHome() {
        // 切换到Home页面
        mActivityUtils.startActivity(HomeActivity.class);
        // 关闭当前页面
        finish();
        //关闭入口Main页面(通过发送本地广播)
        Intent intent = new Intent(MainActivity.ACTION_ENTER_HOME);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

    }
}
