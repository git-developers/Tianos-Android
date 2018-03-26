package xyz.tianos.software.rxJava.Service;

import java.util.HashMap;

import io.reactivex.Single;
import retrofit2.http.POST;
import retrofit2.http.Body;
import xyz.tianos.software.rxJava.Response.UserResponse;
import xyz.tianos.software.utils.Const;

public interface UserService {

    /**
     *
     * @param body
     * @return
     */
    @POST(Const.ROUTE_LOGIN)
    Single<UserResponse> queryLogin(
        @Body HashMap<String, String> body
    );

}
