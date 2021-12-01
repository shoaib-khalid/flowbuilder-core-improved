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
public class Img {

    @JsonProperty("@src")
    public String src ;
    @JsonProperty("@class")
    public String classVar;
    @JsonProperty("@alt")
    public String alt;
    @JsonProperty("@style")
    public String style;
}
