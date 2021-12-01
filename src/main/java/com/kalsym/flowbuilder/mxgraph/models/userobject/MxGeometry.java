package com.kalsym.flowbuilder.mxgraph.models.userobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MxGeometry {

    @JsonProperty("@x")
    public String x;
    @JsonProperty("@y")
    public String y;
    @JsonProperty("@width")
    public String width;
    @JsonProperty("@height")
    public String height;
    @JsonProperty("@as")
    public String as;
}
