package xyz.tianos.software.activity;

import android.content.Intent;
import android.os.Bundle;

import xyz.tianos.software.utils.Const;
import xyz.tianos.software.utils.Utils;

public class NotificationActivity extends BaseActivity {

    private static final int SPLASH_SCREEN_TIMEOUT = 3500;
    private static final String TAG = "NotificationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_1_splash);
        toolBar("Notification", R.string.app_name);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(SPLASH_SCREEN_TIMEOUT);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    gotoIntent();
                }
            }
        };
        timerThread.start();

    }

    private void gotoIntent() {

        String idOffer = "";

        Intent startingIntent = getIntent();
        if (startingIntent != null) {
            idOffer = startingIntent.getStringExtra("text");
        }

        Intent intent = new Intent();

        switch (user.getRole()){
            case Const.ROLE_FATHER:
                intent.setClass(NotificationActivity.this, PointOfSaleActivity.class);
                break;
            case Const.ROLE_TEACHER:
//                intent.setClass(NotificationActivity.this, CoursesActivity.class);
                break;
            default:
                Utils.shortToast(NotificationActivity.this, "El usuario no tiene un rol");
                break;
        }

        startActivity(intent);
        NotificationActivity.this.finish();

        /* Apply our act_1_splash exit (fade out) and menu_reports entry (fade in) animation transitions. */
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

    }

}
