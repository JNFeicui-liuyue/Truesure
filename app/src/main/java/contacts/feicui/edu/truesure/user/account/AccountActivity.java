package contacts.feicui.edu.truesure.user.account;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.hybridsquad.android.library.CropHandler;
import org.hybridsquad.android.library.CropHelper;
import org.hybridsquad.android.library.CropParams;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import contacts.feicui.edu.truesure.R;
import contacts.feicui.edu.truesure.commons.ActivityUtils;
import contacts.feicui.edu.truesure.components.IconSelectWindow;

/**
 * 个人用户信息页面
 */
public class AccountActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar toolbar;

    private ActivityUtils activityUtils;
    private IconSelectWindow iconSelectWindow; // 按下icon，弹出的POPUOWINDOW
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils = new ActivityUtils(this);
        setContentView(R.layout.activity_account);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getTitle());
    }

    /**
     * 当在当前个人用户中心页面，按下icon，弹出POPUOWINDOW
     */
    @OnClick(R.id.iv_userIcon)
    public void onClick(){
        if (iconSelectWindow == null) iconSelectWindow = new IconSelectWindow(this,listener);
        if (iconSelectWindow.isShowing()) {
            iconSelectWindow.dismiss();
            return;
        }
        iconSelectWindow.show();
    }

    private CropHandler mCropHandler = new CropHandler() {
        //剪切完成
        @Override
        public void onPhotoCropped(Uri uri) {
            File file = new File(uri.getPath());
            activityUtils.showToast(file.getPath());

        }

        //剪切取消
        @Override
        public void onCropCancel() {

            activityUtils.showToast("onCropCancel");
        }

        @Override
        public void onCropFailed(String message) {

            activityUtils.showToast("onCropFailed");
        }

        //剪切失败
        @Override
        public CropParams getCropParams() {
            CropParams cropParams = new CropParams();
            cropParams.aspectX = 300;
            cropParams.aspectY = 300;
            return cropParams;
        }

        @Override
        public Activity getContext() {
           return AccountActivity.this;
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CropHelper.handleResult(mCropHandler,requestCode, resultCode, data);
    }

    private final IconSelectWindow.Listener listener = new IconSelectWindow.Listener() {
        // 到相册进行头像选择
        @Override
        public void toGallery() {
//            activityUtils.showToast("toGallery");
            Intent intent = CropHelper.buildCropFromGalleryIntent(mCropHandler.getCropParams());
            startActivityForResult(intent,CropHelper.REQUEST_CROP);

        }

        // 到相机进行头像选择
        @Override
        public void toCamera() {
//            activityUtils.showToast("toCamera");
            //使用前先清理缓存
            CropHelper.clearCachedCropFile(mCropHandler.getCropParams().uri);
            Intent intent = CropHelper.buildCaptureIntent(mCropHandler.getCropParams().uri);
            startActivityForResult(intent, CropHelper.REQUEST_CAMERA);
        }
    };
}
