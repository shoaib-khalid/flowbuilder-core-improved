package com.kalsym.flowbuilder.mxgraph.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kalsym.flowbuilder.mxgraph.models.connectionend.ConnectionEnd;
import com.kalsym.flowbuilder.mxgraph.models.connectionstart.ConnectionStart;
import com.kalsym.flowbuilder.mxgraph.models.data.Data;
import com.kalsym.flowbuilder.mxgraph.models.mxcell.MxCell;
import com.kalsym.flowbuilder.mxgraph.models.triggers.Trigger;
import com.kalsym.flowbuilder.mxgraph.models.userobject.UserObject;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

/**
 *
 * @author saros
 */
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Root {

    List<MxCell> mxCell;

    @JsonProperty("UserObject")
    List<UserObject> UserObject;

    List<Trigger> triggers;

    JSONArray conditions;

    @JsonProperty("ConnectionStart")
    List<ConnectionStart> ConnectionStart;

    @JsonProperty("ConnectionEnd")
    List<ConnectionEnd> ConnectionEnd;
}
