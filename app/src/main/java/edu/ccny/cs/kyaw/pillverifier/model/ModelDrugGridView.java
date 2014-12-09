package edu.ccny.cs.kyaw.pillverifier.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import edu.ccny.cs.kyaw.pillverifier.adapters.ConstantHelp;

/**
 * Created by kyawthan on 12/7/14.
 */
public class ModelDrugGridView implements Serializable {

    @SerializedName(ConstantHelp.KEY_IMAGE_ID)
    public String imageUrl;
    @SerializedName(ConstantHelp.KEY_RXSTRING)
    public String rxString;
}
