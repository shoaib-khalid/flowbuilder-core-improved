package com.kalsym.flowbuilder.mxgraph.models.triggers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TRButton {

    @JsonProperty("@type")
    private String type;

    @JsonProperty("@style")
    private String style;

    @JsonProperty("@class")
    private String _class;

    @JsonProperty("#text")
    private String text;
}
