package com.kalsym.flowbuilder.mxgraph.models.connectionstart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CSMxGeometry {

    @JsonProperty("@x")
    private String x;

    @JsonProperty("@y")
    private String y;

    @JsonProperty("@width")
    private String width;

    @JsonProperty("@height")
    private String height;

    @JsonProperty("@relative")
    private String relative;

    @JsonProperty("@as")
    private String as;
    private CSMxPoint mxPoint;
}
