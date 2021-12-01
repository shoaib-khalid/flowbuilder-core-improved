package com.kalsym.flowbuilder.mxgraph.models.data;

import com.kalsym.flowbuilder.formatted.models.vertex.Condition;
import com.kalsym.flowbuilder.formatted.models.vertex.VertexType;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author saros
 */
@Getter
@Setter
@NoArgsConstructor
public class Data {

    private VertexType type;
    private List<Button> buttons;
    private String vertexId;
    private List<DataVariable> dataVariables;
    private List<RawCondition> conditions;
    private Object actions;
}
