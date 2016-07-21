package contacts.feicui.edu.truesure.treasure.home;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;
import contacts.feicui.edu.truesure.R;
import contacts.feicui.edu.truesure.commons.ActivityUtils;
import contacts.feicui.edu.truesure.user.UserPrefs;
import contacts.feicui.edu.truesure.user.account.AccountActivity;

/**
 * 直接使用统一的keystore进行开发
 * 1.将这个统一的keystore放到项目里（指定使用的是这一个）
 * 2.将使用这个统一的keystore的sha1值去做申请
 * 在使用BaiduMap时，首先会去做验证（明文keystore和密文sha1是否是一个）
 *
 * 1.申请，配置
 *
 */
public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.drawer_layout) DrawerLayout drawerLayout;
    @Bind(R.id.nav_view) NavigationView navigationView;

    private ActivityUtils activityUtils;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils = new ActivityUtils(this);
        setContentView(R.layout.activity_home);

    }

    @Override
    protected void onStart() {
        super.onStart();
        //每次重新进入个人中心，更新用户头像
        String photoUrl = UserPrefs.getInstance().getPhoto();
        if (photoUrl != null){
            ImageLoader.getInstance().displayImage(photoUrl,imageView);
        }
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//关闭Title
        navigationView.setNavigationItemSelectedListener(this);//监听

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        //在滑的过程中可以同步这种状态
        toggle.syncState();

        imageView = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.iv_userIcon);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityUtils.startActivity(AccountActivity.class);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_hide: //埋藏宝藏
                activityUtils.showToast(R.string.hide_treasure);
                break;
        }
        // 返回true,当前选项变为checked状态
        return false;
    }

    //按下back按钮关闭侧滑菜单
    @Override
    public void onBackPressed() {
        // 是不是打开的(左)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
            return;
        }
        super.onBackPressed();
    }
}