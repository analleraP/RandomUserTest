
package com.mydrafts.android.randomuser.data.entity.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PagedResult<T> {

    @SerializedName("results")
    @Expose
    private List<T> results;
    @SerializedName("info")
    @Expose
    private Info info;

    /**
     * No args constructor for use in serialization
     * 
     */
    public PagedResult() {
    }

    /**
     * 
     * @param results
     * @param info
     */
    public PagedResult(List<T> results, Info info) {
        super();
        this.results = results;
        this.info = info;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

}
