package id.tech.rcslive.models;

/**
 * Created by macbook on 4/3/16.
 */

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pojo_Dokumentasi {

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

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("documentation_id")
        @Expose
        private String documentationId;
        @SerializedName("documentation_title")
        @Expose
        private String documentationTitle;
        @SerializedName("documentation_description")
        @Expose
        private String documentationDescription;
        @SerializedName("documentation_photo")
        @Expose
        private String documentationPhoto;
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
         * The documentationId
         */
        public String getDocumentationId() {
            return documentationId;
        }

        /**
         *
         * @param documentationId
         * The documentation_id
         */
        public void setDocumentationId(String documentationId) {
            this.documentationId = documentationId;
        }

        /**
         *
         * @return
         * The documentationTitle
         */
        public String getDocumentationTitle() {
            return documentationTitle;
        }

        /**
         *
         * @param documentationTitle
         * The documentation_title
         */
        public void setDocumentationTitle(String documentationTitle) {
            this.documentationTitle = documentationTitle;
        }

        /**
         *
         * @return
         * The documentationDescription
         */
        public String getDocumentationDescription() {
            return documentationDescription;
        }

        /**
         *
         * @param documentationDescription
         * The documentation_description
         */
        public void setDocumentationDescription(String documentationDescription) {
            this.documentationDescription = documentationDescription;
        }

        /**
         *
         * @return
         * The documentationPhoto
         */
        public String getDocumentationPhoto() {
            return documentationPhoto;
        }

        /**
         *
         * @param documentationPhoto
         * The documentation_photo
         */
        public void setDocumentationPhoto(String documentationPhoto) {
            this.documentationPhoto = documentationPhoto;
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
