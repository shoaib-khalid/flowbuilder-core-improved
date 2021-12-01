package com.kalsym.flowbuilder.formatted.models.vertex;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExternalRequestResponseMapping {

    private String dataVariable;
    private String path;
    private boolean optional;
}
