package id.tech.rcslive.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by macbook on 5/2/16.
 */
public class Rowdata_Categories {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("categories_name")
    @Expose
    private String categoriesName;
    @SerializedName("categories_photo")
    @Expose
    private String categoriesPhoto;
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
     * The categoriesName
     */
    public String getCategoriesName() {
        return categoriesName;
    }

    /**
     *
     * @param categoriesName
     * The categories_name
     */
    public void setCategoriesName(String categoriesName) {
        this.categoriesName = categoriesName;
    }

    /**
     *
     * @return
     * The categoriesPhoto
     */
    public String getCategoriesPhoto() {
        return categoriesPhoto;
    }

    /**
     *
     * @param categoriesPhoto
     * The categories_photo
     */
    public void setCategoriesPhoto(String categoriesPhoto) {
        this.categoriesPhoto = categoriesPhoto;
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

