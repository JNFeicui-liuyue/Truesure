package contacts.feicui.edu.truesure.treasure.home.detail;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

/**
 * 宝藏详情业务视图
 */
public interface TreasureDetailView extends MvpView{

    void showMessage(String msg);
    void setData(List<TreasureDetailResult> results);
}
