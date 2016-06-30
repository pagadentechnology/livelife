package id.tech.rcslive.models;

/**
 * Created by macbook on 5/2/16.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class PojoCity {

    @SerializedName("json_code")
    @Expose
    private String jsonCode;
    @SerializedName("data")
    @Expose
    private List<Datum> data = new ArrayList<Datum>();
    @SerializedName("response")
    @Expose
    private String response;

    /**
     *
     * @return
     * The jsonCode
     */
    public String getJsonCode() {
        return jsonCode;
    }

    /**
     *
     * @param jsonCode
     * The json_code
     */
    public void setJsonCode(String jsonCode) {
        this.jsonCode = jsonCode;
    }

    /**
     *
     * @return
     * The data
     */
    public List<Datum> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(List<Datum> data) {
        this.data = data;
    }

    /**
     *
     * @return
     * The response
     */
    public String getResponse() {
        return response;
    }

    /**
     *
     * @param response
     * The response
     */
    public void setResponse(String response) {
        this.response = response;
    }

    public class Datum {

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
}
