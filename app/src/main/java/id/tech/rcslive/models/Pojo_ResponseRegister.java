package id.tech.rcslive.models;

/**
 * Created by macbook on 5/19/16.
 */
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pojo_ResponseRegister {
    @SerializedName("json_code")
    @Expose
    private String jsonCode;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("member_name")
    @Expose
    private String memberName;
    @SerializedName("member_photo")
    @Expose
    private String memberPhoto;
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

}
