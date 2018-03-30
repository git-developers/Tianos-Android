package xyz.tianos.software.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;

//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.params.BasicHttpParams;
//import org.apache.http.params.HttpConnectionParams;
//import org.apache.http.params.HttpParams;
//import org.apache.http.util.EntityUtils;

/**
 * Created by jafeth on 3/31/17.
 */

public class Utils {

    // Find Image ID corresponding to the name of the image (in the directory mipmap).
    public static int getResourceIdByName(Context context, String resName, String defType)  {
        //defType: drawable, mipmap
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , defType, pkgName);
        Log.i("getResourceIdByName", "Res Name: "+ resName+" ==> Res ID= "+ resID);
        return resID;
    }

    public static void getPackageName(Context context) {
        String packageName = context.getPackageName();
        Toast.makeText(context, packageName, Toast.LENGTH_LONG).show();
    }

    public static void shortToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void longToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public static void rxJavaErrorHandler(final Context context, final String text) {
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e("GATAZO_LOGIN_XX", throwable.getClass().getName());             // io.reactivex.exceptions.OnErrorNotImplementedException
                Log.e("GATAZO_LOGIN_XX", throwable.getCause().getClass().getName());  // java.lang.Exception
                Log.e("GATAZO_LOGIN_XX", throwable.getMessage());                     // "Test"
                throwable.printStackTrace();

                shortToast(context, text);
            }
        });
    }

    public static void hideSoftKeyboard(Activity activity) {
        if (activity != null) {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (activity.getCurrentFocus() != null && inputManager != null) {
                inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
                inputManager.hideSoftInputFromInputMethod(activity.getCurrentFocus().getWindowToken(), 0);
            }
        }
    }

    public static void hideSoftKeyboard(View view) {
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputManager != null) {
                inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public static Gson gsonBuilder() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        Gson gson = gsonBuilder.create();
        return gson;
    }

}
