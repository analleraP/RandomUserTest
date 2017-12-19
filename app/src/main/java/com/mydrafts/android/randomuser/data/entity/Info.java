
package com.mydrafts.android.randomuser.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Info {

    @SerializedName("results")
    @Expose
    private int results;
    @SerializedName("page")
    @Expose
    private int page;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Info() {
    }

    /**
     * 
     * @param results
     * @param page
     */
    public Info(int results, int page) {
        super();
        this.results = results;
        this.page = page;
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

}
