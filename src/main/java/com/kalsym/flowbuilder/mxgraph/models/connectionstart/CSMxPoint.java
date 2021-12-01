package com.kalsym.flowbuilder.mxgraph.models.connectionstart;

import com.kalsym.flowbuilder.mxgraph.models.connectionend.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CSMxPoint {

    @JsonProperty("@x")
    private String x;

    @JsonProperty("@y")
    private String y;

    @JsonProperty("@as")
    private String as;
}
