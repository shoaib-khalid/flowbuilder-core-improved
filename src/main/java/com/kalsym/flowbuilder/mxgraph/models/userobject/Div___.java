
 
 
 

package com.kalsym.flowbuilder.mxgraph.models.userobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class Div___ {

    @JsonProperty("@id")
    private String id;

    @JsonProperty("@class")
    private String _class;

    @JsonProperty("@style")
    private String style;
    
    
    private Img img;
    private Div____ div;
    private Object span;
}
