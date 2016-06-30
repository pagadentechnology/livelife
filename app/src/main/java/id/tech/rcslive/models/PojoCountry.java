package id.tech.rcslive.models;

/**
 * Created by macbook on 5/16/16.
 */

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PojoCountry {

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
        @SerializedName("country_name")
        @Expose
        private String countryName;
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
         * The countryName
         */
        public String getCountryName() {
            return countryName;
        }

        /**
         *
         * @param countryName
         * The country_name
         */
        public void setCountryName(String countryName) {
            this.countryName = countryName;
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
