package com.kalsym.flowbuilder.formatted.models.vertex;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Action {

    private VertexActionType type;

    private ExternalRequest externalRequest;
}
