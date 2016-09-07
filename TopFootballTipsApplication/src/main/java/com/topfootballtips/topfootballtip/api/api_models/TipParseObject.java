package com.topfootballtips.topfootballtip.api.api_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TipParseObject {

    public TipParseObject(){
        //public constructor needed
    }

    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("data")
    @Expose
    private List<BestTip> data = new ArrayList<BestTip>();
    @SerializedName("nextPage")
    @Expose
    private Object nextPage;
    @SerializedName("totalObjects")
    @Expose
    private Integer totalObjects;

    /**
     *
     * @return
     * The offset
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     *
     * @param offset
     * The offset
     */
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    /**
     *
     * @return
     * The data
     */
    public List<BestTip> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(List<BestTip> data) {
        this.data = data;
    }

    /**
     *
     * @return
     * The nextPage
     */
    public Object getNextPage() {
        return nextPage;
    }

    /**
     *
     * @param nextPage
     * The nextPage
     */
    public void setNextPage(Object nextPage) {
        this.nextPage = nextPage;
    }

    /**
     *
     * @return
     * The totalObjects
     */
    public Integer getTotalObjects() {
        return totalObjects;
    }

    /**
     *
     * @param totalObjects
     * The totalObjects
     */
    public void setTotalObjects(Integer totalObjects) {
        this.totalObjects = totalObjects;
    }

}