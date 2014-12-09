package edu.ccny.cs.kyaw.pillverifier.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by kyawthan on 11/17/14.
 */
public class ModelDrug implements Serializable {

    @SerializedName("SPL_ID")
    public String splId;
    @SerializedName("PRODUCT_CODE")
    public String productCode;
    @SerializedName("NDC9")
    public String ndc9;
    @SerializedName("SPLCOLOR")
    public String splColor;
    @SerializedName("SPLIMPRINT")
    public String sqlImprint;
    @SerializedName("SPLSHAPE")
    public String splShape;
    @SerializedName("SPLSIZE")
    public String splSize ;
    @SerializedName("SPLSCORE")
    public String sqlScore;
    @SerializedName("RXCUI")
    public String rxCui;
    @SerializedName("RXTTY")
    public String rxTty;
    @SerializedName("RXSTRING")
    public String rxString;
    @SerializedName("INGREDIENTS")
    public String ingredients;
    @SerializedName("HAS_IMAGE")
    public String hasImage;
    @SerializedName("SETID")
    public String setId;
    @SerializedName("image_id")
    public String imageId;
    @SerializedName("AUTHOR")
    public String author;



}
