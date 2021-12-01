package com.kalsym.flowbuilder.mxgraph.models.data;

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
public class DataVariable {

    private Integer id;
    private String dataVariable;
    private String path;
    private String optional;
}
