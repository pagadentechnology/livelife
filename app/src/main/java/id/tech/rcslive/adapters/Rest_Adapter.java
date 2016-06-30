package id.tech.rcslive.adapters;

/**
 * Created by macbook on 3/31/16.
 */
import com.squareup.okhttp.RequestBody;

import id.tech.rcslive.models.PojoCategories;
import id.tech.rcslive.models.PojoCity;
import id.tech.rcslive.models.PojoCountry;
import id.tech.rcslive.models.PojoEventIrregular;
import id.tech.rcslive.models.PojoEventRegular;
import id.tech.rcslive.models.PojoGroup;
import id.tech.rcslive.models.PojoResponseGmap;
import id.tech.rcslive.models.PojoResponseInsert;
import id.tech.rcslive.models.Pojo_Comment;
import id.tech.rcslive.models.Pojo_Dokumentasi;
import id.tech.rcslive.models.Pojo_EventCalendar;
import id.tech.rcslive.models.Pojo_EventHighlight;
import id.tech.rcslive.models.Pojo_EventJoined;
import id.tech.rcslive.models.Pojo_EventUserJoined;
import id.tech.rcslive.models.Pojo_ResponseAccountUser;
import id.tech.rcslive.models.Pojo_ResponseLogin;
import id.tech.rcslive.models.Pojo_ResponseRegister;
import id.tech.rcslive.models.RowData_Dokumentasi;
import id.tech.rcslive.util.ParameterCollections;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.http.Query;

public interface Rest_Adapter {

    @GET("getApi.php?")
    Call<Pojo_ResponseLogin> login(
            @Query("kind") String kind,
            @Query("email") String email,
            @Query("password") String password
    );

    @GET("getApi.php?")
    Call<Pojo_EventHighlight> get_all_events(
            @Query("kind") String kind
    );


    @GET("getApi.php?kind=country")
    Call<PojoCountry> get_all_country();

    @GET("getApi.php?kind=city")
    Call<PojoCity> get_all_city(
    );

    @GET("getApi.php?kind=group")
    Call<PojoGroup> get_all_group(
    );

    @GET("getApi.php?kind=categories")
    Call<PojoCategories> get_all_categories(
    );

    @GET("json?")
    Call<PojoResponseGmap> calculate_distance(
            @Query("origin") String latlongi_origin,
            @Query("destination") String latlongi_destination,
            @Query("sensor") String sensor_false,
            @Query("units") String metric

    );

    @GET("getApi.php?")
    Call<Pojo_EventCalendar> get_all_events_calendar(
            @Query("kind") String kind,
            @Query("calendar_mode") String calendar_mode
    );

    @GET("getApi.php?")
    Call<Pojo_EventHighlight> get_all_events_highlight(
            @Query("kind") String kind,
            @Query("display_region") boolean display_region,
            @Query("limit") String limit
    );

    @GET("getApi.php?kind=event&filter_mode=3&eventtype=Irregular")
    Call<PojoEventIrregular> get_all_events_irregular(
            @Query("userlog_id") String userlog_id
    );

    @GET("getApi.php?kind=event&filter_mode=1&eventtype=Irregular")
    Call<Pojo_EventHighlight> get_result_events(
            @Query("regionid") String regionid,
            @Query("categoryid") String categoryid
    );

    @GET("getApi.php?kind=event&filter_mode=4&eventtype=Regular")
    Call<PojoEventRegular> get_result_events_regular(
            @Query("idregion") String regionid,
            @Query("userlog_id") String userlog_id
    );

    @GET("getApi.php?")
    Call<Pojo_EventJoined> get_all_events_joined(
            @Query("kind") String kind,
            @Query("id") String id
    );

    @GET("getApi.php?")
    Call<Pojo_EventUserJoined> get_all_user_joined(
            @Query("kind") String kind,
            @Query("id") String id
    );

    @GET("getApi.php?")
    Call<Pojo_Dokumentasi> get_all_dokumentasi(
            @Query("kind") String kind,
            @Query("id") String id
    );

    @GET("getApi.php?")
    Call<Pojo_EventUserJoined> get_top_user_joined(
            @Query("kind") String kind,
            @Query("id") String id,
            @Query("limit") String limit
    );

    @GET("getApi.php?")
    Call<Pojo_Dokumentasi> get_top_dokumentasi(
            @Query("kind") String kind,
            @Query("id") String id,
            @Query("limit") String limit
    );

    @GET("getApi.php?")
    Call<Pojo_Comment> get_top_comments(
            @Query("kind") String kind,
            @Query("id") String id,
            @Query("limit") String limit
    );

    @GET("getApi.php?")
    Call<Pojo_Comment> get_all_event_comments(
            @Query("kind") String kind,
            @Query("id") String id
    );

    @FormUrlEncoded
    @POST("insert.php?kind=join")
    Call<PojoResponseInsert> insert_join_event(
            @Field("eventid") String eventid,
            @Field("userid") String userid
    );

    @FormUrlEncoded
    @POST("delete.php?kind=unjoin")
    Call<PojoResponseInsert> delete_unjoin_event(
            @Field("id") String eventid
    );

    @GET("insert.php?")
    Call<PojoResponseInsert> insert_join_event_get(
            @Query("kind") String kind,
            @Query("eventid") String eventid,
            @Query("userid") String userid
    );

    @Multipart
    @POST("insert.php?")
    Call<PojoResponseInsert> insert_event_comment(
            @Query("kind") String kind,
            @Part("eventid") RequestBody eventid,
            @Part("userid") RequestBody userid,
            @Part("text") RequestBody comment
    );

    @FormUrlEncoded
    @POST("insert.php?kind=comments")
    Call<PojoResponseInsert> insert_event_comment_test(
            @Field("eventid") String eventid,
            @Field("userid") String userid,
            @Field("text") String comment
    );

    @Multipart
    @POST("insert.php?")
    Call<PojoResponseInsert> insert_event_dokumentasi(
            @Query("kind") String kind,
            @Part("docid") RequestBody docid,
            @Part("docphoto\"; filename=\"img0.png\" ") RequestBody docphoto
    );

    @Multipart
    @POST("insert.php?kind=register")
    Call<Pojo_ResponseRegister> register(
            @Part("name") RequestBody name,
            @Part("email") RequestBody email,
            @Part("phone") RequestBody phone,
            @Part("password") RequestBody password,
            @Part("country") RequestBody country,
            @Part("city") RequestBody city,
            @Part("avatar\"; filename=\"img0.png\" ") RequestBody docphoto
    );

    @FormUrlEncoded
    @POST("update.php?kind=update_person")
    Call<PojoResponseInsert> update_person(
            @Query("id") String id,
            @Field("name") String name,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("password") String password,
            @Field("country") String country,
            @Field("city") String city,
            @Field("hometown") String hometown,
            @Field("group") String group,
            @Field("gender") String gender,
            @Field("birth") String  birth,
            @Field("bio") String bio

//            @Part("avatar\"; filename=\"img0.png\" ") RequestBody docphoto
    );

    @Multipart
    @POST("update.php?kind=update_person_photo")
    Call<PojoResponseInsert> update_avatar(
            @Part("id") RequestBody id,
            @Part("avatar\"; filename=\"img0.png\" ") RequestBody docphoto
    );

    @GET("getApi.php?kind=profile")
    Call<Pojo_ResponseAccountUser> get_profile_user(
            @Query("id") String id
    );

    @FormUrlEncoded
    @POST("insert.php?kind=checkin")
    Call<PojoResponseInsert> checkin_event(
            @Field("userid") String id,
            @Field("eventid") String limit
    );
}
