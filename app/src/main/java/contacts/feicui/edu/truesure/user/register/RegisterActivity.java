package contacts.feicui.edu.truesure.user.register;

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
import contacts.feicui.edu.truesure.treasure.home.HomeActivity;
import contacts.feicui.edu.truesure.user.User;

/**
 * 注册视图
 * 1.完成注册的视图
 * 2.考虑注册相关业务及视图（接口）
 *   要做什么功能？
 *       注册
 *       网络连接，验证       视图表现（显示一个loading）
 *       出错了               试图表现
 *       通过了
 *         读取，解析数据
 *               出错了       试图表现
 *               通过了（得到数据了）    视图表现（进入Home页面）
 *
 * Okhttp库
 * 是我们使用的网络连接框架基栈技术
 *
 */
public class RegisterActivity extends MvpActivity<RegisterView, RegisterPresenter> implements RegisterView {

    @Bind(R.id.et_Username)EditText etUsername;
    @Bind(R.id.et_Password)EditText etPassword;
    @Bind(R.id.et_Confirm)EditText etConfirm;
    @Bind(R.id.btn_Register)Button btnRegister;

    private String username; // 用来保存编辑框内的用户名
    private String password; // 用来保存编辑框内的密码

    private ActivityUtils mActivityUtils;// Activity常用工具集

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mActivityUtils = new ActivityUtils(this);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        etConfirm.addTextChangedListener(mTextWatcher);// EditText监听
        etPassword.addTextChangedListener(mTextWatcher); // EditText监听
        etUsername.addTextChangedListener(mTextWatcher); // EditText监听
    }

    @NonNull
    @Override
    public RegisterPresenter createPresenter() {
        return new RegisterPresenter();
    }

    private final TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            username = etUsername.getText().toString();
            password = etPassword.getText().toString();
            String confirm = etConfirm.getText().toString();
            boolean canRegister = !TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)
                    && password.equals(confirm);
            btnRegister.setEnabled(canRegister);// 注意：在布局内注册按钮默认是不可用的
        }
    };

    @OnClick(R.id.btn_Register)
    public void register(){
        //正则进行判断输入的用户名是否有效
        if (RegexUtils.verifyUsername(username) != RegexUtils.VERIFY_SUCCESS){
            mActivityUtils.showToast(R.string.username_rules);
            return;
        }
        //正则进行判断输入的密码是否有效
        if (RegexUtils.verifyUsername(password) != RegexUtils.VERIFY_SUCCESS){
            mActivityUtils.showToast(R.string.username_rules);
            return;
        }
        // 执行注册业务逻辑
//        new RegisterPresenter(this).register();
        getPresenter().register(new User(username,password));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
    private ProgressDialog progressDialog;

    @Override
    public void navigateToHome() {
        mActivityUtils.startActivity(HomeActivity.class);
        finish();
        //关闭入口Main页面(通过发送本地广播)
        Intent intent = new Intent(MainActivity.ACTION_ENTER_HOME);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    @Override
    public void showProgress() {
        mActivityUtils.hideSoftKeyboard();
        progressDialog = ProgressDialog.show(this, "", "注册中,请稍后...");
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showMessage(String msg) {
        mActivityUtils.showToast(msg);
    }


}
