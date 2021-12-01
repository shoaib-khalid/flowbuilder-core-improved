package com.kalsym.flowbuilder.mxgraph.models.connectionend;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CEMxCell {

    @JsonProperty("@style")
    private String style;
    @JsonProperty("@parent")
    private String parent;
    @JsonProperty("@vertex")
    private String vertex;
    private CEMxGeometry mxGeometry;
    private CEDiv div;
}
