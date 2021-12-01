package com.kalsym.flowbuilder.mxgraph.models.connectionstart;

import com.kalsym.flowbuilder.mxgraph.models.connectionend.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CSSvg {

    @JsonProperty("@class")
    private String _class;

    @JsonProperty("@xmlns")
    private String xmlns;

    @JsonProperty("@height")
    private String height;

    @JsonProperty("@width")
    private String width;

    private CSCircle circle;
}
