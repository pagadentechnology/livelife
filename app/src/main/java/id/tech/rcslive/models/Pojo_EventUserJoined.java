package id.tech.rcslive.models;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pojo_EventUserJoined {

    @SerializedName("json_code")
    @Expose
    private String jsonCode;
    @Nullable
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

        @SerializedName("userjoined_uniqueid")
        @Expose
        private String userjoinedUniqueid;
        @SerializedName("userjoined_id")
        @Expose
        private String userjoinedId;
        @SerializedName("userjoined_name")
        @Expose
        private String userjoinedName;
        @SerializedName("userjoined_photo")
        @Expose
        private String userjoinedPhoto;

        /**
         *
         * @return
         * The userjoinedUniqueid
         */
        public String getUserjoinedUniqueid() {
            return userjoinedUniqueid;
        }

        /**
         *
         * @param userjoinedUniqueid
         * The userjoined_uniqueid
         */
        public void setUserjoinedUniqueid(String userjoinedUniqueid) {
            this.userjoinedUniqueid = userjoinedUniqueid;
        }

        /**
         *
         * @return
         * The userjoinedId
         */
        public String getUserjoinedId() {
            return userjoinedId;
        }

        /**
         *
         * @param userjoinedId
         * The userjoined_id
         */
        public void setUserjoinedId(String userjoinedId) {
            this.userjoinedId = userjoinedId;
        }

        /**
         *
         * @return
         * The userjoinedName
         */
        public String getUserjoinedName() {
            return userjoinedName;
        }

        /**
         *
         * @param userjoinedName
         * The userjoined_name
         */
        public void setUserjoinedName(String userjoinedName) {
            this.userjoinedName = userjoinedName;
        }

        /**
         *
         * @return
         * The userjoinedPhoto
         */
        public String getUserjoinedPhoto() {
            return userjoinedPhoto;
        }

        /**
         *
         * @param userjoinedPhoto
         * The userjoined_photo
         */
        public void setUserjoinedPhoto(String userjoinedPhoto) {
            this.userjoinedPhoto = userjoinedPhoto;
        }

    }
}
