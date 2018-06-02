package xyz.elcomercio.software.rxJava.Service;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;
import xyz.elcomercio.software.entity.ListPdvHasProduct;
import xyz.elcomercio.software.rxJava.Response.PdvHasProductResponse;
import xyz.elcomercio.software.utils.Const;

public interface PdvHasProductService {

    /**
     * This method returns all cities within a given bounding box
     *
     * Example from the api docs: citiesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&lang=de&username=demo
     *
     * @param north    bounding box north
     */
    @POST(Const.ROUTE_POINT_OF_SALE_HAS_PRODUCT)
//    @FormUrlEncoded
    Single<PdvHasProductResponse> queryVisit(
            @Body ListPdvHasProduct pdvHasProductList
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
