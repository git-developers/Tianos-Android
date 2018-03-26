package xyz.tianos.software.activity;

import android.content.Intent;
import android.os.Bundle;

import xyz.tianos.software.utils.Const;
import xyz.tianos.software.utils.Utils;

public class SplashActivity extends BaseActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();
    private static final int SPLASH_SCREEN_TIMEOUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_1_splash);
        toolBar("Splash", R.string.app_name);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(SPLASH_SCREEN_TIMEOUT);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    isLogged();
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

    private void isLogged() {
        Intent intent = new Intent();
        boolean isLogged = getSharePreferencesIsLogged();

        if(user != null && isLogged){

            int activity = getSharePreferencesActivity();

            switch (activity){
                case Const.ACTIVITY_POINT_OF_SALE:
                    intent.setClass(SplashActivity.this, PointOfSaleActivity.class);
                    break;
                case Const.ACTIVITY_CATEGORY:
                    intent.setClass(SplashActivity.this, CategoryActivity.class);
                    break;
                default:
                    intent.setClass(SplashActivity.this, LoginActivity.class);
                    break;
            }

        }else{
            intent.setClass(SplashActivity.this, LoginActivity.class);
        }

        startActivity(intent);
        SplashActivity.this.finish();

        /* Apply our act_1_splash exit (fade out) and menu_reports entry (fade in) animation transitions. */
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

    }


}
