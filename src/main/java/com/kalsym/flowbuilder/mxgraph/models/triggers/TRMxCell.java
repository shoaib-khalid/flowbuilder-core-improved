package com.kalsym.flowbuilder.mxgraph.models.triggers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TRMxCell {

    @JsonProperty("@style")
    private String style;
    @JsonProperty("@parent")
    private String parent;
    @JsonProperty("@vertex")
    private String vertex;
    @JsonProperty("@connectable")
    private String connectable;
    private TRMxGeometry mxGeometry;
    private TRDiv div;
}
