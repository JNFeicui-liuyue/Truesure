package contacts.feicui.edu.truesure.user.login;

import android.os.AsyncTask;

/**
 * 登录视图业务
 */
public class LoginPresenter {

    private LoginView loginView;

    public LoginPresenter(LoginView loginView){
        this.loginView = loginView;
    }

    /**本类核心业务*/
    public void login(){
       new LoginTask().execute();
    }

    private final class LoginTask extends AsyncTask<Void,Void,Integer>{

        // 在doInBackground之前,UI线程来调用
        @Override protected void onPreExecute() {
            super.onPreExecute();
            loginView.showProgress();
        }
        // 在onPreExecute之后, 后台线程来调用
        @Override
        protected Integer doInBackground(Void... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                return 0;
            }
            return 1;
        }

        // 在doInBackground之后,UI线程来调用
        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            if (integer == 0){
                loginView.showMessage("未知错误");
                loginView.hideProgress();
                return;
            }
            loginView.navigateToHome();
            loginView.hideProgress();
        }
    }
}
