
package com.mydrafts.android.randomuser.data.entity;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("results")
    @Expose
    private List<User> results;
    @SerializedName("info")
    @Expose
    private Info info;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Response() {
    }

    /**
     * 
     * @param results
     * @param info
     */
    public Response(List<User> results, Info info) {
        super();
        this.results = results;
        this.info = info;
    }

    public List<User> getResults() {
        return results;
    }

    public void setResults(List<User> results) {
        this.results = results;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

}
