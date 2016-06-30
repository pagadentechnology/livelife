package id.tech.rcslive.models;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Pojo_EventCalendar {


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
        private String id_event;
        @SerializedName("event_creatorid")
        @Expose
        private String eventCreatorid;
        @SerializedName("member_userid")
        @Expose
        private String memberUserid;
        @SerializedName("member_name")
        @Expose
        private String memberName;
        @SerializedName("member_facebooklink")
        @Expose
        private String memberFacebooklink;
        @SerializedName("member_linkedinlink")
        @Expose
        private String memberLinkedinlink;
        @SerializedName("event_title")
        @Expose
        private String eventTitle;
        @SerializedName("event_documentationid")
        @Expose
        private String eventDocumentationid;
        @SerializedName("event_description")
        @Expose
        private String eventDescription;
        @SerializedName("event_minjoin")
        @Expose
        private String eventMinjoin;
        @SerializedName("event_categoriesid")
        @Expose
        private String eventCategoriesid;
        @SerializedName("categories_name")
        @Expose
        private String categoriesName;
        @SerializedName("categories_photo")
        @Expose
        private String categoriesPhoto;
        @SerializedName("event_photo")
        @Expose
        private String eventPhoto;
        @SerializedName("event_location")
        @Expose
        private String eventLocation;
        @SerializedName("event_lat")
        @Expose
        private String eventLat;
        @SerializedName("event_lon")
        @Expose
        private String eventLon;
        @SerializedName("deadline")
        @Expose
        private String deadline;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("event_statusactive")
        @Expose
        private String eventStatusactive;
        @SerializedName("member_phone")
        @Expose
        private String memberPhone;
        @SerializedName("member_photo")
        @Expose
        private String memberPhoto;

        @SerializedName("total_join")
        @Expose
        private String totalJoin;

        public String getTotalJoin() {
            return totalJoin;
        }

        public void setTotalJoin(String totalJoin) {
            this.totalJoin = totalJoin;
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

        /**
         *
         * @return
         * The id
         */
        public String getId() {
            return id_event;
        }

        /**
         *
         * @param id
         * The id
         */
        public void setId(String id) {
            this.id_event = id;
        }

        /**
         *
         * @return
         * The eventCreatorid
         */
        public String getEventCreatorid() {
            return eventCreatorid;
        }

        /**
         *
         * @param eventCreatorid
         * The event_creatorid
         */
        public void setEventCreatorid(String eventCreatorid) {
            this.eventCreatorid = eventCreatorid;
        }

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
         * The memberFacebooklink
         */
        public String getMemberFacebooklink() {
            return memberFacebooklink;
        }

        /**
         *
         * @param memberFacebooklink
         * The member_facebooklink
         */
        public void setMemberFacebooklink(String memberFacebooklink) {
            this.memberFacebooklink = memberFacebooklink;
        }

        /**
         *
         * @return
         * The memberLinkedinlink
         */
        public String getMemberLinkedinlink() {
            return memberLinkedinlink;
        }

        /**
         *
         * @param memberLinkedinlink
         * The member_linkedinlink
         */
        public void setMemberLinkedinlink(String memberLinkedinlink) {
            this.memberLinkedinlink = memberLinkedinlink;
        }

        /**
         *
         * @return
         * The eventTitle
         */
        public String getEventTitle() {
            return eventTitle;
        }

        /**
         *
         * @param eventTitle
         * The event_title
         */
        public void setEventTitle(String eventTitle) {
            this.eventTitle = eventTitle;
        }

        /**
         *
         * @return
         * The eventDocumentationid
         */
        public String getEventDocumentationid() {
            return eventDocumentationid;
        }

        /**
         *
         * @param eventDocumentationid
         * The event_documentationid
         */
        public void setEventDocumentationid(String eventDocumentationid) {
            this.eventDocumentationid = eventDocumentationid;
        }

        /**
         *
         * @return
         * The eventDescription
         */
        public String getEventDescription() {
            return eventDescription;
        }

        /**
         *
         * @param eventDescription
         * The event_description
         */
        public void setEventDescription(String eventDescription) {
            this.eventDescription = eventDescription;
        }

        /**
         *
         * @return
         * The eventMinjoin
         */
        public String getEventMinjoin() {
            return eventMinjoin;
        }

        /**
         *
         * @param eventMinjoin
         * The event_minjoin
         */
        public void setEventMinjoin(String eventMinjoin) {
            this.eventMinjoin = eventMinjoin;
        }

        /**
         *
         * @return
         * The eventCategoriesid
         */
        public String getEventCategoriesid() {
            return eventCategoriesid;
        }

        /**
         *
         * @param eventCategoriesid
         * The event_categoriesid
         */
        public void setEventCategoriesid(String eventCategoriesid) {
            this.eventCategoriesid = eventCategoriesid;
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

        public String getEventPhoto() {
            return eventPhoto;
        }

        /**
         *
         * @param eventPhoto
         * The categories_photo
         */
        public void setEventPhoto(String eventPhoto) {
            this.eventPhoto = eventPhoto;
        }
        /**
         *
         * @return
         * The eventLocation
         */
        public String getEventLocation() {
            return eventLocation;
        }

        /**
         *
         * @param eventLocation
         * The event_location
         */
        public void setEventLocation(String eventLocation) {
            this.eventLocation = eventLocation;
        }

        /**
         *
         * @return
         * The eventLat
         */
        public String getEventLat() {
            return eventLat;
        }

        /**
         *
         * @param eventLat
         * The event_lat
         */
        public void setEventLat(String eventLat) {
            this.eventLat = eventLat;
        }

        /**
         *
         * @return
         * The eventLon
         */
        public String getEventLon() {
            return eventLon;
        }

        /**
         *
         * @param eventLon
         * The event_lon
         */
        public void setEventLon(String eventLon) {
            this.eventLon = eventLon;
        }

        /**
         *
         * @return
         * The deadline
         */
        public String getDeadline() {
            return deadline;
        }

        /**
         *
         * @param deadline
         * The deadline
         */
        public void setDeadline(String deadline) {
            this.deadline = deadline;
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

        /**
         *
         * @return
         * The eventStatusactive
         */
        public String getEventStatusactive() {
            return eventStatusactive;
        }

        /**
         *
         * @param eventStatusactive
         * The event_statusactive
         */
        public void setEventStatusactive(String eventStatusactive) {
            this.eventStatusactive = eventStatusactive;
        }

    }

}
