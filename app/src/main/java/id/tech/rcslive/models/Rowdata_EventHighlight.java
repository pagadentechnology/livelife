package id.tech.rcslive.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by RebelCreative-A1 on 23/03/2016.
 */
public class Rowdata_EventHighlight {

    @SerializedName("id_event")
    @Expose
    private String idEvent;
    @SerializedName("id_visit")
    @Expose
    private String idVisit;
    @SerializedName("tv_tgl")
    @Expose
    private String tvTgl;
    @SerializedName("tv_judul")
    @Expose
    private String tvJudul;
    @SerializedName("tv_alamat")
    @Expose
    private String tvAlamat;
    @SerializedName("tv_kategori")
    @Expose
    private String tvKategori;
//    @SerializedName("joined")
//    @Expose
//    private String joined;
    @SerializedName("event_photo")
    @Expose
    private String eventPhoto;
    @SerializedName("event_lat")
    @Expose
    private String eventLat;
    @SerializedName("event_lon")
    @Expose
    private String eventLon;
    @SerializedName("event_description")
    @Expose
    private String eventDescription;
    @SerializedName("event_documentationid")
    @Expose
    private String eventDocumentationid;
    @SerializedName("event_minjoin")
    @Expose
    private String eventMinjoin;
    @SerializedName("total_join")
    @Expose
    private String totalJoin;

    public String getTotalJoin() {
        return totalJoin;
    }

    public void setTotalJoin(String totalJoin) {
        this.totalJoin = totalJoin;
    }

    @SerializedName("member_name")
    @Expose
    private String memberName;
    @SerializedName("member_phone")
    @Expose
    private String memberPhone;
    @SerializedName("member_photo")
    @Expose
    private String memberPhoto;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getMemberPhoto() {
        return memberPhoto;
    }

    public void setMemberPhoto(String memberPhoto) {
        this.memberPhoto = memberPhoto;
    }

    public String getEventMinjoin() {
        return eventMinjoin;
    }

    public void setEventMinjoin(String eventMinjoin) {
        this.eventMinjoin = eventMinjoin;
    }

    public String getEventDocumentationid() {
        return eventDocumentationid;
    }

    public void setEventDocumentationid(String eventDocumentationid) {
        this.eventDocumentationid = eventDocumentationid;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventLat() {
        return eventLat;
    }

    public void setEventLat(String eventLat) {
        this.eventLat = eventLat;
    }

    public String getEventLon() {
        return eventLon;
    }

    public void setEventLon(String eventLon) {
        this.eventLon = eventLon;
    }

    /**
     *
     * @return
     * The idEvent
     */
    public String getIdEvent() {
        return idEvent;
    }

    /**
     *
     * @param idEvent
     * The id_event
     */
    public void setIdEvent(String idEvent) {
        this.idEvent = idEvent;
    }

    /**
     *
     * @return
     * The idVisit
     */
    public String getIdVisit() {
        return idVisit;
    }

    /**
     *
     * @param idVisit
     * The id_visit
     */
    public void setIdVisit(String idVisit) {
        this.idVisit = idVisit;
    }

    /**
     *
     * @return
     * The tvTgl
     */
    public String getTvTgl() {
        return tvTgl;
    }

    /**
     *
     * @param tvTgl
     * The tv_tgl
     */
    public void setTvTgl(String tvTgl) {
        this.tvTgl = tvTgl;
    }

    /**
     *
     * @return
     * The tvJudul
     */
    public String getTvJudul() {
        return tvJudul;
    }

    /**
     *
     * @param tvJudul
     * The tv_judul
     */
    public void setTvJudul(String tvJudul) {
        this.tvJudul = tvJudul;
    }

    /**
     *
     * @return
     * The tvAlamat
     */
    public String getTvAlamat() {
        return tvAlamat;
    }

    /**
     *
     * @param tvAlamat
     * The tv_alamat
     */
    public void setTvAlamat(String tvAlamat) {
        this.tvAlamat = tvAlamat;
    }

    /**
     *
     * @return
     * The tvKategori
     */
    public String getTvKategori() {
        return tvKategori;
    }

    /**
     *
     * @param tvKategori
     * The tv_kategori
     */
    public void setTvKategori(String tvKategori) {
        this.tvKategori = tvKategori;
    }

    /**
     *
     * @return
     * The joined
     */
//    public String getJoined() {
//        return joined;
//    }

//    public void setJoined(String joined) {
//        this.joined = joined;
//    }


    public String getEventPhoto() {
        return eventPhoto;
    }

    public void setEventPhoto(String eventPhoto) {
        this.eventPhoto = eventPhoto;
    }

    @SerializedName("cek_exists")
    @Expose
    private String cek_exists;

    public String getCek_exists() {
        return cek_exists;
    }

    public void setCek_exists(String cek_exists) {
        this.cek_exists = cek_exists;
    }
}
