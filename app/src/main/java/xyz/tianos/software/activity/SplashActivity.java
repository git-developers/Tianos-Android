package xyz.tianos.software.activity;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends BaseActivity {

    private static final int SPLASH_SCREEN_TIMEOUT = 2000;
    private static final String TAG = "SplashActivity";

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

            intent.setClass(SplashActivity.this, ListPointOfSaleActivity.class);

            /*
            switch (user.getRole()){
                case Const.ROLE_FATHER:
                    intent.setClass(SplashActivity.this, ListPointOfSaleActivity.class);
                    break;
                case Const.ROLE_TEACHER:
                    intent.setClass(SplashActivity.this, CoursesActivity.class);
                    break;
                default:
                    Utils.shortToast(SplashActivity.this, "El usuario no tiene un rol");
                    break;
            }
            */
        }else{
//            intent.setClass(SplashActivity.this, LoginActivity.class);
            intent.setClass(SplashActivity.this, ApiActivity.class);
        }

        //REDIRECT - JAFETH
//        intent.setClass(SplashActivity.this, ModulesSeleccionActivity.class);

        startActivity(intent);
        SplashActivity.this.finish();

        /* Apply our act_1_splash exit (fade out) and menu_reports entry (fade in) animation transitions. */
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

    }


}
