package com.kalsym.flowbuilder.mxgraph.models.userobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Div__ {

    @JsonProperty("@class")
    private String _class;
    @JsonProperty("@style")
    private String style;
    
    private List<Div___> div;
    
    private Img img;
}
