package contacts.feicui.edu.truesure.user.register;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by liuyue on 2016/7/13.
 */
public interface RegisterView extends MvpView{

    /** 导航到HOME页面*/
    void navigateToHome();

    /** 显示注册中进度视图*/
    void showProgress();

    /** 隐藏注册中进度视图*/
    void hideProgress();

    /** 显示提示信息*/
    void showMessage(String msg);

}
