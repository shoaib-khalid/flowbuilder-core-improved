package com.kalsym.flowbuilder.formatted.models.vertex;

import com.kalsym.flowbuilder.mxgraph.models.connectionstart.ConnectionStart;
import com.kalsym.flowbuilder.mxgraph.models.mxcell.MxCell;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Step {

    private VertexTargetType targetType;
    private String targetId;
    private String targetMxGraphId;

//    private ConnectionStart connectionStart;
//    private MxCell mxCell;
}
