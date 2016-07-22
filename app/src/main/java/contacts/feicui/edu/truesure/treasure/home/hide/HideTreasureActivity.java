package contacts.feicui.edu.truesure.treasure.home.hide;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.baidu.mapapi.model.LatLng;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import contacts.feicui.edu.truesure.R;
import contacts.feicui.edu.truesure.commons.ActivityUtils;
import contacts.feicui.edu.truesure.treasure.TreasureRepo;
import contacts.feicui.edu.truesure.user.UserPrefs;

public class HideTreasureActivity extends MvpActivity<HideTreasureView,HideTreasurePresenter> implements HideTreasureView{

    private static final String EXTRA_KEY_TITLE = "key_title";
    private static final String EXTRA_KEY_LOCATION = "key_location";
    private static final String EXTRA_KEY_LAT_LNG = "key_latlng";
    private static final String EXTRA_KEY_ALTITUDE = "key_altitude";

    /**
     * 进入当前Activity
     * Mvp
     * OkHttp
     * Retrofit
     */
    public static void open(Context context, String title, String location, LatLng latLng, double altitude) {
        Intent intent = new Intent(context, HideTreasureActivity.class);
        intent.putExtra(EXTRA_KEY_TITLE, title);
        intent.putExtra(EXTRA_KEY_LOCATION, location);
        intent.putExtra(EXTRA_KEY_LAT_LNG, latLng);
        intent.putExtra(EXTRA_KEY_ALTITUDE, altitude);
        context.startActivity(intent);
    }

    private ActivityUtils activityUtils;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.et_description) EditText etDdscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils = new ActivityUtils(this);
        setContentView(R.layout.activity_hide_treasure);
    }

    @Override public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getIntent().getStringExtra(EXTRA_KEY_TITLE));
        }
    }

        @NonNull @Override public HideTreasurePresenter createPresenter() {
        return new HideTreasurePresenter();
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_hide_treasure, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            // 确定上传
            case R.id.action_send:
                Intent preIntent = getIntent();
                //经纬度
                LatLng latLng = preIntent.getParcelableExtra(EXTRA_KEY_LAT_LNG);
                //高度
                double altitude = preIntent.getDoubleExtra(EXTRA_KEY_ALTITUDE, 0);
                //位置
                String location = preIntent.getStringExtra(EXTRA_KEY_LOCATION);
                //标题
                String title = preIntent.getStringExtra(EXTRA_KEY_TITLE);
                int tokenId = UserPrefs.getInstance().getTokenid();
                //描述信息
                String description = etDdscription.getText().toString();
                // 执行业务，首先封装出实体
                HideTreasure hideTreasure = new HideTreasure();
                hideTreasure.setLatitude(latLng.latitude);
                hideTreasure.setLongitude(latLng.longitude);
                hideTreasure.setAltitude(altitude);
                hideTreasure.setLocation(location);
                hideTreasure.setTitle(title);
                hideTreasure.setTokenId(tokenId);
                hideTreasure.setDescription(description);
                getPresenter().hideTreasure(hideTreasure);
                break;
        }
        return true;
    }

    private ProgressDialog progressDialog;

    @Override public void showProgress() {
        activityUtils.hideSoftKeyboard();
        progressDialog = ProgressDialog.show(this, "", "宝藏数据上传中,请稍后...");
    }

    @Override public void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override public void showMessage(String msg) {
        activityUtils.showToast(msg);
    }

    @Override public void navigateToHome() {
        //把自己关闭
        finish();
        // 清理宝藏仓库
        TreasureRepo.getInstance().clear();
    }
}
