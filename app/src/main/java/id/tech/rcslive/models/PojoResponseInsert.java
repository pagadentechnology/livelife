package id.tech.rcslive.models;

/**
 * Created by macbook on 4/15/16.
 */

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PojoResponseInsert {
    @SerializedName("json_code")
    @Expose
    private String jsonCode;
    @SerializedName("data")
    @Expose
    private String data;
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
    public String getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(String data) {
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
}
