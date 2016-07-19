package contacts.feicui.edu.truesure.user.account;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by liuyue on 2016/7/18.
 */
public interface AccountView extends MvpView {

    void showProgress();

    void hideProgress();

    void showMessage(String msg);

    /*更新头像*/
    void updatePhoto(String url);

}
