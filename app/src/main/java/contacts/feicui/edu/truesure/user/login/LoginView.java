package contacts.feicui.edu.truesure.user.login;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * 登录业务相关视图
 */
public interface LoginView extends MvpView{

    //显示登录中的loading视图
    void showProgress();

    //隐藏登录中的loading视图
    void hideProgress();

    //显示信息
    void showMessage(String msg);

    //导航到home页面
    void navigateToHome();


}
