package id.tech.rcslive.models;

/**
 * Created by macbook on 5/19/16.
 */
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pojo_ResponseAccountUser {

    @SerializedName("json_code")
    @Expose
    private String jsonCode;
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
        @SerializedName("member_userid")
        @Expose
        private String memberUserid;
        @SerializedName("member_name")
        @Expose
        private String memberName;
        @SerializedName("member_phone")
        @Expose
        private String memberPhone;
        @SerializedName("member_password")
        @Expose
        private String memberPassword;
        @SerializedName("member_email")
        @Expose
        private String memberEmail;
        @SerializedName("member_gender")
        @Expose
        private String memberGender;
        @SerializedName("member_country")
        @Expose
        private String memberCountry;
        @SerializedName("member_city")
        @Expose
        private String memberCity;
        @SerializedName("member_hometown")
        @Expose
        private String memberHometown;
        @SerializedName("member_birthyear")
        @Expose
        private String memberBirthyear;
        @SerializedName("member_bio")
        @Expose
        private String memberBio;
        @SerializedName("member_group")
        @Expose
        private String memberGroup;
        @SerializedName("member_facebooklink")
        @Expose
        private String memberFacebooklink;
        @SerializedName("member_linkedinlink")
        @Expose
        private String memberLinkedinlink;
        @SerializedName("member_photo")
        @Expose
        private String memberPhoto;
        @SerializedName("email_confirmation_id")
        @Expose
        private String emailConfirmationId;
        @SerializedName("admin_account")
        @Expose
        private String adminAccount;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("gender_name")
        @Expose
        private String genderName;
        @SerializedName("country_name")
        @Expose
        private String countryName;
        @SerializedName("city_name")
        @Expose
        private String cityName;
        @SerializedName("group_name")
        @Expose
        private String groupName;

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
         * The memberPhone
         */
        public String getMemberPhone() {
            return memberPhone;
        }

        /**
         *
         * @param memberPhone
         * The member_phone
         */
        public void setMemberPhone(String memberPhone) {
            this.memberPhone = memberPhone;
        }

        /**
         *
         * @return
         * The memberPassword
         */
        public String getMemberPassword() {
            return memberPassword;
        }

        /**
         *
         * @param memberPassword
         * The member_password
         */
        public void setMemberPassword(String memberPassword) {
            this.memberPassword = memberPassword;
        }

        /**
         *
         * @return
         * The memberEmail
         */
        public String getMemberEmail() {
            return memberEmail;
        }

        /**
         *
         * @param memberEmail
         * The member_email
         */
        public void setMemberEmail(String memberEmail) {
            this.memberEmail = memberEmail;
        }

        /**
         *
         * @return
         * The memberGender
         */
        public String getMemberGender() {
            return memberGender;
        }

        /**
         *
         * @param memberGender
         * The member_gender
         */
        public void setMemberGender(String memberGender) {
            this.memberGender = memberGender;
        }

        /**
         *
         * @return
         * The memberCountry
         */
        public String getMemberCountry() {
            return memberCountry;
        }

        /**
         *
         * @param memberCountry
         * The member_country
         */
        public void setMemberCountry(String memberCountry) {
            this.memberCountry = memberCountry;
        }

        /**
         *
         * @return
         * The memberCity
         */
        public String getMemberCity() {
            return memberCity;
        }

        /**
         *
         * @param memberCity
         * The member_city
         */
        public void setMemberCity(String memberCity) {
            this.memberCity = memberCity;
        }

        /**
         *
         * @return
         * The memberHometown
         */
        public String getMemberHometown() {
            return memberHometown;
        }

        /**
         *
         * @param memberHometown
         * The member_hometown
         */
        public void setMemberHometown(String memberHometown) {
            this.memberHometown = memberHometown;
        }

        /**
         *
         * @return
         * The memberBirthyear
         */
        public String getMemberBirthyear() {
            return memberBirthyear;
        }

        /**
         *
         * @param memberBirthyear
         * The member_birthyear
         */
        public void setMemberBirthyear(String memberBirthyear) {
            this.memberBirthyear = memberBirthyear;
        }

        /**
         *
         * @return
         * The memberBio
         */
        public String getMemberBio() {
            return memberBio;
        }

        /**
         *
         * @param memberBio
         * The member_bio
         */
        public void setMemberBio(String memberBio) {
            this.memberBio = memberBio;
        }

        /**
         *
         * @return
         * The memberGroup
         */
        public String getMemberGroup() {
            return memberGroup;
        }

        /**
         *
         * @param memberGroup
         * The member_group
         */
        public void setMemberGroup(String memberGroup) {
            this.memberGroup = memberGroup;
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
         * The emailConfirmationId
         */
        public String getEmailConfirmationId() {
            return emailConfirmationId;
        }

        /**
         *
         * @param emailConfirmationId
         * The email_confirmation_id
         */
        public void setEmailConfirmationId(String emailConfirmationId) {
            this.emailConfirmationId = emailConfirmationId;
        }

        /**
         *
         * @return
         * The adminAccount
         */
        public String getAdminAccount() {
            return adminAccount;
        }

        /**
         *
         * @param adminAccount
         * The admin_account
         */
        public void setAdminAccount(String adminAccount) {
            this.adminAccount = adminAccount;
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
         * The genderName
         */
        public String getGenderName() {
            return genderName;
        }

        /**
         *
         * @param genderName
         * The gender_name
         */
        public void setGenderName(String genderName) {
            this.genderName = genderName;
        }

        /**
         *
         * @return
         * The countryName
         */
        public String getCountryName() {
            return countryName;
        }

        /**
         *
         * @param countryName
         * The country_name
         */
        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }

        /**
         *
         * @return
         * The cityName
         */
        public String getCityName() {
            return cityName;
        }

        /**
         *
         * @param cityName
         * The city_name
         */
        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        /**
         *
         * @return
         * The groupName
         */
        public String getGroupName() {
            return groupName;
        }

        /**
         *
         * @param groupName
         * The group_name
         */
        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

    }
}
