package com.kalsym.flowbuilder.mxgraph.models.userobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Div {

    @JsonProperty("@xmlns")
    private String xmlns;
    @JsonProperty("@as")
    private String as;
    
    @JsonProperty("@class")
    private String _class;
    
    
    private Div_ div;
    private Object br;
    
    private Img img;
}
