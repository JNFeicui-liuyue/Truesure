package contacts.feicui.edu.truesure.treasure.home.map;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

import contacts.feicui.edu.truesure.treasure.Treasure;

/**
 * 作者：yuanchao on 2016/7/19 0019 10:22
 * 邮箱：yuanchao@feicuiedu.com
 */
public interface MapMvpView extends MvpView{

    void showMessage(String msg);

    void setData(List<Treasure> datas);
}
