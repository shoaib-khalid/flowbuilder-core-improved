package com.kalsym.flowbuilder.formatted.models.vertex;

import java.util.HashMap;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Option {

    private String mxGraphId;
    private String text;
    private Step step;
    private String value;

}
