package xyz.tianos.software.rxJava.Service;

import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import xyz.tianos.software.entity.Visit;
import xyz.tianos.software.rxJava.Response.VisitResponse;
import xyz.tianos.software.utils.Const;

public interface VisitService {

    /**
     * This method returns all cities within a given bounding box
     *
     * Example from the api docs: citiesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&lang=de&username=demo
     *
     * @param north    bounding box north
     */
    @POST(Const.ROUTE_VISIT)
    @FormUrlEncoded
    Single<VisitResponse> queryVisit(
//            @Field("north") double north
//        @Body ArrayList<Visit> visits
        @Body HashMap<String, ArrayList<Visit>> body
    );

//    A

    /*

    @POST("point-of-sale/")
    @FormUrlEncoded
    Single<PointOfSaleResponse> queryPointOfSale(
        @Query("north") double north,
        @Query("south") double south,
        @Query("east") double east,
        @Query("west") double west,
        @Query("lang") String lang
    );


    public interface APIService {

    @POST("/posts")
    @FormUrlEncoded
    Call<Post> savePost(
    @Field("title") String title,
                        @Field("body") String body,
                        @Field("userId") long userId
                        );
}
     */

}
