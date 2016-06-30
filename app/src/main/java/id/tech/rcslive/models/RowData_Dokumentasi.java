package id.tech.rcslive.models;

/**
 * Created by macbook on 4/3/16.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RowData_Dokumentasi {

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
