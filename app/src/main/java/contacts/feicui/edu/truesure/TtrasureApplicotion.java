package contacts.feicui.edu.truesure;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import contacts.feicui.edu.truesure.user.UserPrefs;

/**
 * Created by liuyue on 2016/7/12.
 */
public class TtrasureApplicotion extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        UserPrefs.init(this);
        initImageLoader();
    }

    private void initImageLoader() {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnFail(R.drawable.user_icon)
                .showImageOnFail(R.id.user_name)
                .showImageForEmptyUri(R.id.user_name)
//                .displayer(new RoundedBitmapDisplayer(getResources().getDimensionPixelOffset(R.dimen.dp_10)))
                .cacheOnDisk(true)//打开Disk
                .cacheInMemory(true)//打开cache
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheSize(5*1024*1024)//设置内存缓存5MB
                .defaultDisplayImageOptions(options)//设置默认的显示选项
                .build();
        ImageLoader.getInstance().init(config);
    }
}
