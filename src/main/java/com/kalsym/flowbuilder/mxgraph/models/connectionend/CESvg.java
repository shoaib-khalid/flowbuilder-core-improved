package com.kalsym.flowbuilder.mxgraph.models.connectionend;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CESvg {

    @JsonProperty("@xmlns")
    private String xmlns;

    @JsonProperty("@height")
    private String height;

    @JsonProperty("@width")
    private String width;

    private CECircle circle;
}
