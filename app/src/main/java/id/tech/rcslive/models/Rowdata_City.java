package id.tech.rcslive.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by macbook on 5/2/16.
 */
public class Rowdata_City {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("city_idcountry")
    @Expose
    private String cityIdcountry;
    @SerializedName("city_name")
    @Expose
    private String cityName;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The cityIdcountry
     */
    public String getCityIdcountry() {
        return cityIdcountry;
    }

    /**
     *
     * @param cityIdcountry
     * The city_idcountry
     */
    public void setCityIdcountry(String cityIdcountry) {
        this.cityIdcountry = cityIdcountry;
    }

    /**
     *
     * @return
     * The cityName
     */
    public String getCityName() {
        return cityName;
    }

    /**
     *
     * @param cityName
     * The city_name
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     *
     * @return
     * The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     * The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}

