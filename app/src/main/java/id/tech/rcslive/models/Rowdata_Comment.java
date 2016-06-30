package id.tech.rcslive.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by macbook on 4/12/16.
 */
public class Rowdata_Comment {
    @SerializedName("id_comment")
    @Expose
    private String idComment;
    @SerializedName("commentor_photo")
    @Expose
    private String commentorPhoto;
    @SerializedName("nama_commentor")
    @Expose
    private String namaCommentor;
    @SerializedName("comment_description")
    @Expose
    private String commentDescription;

    public String getIdComment() {
        return idComment;
    }

    public void setIdComment(String idComment) {
        this.idComment = idComment;
    }

    public String getCommentorPhoto() {
        return commentorPhoto;
    }

    public void setCommentorPhoto(String commentorPhoto) {
        this.commentorPhoto = commentorPhoto;
    }

    public String getNamaCommentor() {
        return namaCommentor;
    }

    public void setNamaCommentor(String namaCommentor) {
        this.namaCommentor = namaCommentor;
    }

    public String getCommentDescription() {
        return commentDescription;
    }

    public void setCommentDescription(String commentDescription) {
        this.commentDescription = commentDescription;
    }
}
