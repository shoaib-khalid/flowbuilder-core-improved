package com.kalsym.flowbuilder.formatted.models.vertex;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Validation {

    private ValidationInputType inputType;
    private String phone;
    private String regex;
    private Retry retry;

}
