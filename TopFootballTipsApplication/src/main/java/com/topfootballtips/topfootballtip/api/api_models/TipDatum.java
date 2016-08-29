package com.topfootballtips.topfootballtip.api.api_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TipDatum {

    @SerializedName("TipResult")
    @Expose
    private Integer tipResult;
    @SerializedName("Comments")
    @Expose
    private Object comments;
    @SerializedName("created")
    @Expose
    private Long created;
    @SerializedName("ownerId")
    @Expose
    private Object ownerId;
    @SerializedName("Coef")
    @Expose
    private Double coef;
    @SerializedName("Match")
    @Expose
    private String match;
    @SerializedName("Result")
    @Expose
    private String result;
    @SerializedName("__meta")
    @Expose
    private String meta;
    @SerializedName("MatchDate")
    @Expose
    private Long matchDate;
    @SerializedName("___class")
    @Expose
    private String _class;
    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("Tip")
    @Expose
    private String tip;
    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("updated")
    @Expose
    private Object updated;
    @SerializedName("objectId")
    @Expose
    private String objectId;
    @SerializedName("TipType")
    @Expose
    private Integer tipType;

    /**
     *
     * @return
     * The tipResult
     */
    public Integer getTipResult() {
        return tipResult;
    }

    /**
     *
     * @param tipResult
     * The TipResult
     */
    public void setTipResult(Integer tipResult) {
        this.tipResult = tipResult;
    }

    /**
     *
     * @return
     * The comments
     */
    public Object getComments() {
        return comments;
    }

    /**
     *
     * @param comments
     * The Comments
     */
    public void setComments(Object comments) {
        this.comments = comments;
    }

    /**
     *
     * @return
     * The created
     */
    public Long getCreated() {
        return created;
    }

    /**
     *
     * @param created
     * The created
     */
    public void setCreated(Long created) {
        this.created = created;
    }

    /**
     *
     * @return
     * The ownerId
     */
    public Object getOwnerId() {
        return ownerId;
    }

    /**
     *
     * @param ownerId
     * The ownerId
     */
    public void setOwnerId(Object ownerId) {
        this.ownerId = ownerId;
    }

    /**
     *
     * @return
     * The coef
     */
    public Double getCoef() {
        return coef;
    }

    /**
     *
     * @param coef
     * The Coef
     */
    public void setCoef(Double coef) {
        this.coef = coef;
    }

    /**
     *
     * @return
     * The match
     */
    public String getMatch() {
        return match;
    }

    /**
     *
     * @param match
     * The Match
     */
    public void setMatch(String match) {
        this.match = match;
    }

    /**
     *
     * @return
     * The result
     */
    public String getResult() {
        return result;
    }

    /**
     *
     * @param result
     * The Result
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     *
     * @return
     * The meta
     */
    public String getMeta() {
        return meta;
    }

    /**
     *
     * @param meta
     * The __meta
     */
    public void setMeta(String meta) {
        this.meta = meta;
    }

    /**
     *
     * @return
     * The matchDate
     */
    public Long getMatchDate() {
        return matchDate;
    }

    /**
     *
     * @param matchDate
     * The MatchDate
     */
    public void setMatchDate(Long matchDate) {
        this.matchDate = matchDate;
    }

    /**
     *
     * @return
     * The _class
     */
    public String getClass_() {
        return _class;
    }

    /**
     *
     * @param _class
     * The ___class
     */
    public void setClass_(String _class) {
        this._class = _class;
    }

    /**
     *
     * @return
     * The country
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     * The Country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return
     * The tip
     */
    public String getTip() {
        return tip;
    }

    /**
     *
     * @param tip
     * The Tip
     */
    public void setTip(String tip) {
        this.tip = tip;
    }

    /**
     *
     * @return
     * The iD
     */
    public Integer getID() {
        return iD;
    }

    /**
     *
     * @param iD
     * The ID
     */
    public void setID(Integer iD) {
        this.iD = iD;
    }

    /**
     *
     * @return
     * The updated
     */
    public Object getUpdated() {
        return updated;
    }

    /**
     *
     * @param updated
     * The updated
     */
    public void setUpdated(Object updated) {
        this.updated = updated;
    }

    /**
     *
     * @return
     * The objectId
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     *
     * @param objectId
     * The objectId
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    /**
     *
     * @return
     * The tipType
     */
    public Integer getTipType() {
        return tipType;
    }

    /**
     *
     * @param tipType
     * The TipType
     */
    public void setTipType(Integer tipType) {
        this.tipType = tipType;
    }

}