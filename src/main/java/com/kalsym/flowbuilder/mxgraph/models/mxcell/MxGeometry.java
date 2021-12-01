package com.kalsym.flowbuilder.mxgraph.models.mxcell;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MxGeometry {

    @JsonProperty("@relative")
    private String relative;

    @JsonProperty("@as")
    private String as;
}
