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
public class Div_ {

    @JsonProperty("@id")
    private String id;

    @JsonProperty("@class")
    private String _class;

    @JsonProperty("@style")
    private String style;

    private List<Div__> div = new ArrayList<Div__>();
    private Span span;

    private Img img;
}
