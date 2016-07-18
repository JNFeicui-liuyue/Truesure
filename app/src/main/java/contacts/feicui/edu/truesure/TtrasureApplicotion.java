package contacts.feicui.edu.truesure;

import android.app.Application;

import contacts.feicui.edu.truesure.user.UserPrefs;

/**
 * Created by liuyue on 2016/7/12.
 */
public class TtrasureApplicotion extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        UserPrefs.init(this);
    }
}
