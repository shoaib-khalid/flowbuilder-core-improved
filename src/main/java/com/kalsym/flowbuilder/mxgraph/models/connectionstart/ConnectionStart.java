package com.kalsym.flowbuilder.mxgraph.models.connectionstart;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConnectionStart {

    @JsonProperty("@id")
    private String id;
    private CSMxCell mxCell;
}
