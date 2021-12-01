package com.kalsym.flowbuilder.mxgraph.models.data;

import com.kalsym.flowbuilder.formatted.models.vertex.ConditionGroup;
import com.kalsym.flowbuilder.formatted.models.vertex.ConditionOperator;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RawCondition{
    public ConditionOperator operator;
    public List<ConditionGroup> groups;
}
