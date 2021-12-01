package com.kalsym.flowbuilder.mxgraph.models.mxcell;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MxCell {

    @JsonProperty("@id")
    private String id;

    @JsonProperty("@parent")
    private String parent;

    @JsonProperty("@source")
    private String source;

    @JsonProperty("@target")
    private String target;

    @JsonProperty("@edge")
    private String edge;

    private MxGeometry mxGeometry;
}
