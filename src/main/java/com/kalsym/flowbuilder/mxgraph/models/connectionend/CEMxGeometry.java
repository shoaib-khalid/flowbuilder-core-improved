package com.kalsym.flowbuilder.mxgraph.models.connectionend;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CEMxGeometry {

    @JsonProperty("@width")
    private String width;

    @JsonProperty("@height")
    private String height;

    @JsonProperty("@relative")
    private String relative;

    @JsonProperty("@as")
    private String as;
    private CEMxPoint mxPoint;
}
