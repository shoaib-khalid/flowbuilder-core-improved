package com.kalsym.flowbuilder.mxgraph.models.connectionstart;

import com.kalsym.flowbuilder.mxgraph.models.connectionend.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString()
public class CSMxCell {

    @JsonProperty("@style")
    private String style;
    @JsonProperty("@parent")
    private String parent;
    @JsonProperty("@vertex")
    private String vertex;
    private CSMxGeometry mxGeometry;
    private CSDiv div;
}
