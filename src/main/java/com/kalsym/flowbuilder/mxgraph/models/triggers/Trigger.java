package com.kalsym.flowbuilder.mxgraph.models.triggers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Trigger {

    @JsonProperty("@id")
    private String id;
    private TRMxCell mxCell;
}
