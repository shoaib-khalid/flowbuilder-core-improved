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
public class H4 {

    @JsonProperty("@id")
    public String id;
    @JsonProperty("@class")
    public String classVar;
    @JsonProperty("#text")
    public String text;
}
