package com.kalsym.flowbuilder.formatted.models.vertex;

import lombok.Getter;
import lombok.Setter;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class Condition {

    private ConditionOperator operator;
    private List<ConditionGroup> groups;
    private Step step;
}
