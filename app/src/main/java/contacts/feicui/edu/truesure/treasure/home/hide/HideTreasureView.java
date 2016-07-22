package contacts.feicui.edu.truesure.treasure.home.hide;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by liuyue on 2016/7/22.
 */
public interface HideTreasureView extends MvpView{

    void showProgress();
    void hideProgress();
    void showMessage(String msg);
    void navigateToHome();
}
