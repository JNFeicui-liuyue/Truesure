package contacts.feicui.edu.truesure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import contacts.feicui.edu.truesure.commons.ActivityUtils;
import contacts.feicui.edu.truesure.user.LoginActivity;
import contacts.feicui.edu.truesure.user.RegisterActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityUtils mActivityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityUtils = new ActivityUtils(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_Login,R.id.btn_Register})
    public void click(View view){
        switch (view.getId()){
            case R.id.btn_Login:
                mActivityUtils.startActivity(LoginActivity.class);
                break;
            case R.id.btn_Register:
                mActivityUtils.startActivity(RegisterActivity.class);
                break;
        }
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
