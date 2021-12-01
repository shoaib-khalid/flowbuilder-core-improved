package com.kalsym.flowbuilder.mxgraph.models.connectionend;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CECircle {

    @JsonProperty("@cx")
    private String cx;

    @JsonProperty("@cy")
    private String cy;

    @JsonProperty("@r")
    private String r;

    @JsonProperty("@stroke")
    private String stroke ;

    @JsonProperty("@stroke-width")
    private String strokeWidth ;

    @JsonProperty("@fill")
    private String fill;
}
