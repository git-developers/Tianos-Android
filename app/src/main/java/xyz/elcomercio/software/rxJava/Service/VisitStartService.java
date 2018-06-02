package xyz.elcomercio.software.rxJava.Service;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;
import xyz.elcomercio.software.entity.ListVisit;
import xyz.elcomercio.software.rxJava.Response.VisitStartResponse;
import xyz.elcomercio.software.utils.Const;

public interface VisitStartService {

    /**
     * This method returns all cities within a given bounding box
     *
     * Example from the api docs: citiesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&lang=de&username=demo
     *
     * @param north    bounding box north
     */
    @POST(Const.ROUTE_VISIT_START)
//    @FormUrlEncoded
    Single<VisitStartResponse> queryVisit(
        @Body ListVisit visits
    );





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
