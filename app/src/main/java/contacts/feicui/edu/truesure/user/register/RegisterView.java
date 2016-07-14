package contacts.feicui.edu.truesure.user.register;

/**
 * Created by liuyue on 2016/7/13.
 */
public interface RegisterView {

    /** 导航到HOME页面*/
    void navigateToHome();

    /** 显示注册中进度视图*/
    void showProgress();

    /** 隐藏注册中进度视图*/
    void hideProgress();

    /** 显示提示信息*/
    void showMessage(String msg);

}