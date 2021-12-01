package com.kalsym.flowbuilder.formatted.models.vertex;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "vertex")
public class Vertex {

    @Id
    private String id;

    private Info info;

    private String mxGraphId;

    private Validation validation;
    private List<Condition> conditions;
    private List<Action> actions;
    private List<Option> options;
    private Handover handover;

    private Step step;

    private String dataVariable;

    private Date createdDate;
    @LastModifiedDate
    private Date lastModifiedDate;

    private String flowId;

    private Integer isLastVertex;

}
