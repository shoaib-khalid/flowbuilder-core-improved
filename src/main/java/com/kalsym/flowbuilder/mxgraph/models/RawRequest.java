package com.kalsym.flowbuilder.mxgraph.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.minidev.json.JSONObject;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class RawRequest implements Serializable {
    @Id
    private String flowId;
    private MxRequest mxRequest;
    private String rawData;
}
