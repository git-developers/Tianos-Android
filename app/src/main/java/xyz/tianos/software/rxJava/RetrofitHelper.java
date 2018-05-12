package xyz.tianos.software.rxJava;

import android.util.Log;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.tianos.software.rxJava.Service.CategoryService;
import xyz.tianos.software.rxJava.Service.PointOfSaleService;
import xyz.tianos.software.rxJava.Service.ProductService;
import xyz.tianos.software.rxJava.Service.UserService;
import xyz.tianos.software.rxJava.Service.VisitService;
import xyz.tianos.software.utils.Const;

/**
 * This class initializes retrofit with a default configuration.
 * You can use this class to initialize the different services.
 */
public class RetrofitHelper {

    /**
     * The CityService communicates with the json api of the city provider.
     */
    public PointOfSaleService getPointOfSaleService() {
        final Retrofit retrofit = createTianosRetrofit();
        return retrofit.create(PointOfSaleService.class);
    }

    public ProductService getProductService() {
        final Retrofit retrofit = createTianosRetrofit();
        return retrofit.create(ProductService.class);
    }

    public CategoryService getCategoryService() {
        final Retrofit retrofit = createTianosRetrofit();
        return retrofit.create(CategoryService.class);
    }

    public UserService getUserService() {
        final Retrofit retrofit = createTianosRetrofit();
        return retrofit.create(UserService.class);
    }

    public VisitService getVisitService() {
        final Retrofit retrofit = createTianosRetrofit();
        return retrofit.create(VisitService.class);
    }

    /**
     * This custom client will append the "username=demo" query after every request.
     */
    private OkHttpClient createOkHttpClient() {

        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                final Request original = chain.request();
                final HttpUrl originalHttpUrl = original.url();

//                http://api.geonames.org?username=demo
                final HttpUrl url = originalHttpUrl
                        .newBuilder()
                        .addQueryParameter("pollo", "demo")
                        .build()
                        ;

                Log.d("TIANOS_OkHttpClient", "URL:: " + url);

                // Request customization: add request headers
                final Request.Builder requestBuilder = original
                        .newBuilder()
                        .url(url)
                        ;

                final Request request = requestBuilder.build();

                Log.d("TIANOS_OkHttpClient", "REQUEST:: " + request);

                return chain.proceed(request);
            }
        });

        return httpClient.build();
    }

    /**
     * Creates a pre configured Retrofit instance
     */
    private Retrofit createTianosRetrofit() {


//        EJEMPLO
//        https://github.com/codepath/android_guides/wiki/Consuming-APIs-with-Retrofit

        return new Retrofit
                .Builder()
                .baseUrl(Const.API_BASE_ROUTE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // <- add this
                .client(createOkHttpClient())
                .build()
                ;
    }
}
