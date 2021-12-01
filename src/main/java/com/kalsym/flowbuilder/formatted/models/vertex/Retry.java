package com.kalsym.flowbuilder.formatted.models.vertex;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Retry {

    private int count;
    private String message;

    private Step failureStep;
}
