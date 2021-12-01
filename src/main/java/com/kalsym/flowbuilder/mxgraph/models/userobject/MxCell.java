package com.kalsym.flowbuilder.mxgraph.models.userobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MxCell {

    @JsonProperty("@style")
    public String style;
    @JsonProperty("@parent")
    public String parent;
    @JsonProperty("@vertex")
    public String vertex;
    @JsonProperty("@connectable")
    public String connectable;

    public MxGeometry mxGeometry;

    private Div div;
}
