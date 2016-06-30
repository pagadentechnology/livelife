package id.tech.rcslive.util;

import java.util.List;

import id.tech.rcslive.models.RowDataGallery;

/**
 * Created by RebelCreative-A1 on 21/03/2016.
 */
public class ParameterCollections {

//    public static String BASE_URL=  "http://rcs-indonesia.com/nokia/api/";
    public static String BASE_URL=  "http://livelifeid.com/api/";
    public static String BASE_URL_IMG=  "http://livelifeid.com/uploads/source/";
    public static String BASE_URL_IMG_THUMB=  "http://livelifeid.com/uploads/thumbnail/";
    public static String BASE_URL_GMAP=  "http://maps.google.com/maps/api/directions/";
    public static String URL_SD_IMAGES =  "/LivelifeId/images/";

    public static List<RowDataGallery> data_selected;

    public static String SPF_NAME = "LiveLifeID";
    public static String SPF_LOGGED = "rcs_life_islogged";
    public static String SPF_USER_ID = "id";
    public static String SPF_USER_NAME = "name";
    public static String SPF_USER_PHOTO_URL = "photo_url";
    public static String SPF_SAME_DATE = "same_date";

    public static String KIND_LOGIN = "login";
    public static String KIND_EVENT = "event";
    public static String KIND_EVENT_JOINED = "joinbyid_user";
    public static String KIND_USER_JOINED = "joinbyid_event";
    public static String KIND_DOKUMENTASI = "documentationbyid_documentation";
    public static String KIND_COMMENTS_EVENT = "commentsbyid_event";
    public static String KIND_COMMENTS_INSERT = "comments";
    public static String KIND_DOCUMENTATION_INSERT = "documentation";
    public static String KIND_JOIN = "join";

    public static String TAG_EMAIL = "email";
    public static String TAG_PASS = "password";

    public static String ERROR_MESSAGE = "Error = ";

    public static String TAG_LATITUDE_NOW = "lat_now";
    public static String TAG_LONGITUDE_NOW = "long_now";
}
