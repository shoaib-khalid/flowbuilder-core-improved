package com.kalsym.flowbuilder.mxgraph.models.connectionend;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CEMxPoint {

    @JsonProperty("@y")
    private String y;

    @JsonProperty("@as")
    private String as;
}
