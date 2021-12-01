package com.kalsym.flowbuilder.mxgraph.models.flows;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@Document(collection = "flow")
public class Flow implements Serializable {

    @Id
    private String id;
    private String title;
    private String description;
    private String storeId;
    private String ownerId;
    private String status;
    private List<String> botIds;
    private String topVertexId;
}
