package com.kalsym.flowbuilder.mxgraph.models.connectionend;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConnectionEnd {

    @JsonProperty("@id")
    private String id;
    private CEMxCell mxCell;
}
