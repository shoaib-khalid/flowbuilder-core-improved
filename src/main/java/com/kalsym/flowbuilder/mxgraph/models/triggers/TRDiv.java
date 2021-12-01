package com.kalsym.flowbuilder.mxgraph.models.triggers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TRDiv {

    @JsonProperty("@xmlns")
    private String xmlns;

    @JsonProperty("@as")
    private String as;
    private TRDiv_ div;
    private Object br;
}
