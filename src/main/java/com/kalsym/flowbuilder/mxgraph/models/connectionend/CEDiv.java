package com.kalsym.flowbuilder.mxgraph.models.connectionend;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CEDiv {

    @JsonProperty("@xmlns")
    private String xmlns;

    @JsonProperty("@as")
    private String as;
    private CESvg svg;
    private Object br;
}
