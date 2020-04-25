package au.edu.unsw.infs3634.Group79App;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Quiz implements Serializable
{

    @SerializedName("response_code")
    @Expose
    private Integer responseCode;
    @SerializedName("results")
    @Expose
    private List<Result> results = new ArrayList<Result>();
    private final static long serialVersionUID = 6449494497006561444L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Quiz() {
    }

    /**
     *
     * @param results
     * @param responseCode
     */
    public Quiz(Integer responseCode, List<Result> results) {
        super();
        this.responseCode = responseCode;
        this.results = results;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public Quiz withResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
        return this;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Quiz withResults(List<Result> results) {
        this.results = results;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("responseCode", responseCode).append("results", results).toString();
    }

}