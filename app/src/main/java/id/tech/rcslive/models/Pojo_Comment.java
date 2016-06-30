package id.tech.rcslive.models;

/**
 * Created by macbook on 4/14/16.
 */
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pojo_Comment {
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

        @SerializedName("member_userid")
        @Expose
        private String memberUserid;
        @SerializedName("member_name")
        @Expose
        private String memberName;
        @SerializedName("member_photo")
        @Expose
        private String memberPhoto;
        @SerializedName("comments_text")
        @Expose
        private String commentsText;
        @SerializedName("created_at")
        @Expose
        private String createdAt;

        /**
         *
         * @return
         * The memberUserid
         */
        public String getMemberUserid() {
            return memberUserid;
        }

        /**
         *
         * @param memberUserid
         * The member_userid
         */
        public void setMemberUserid(String memberUserid) {
            this.memberUserid = memberUserid;
        }

        /**
         *
         * @return
         * The memberName
         */
        public String getMemberName() {
            return memberName;
        }

        /**
         *
         * @param memberName
         * The member_name
         */
        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        /**
         *
         * @return
         * The memberPhoto
         */
        public String getMemberPhoto() {
            return memberPhoto;
        }

        /**
         *
         * @param memberPhoto
         * The member_photo
         */
        public void setMemberPhoto(String memberPhoto) {
            this.memberPhoto = memberPhoto;
        }

        /**
         *
         * @return
         * The commentsText
         */
        public String getCommentsText() {
            return commentsText;
        }

        /**
         *
         * @param commentsText
         * The comments_text
         */
        public void setCommentsText(String commentsText) {
            this.commentsText = commentsText;
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
