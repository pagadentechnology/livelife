package id.tech.rcslive.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macbook on 4/3/16.
 */
public class Rowdata_EventUserJoined {
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
