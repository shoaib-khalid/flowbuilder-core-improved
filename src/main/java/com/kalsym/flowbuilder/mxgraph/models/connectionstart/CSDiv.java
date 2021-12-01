package com.kalsym.flowbuilder.mxgraph.models.connectionstart;

import com.kalsym.flowbuilder.mxgraph.models.connectionend.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CSDiv {

    @JsonProperty("@xmlns")
    private String xmlns;

    @JsonProperty("@as")
    private String as;
    private CSSvg svg;
    private Object br;
}
