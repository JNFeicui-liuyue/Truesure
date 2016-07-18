package contacts.feicui.edu.truesure.user;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 用户仓库，使用前必须先对其进行初始化init(),否则就报错啦！！！
 */
public class UserPrefs {

    private final SharedPreferences mPreferences;

    private static  UserPrefs sUserPrefs;

    public static void init(Context context){
        sUserPrefs = new UserPrefs(context);
    }

    private static final String PREFS_NAME = "user_info";

    private static final String KEY_TOKENID = "key_tokenid";
    private static final String KEY_PHOTO = "key_photo";

    private UserPrefs(Context context){
        mPreferences = context.getApplicationContext().
                getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
    }

    public static UserPrefs getInstance() {
        return sUserPrefs;
    }

    public void setTokenid(int tokenid) {
        mPreferences.edit().putInt(KEY_TOKENID, tokenid).apply();
    }

    public void setPhoto(String photoUrl) {
        mPreferences.edit().putString(KEY_PHOTO, photoUrl).apply();
    }

    public int getTokenid() {
        return mPreferences.getInt(KEY_TOKENID, -1);
    }

    public String getPhoto() {
        return mPreferences.getString(KEY_PHOTO, null);
    }
}
