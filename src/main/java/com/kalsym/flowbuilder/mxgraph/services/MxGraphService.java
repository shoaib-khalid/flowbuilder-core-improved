package com.kalsym.flowbuilder.mxgraph.services;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.kalsym.flowbuilder.FlowbuilderApplication;
import com.kalsym.flowbuilder.formatted.models.vertex.*;
import com.kalsym.flowbuilder.mxgraph.models.MxRequest;
import com.kalsym.flowbuilder.mxgraph.models.RawRequest;
import com.kalsym.flowbuilder.mxgraph.models.connectionend.ConnectionEnd;
import com.kalsym.flowbuilder.mxgraph.models.connectionstart.ConnectionStart;
import com.kalsym.flowbuilder.mxgraph.models.data.Button;
import com.kalsym.flowbuilder.mxgraph.models.data.Data;
import com.kalsym.flowbuilder.mxgraph.models.data.DataVariable;
import com.kalsym.flowbuilder.mxgraph.models.data.RawCondition;
import com.kalsym.flowbuilder.mxgraph.models.flows.Flow;
import com.kalsym.flowbuilder.mxgraph.models.mxcell.MxCell;
import com.kalsym.flowbuilder.mxgraph.models.triggers.Trigger;
import com.kalsym.flowbuilder.mxgraph.models.userobject.UserObject;
import com.kalsym.flowbuilder.mxgraph.repositories.FlowRespository;
import com.kalsym.flowbuilder.mxgraph.repositories.MxGraphRepository;
import com.kalsym.flowbuilder.mxgraph.repositories.RawRequestRepository;
import com.kalsym.flowbuilder.utils.Logger;

import java.util.*;
import java.util.stream.Collectors;

import net.bytebuddy.pool.TypePool;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
public class MxGraphService {

    @Autowired
    MxGraphRepository mxGraphRepository;

    @Autowired
    FlowRespository flowRespository;

    @Autowired
    RawRequestRepository rawRequestRepository;

    public List<Vertex> generateFormattedVertexes(MxRequest mxRequest, String flowId) {
        String logprefix = "generateFormattedVertexes";

        Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "uri: " + logprefix, "");

        List<Vertex> vertexes = new ArrayList<>();

        for (UserObject userObject : mxRequest.getMxGraphModel().getRoot().getUserObject()) {

            //get Data array from request
            Data data = mxRequest.getData()
                    .stream()
                    .filter(e -> e.getVertexId().equals(userObject.getId()))
                    .findAny()
                    .orElse(null);

            List<Trigger> tiggers = mxRequest.getMxGraphModel().getRoot().getTriggers()
                    .stream()
                    .filter(e -> e.getMxCell().getParent().equals(userObject.getId()))
                    .collect(Collectors.toList());

            String conditionsInString = null;
            if(mxRequest.getMxGraphModel().getRoot().getConditions() != null ){
                conditionsInString = mxRequest.getMxGraphModel().getRoot().getConditions().toString().trim();
            }
            Vertex vertex = generateVertex(
                    userObject,
                    data,
                    tiggers,
                    mxRequest.getMxGraphModel().getRoot().getConnectionStart(),
                    mxRequest.getMxGraphModel().getRoot().getConnectionEnd(),
                    mxRequest.getMxGraphModel().getRoot().getMxCell(),
                    conditionsInString);

            //this should be added at end
            vertex.setFlowId(flowId);
            vertex.setCreatedDate(new Date());
            mxGraphRepository.save(vertex);

            RawRequest rawRequest = new RawRequest();
            rawRequest.setFlowId(flowId);
            rawRequest.setMxRequest(mxRequest);
            rawRequestRepository.save(rawRequest);

            Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "Raw request saved with id: " + flowId, "");

            vertexes.add(vertex);
        }

        Flow flow = findFlowById(flowId);
        if(null != flow)
        {
            flow.setTopVertexId(vertexes.get(0).getMxGraphId());
            flowRespository.save(flow);
        }else{
            Logger.application.warn(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "flow with id : " + flowId+" not found !", "");
        }


        return vertexes;
    }

    public Vertex generateVertex(UserObject userObject,
            Data data,
            List<Trigger> tiggers,
            List<ConnectionStart> connectionStartUnfiltered,
            List<ConnectionEnd> connectionEnds,
            List<MxCell> mxCells, String conditions) {

        String logprefix = "generateVertex";
        Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "userObject.getId(): " + userObject.getId(), "");

        Vertex vertex = getVertex(userObject, data, tiggers);


        try {
            List<ConnectionStart> connectionStartFilteredList = new ArrayList<>();
            connectionStartFilteredList = connectionStartUnfiltered.stream()
                    .filter(e -> e.getMxCell().getParent().equals(userObject.getId()))
                    .collect(Collectors.toList());
            ConnectionStart connectionStart = connectionStartFilteredList.get(0);

            //Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "connectionStart: " + connectionStart, "");

            MxCell mxCell = mxCells.stream()
                        .filter(e -> {
                            //Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "e.getSource(): " + e.getSource(), "");
                            //Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "connectionStart.getId(): " + connectionStart.getId(), "");

                            return e.getSource() != null && e.getSource().equals(connectionStart.getId());
                        })
                        .findAny()
                        .orElse(null);

            ConnectionEnd connectionEnd = null;

            if(mxCell != null)
                connectionEnd = connectionEnds.stream().filter(e -> e.getId().equals(mxCell.getTarget())).findAny().orElse(null);



            //Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "mxCell: " + mxCell, "");

            Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "DataVariable size  :::  " + data.getDataVariables().size() + " " + data.getDataVariables().get(0).getDataVariable(), "");

//            Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "data vertex id :::  " + data.getVertexId(), "");
//            Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "generated Verted id :::  " + userObject.getId(), "");




            if(data.getVertexId().equals(userObject.getId()) && data.getDataVariables().get(0) != null){
                vertex.setDataVariable(data.getDataVariables().get(0).getDataVariable());
            }

            if (connectionEnd != null || isOptionConnected(vertex.getOptions())) {
                Step step = new Step();
                step.setTargetType(VertexTargetType.VERTEX);
                step.setTargetMxGraphId(connectionEnd.getMxCell().getParent());
                step.setTargetId(connectionEnd.getMxCell().getParent());
//                step.setConnectionStart(connectionStart);

                Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "Target ID :::  " + mxCell.getTarget(), "");
                //step.setTargetId();
//                step.setMxCell(mxCell);
                vertex.setStep(step);
                vertex.setIsLastVertex(0);
            }else {
                Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "IsLastVertex :::  True", "");
                vertex.setIsLastVertex(1);
                vertex.setStep(null);
            }

        } catch (Exception e) {
            Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "cannot read targets", "", e);
        }

        try {
            if (vertex.getOptions() != null) {
                for (int i = 0; i < vertex.getOptions().size(); i++) {
                    Option option = vertex.getOptions().get(i);

                    Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "mxGraphId: " + userObject.getId() + " optionMxGraphId: " + option.getMxGraphId(), "");

                    ConnectionStart optionConnectionStart = connectionStartUnfiltered.stream()
                            .filter(e -> e.getMxCell().getParent().equals(option.getMxGraphId()))
                            .findAny()
                            .orElse(null);

                    MxCell optionMxCell = mxCells.stream()
                            .filter(e -> e.getSource() != null && e.getSource().equals(optionConnectionStart.getId()))
                            .findAny()
                            .orElse(null);

                    ConnectionEnd connectionEnd = null;

                    if(optionMxCell != null)
                        connectionEnd = connectionEnds.stream().filter(e -> e.getId().equals(optionMxCell.getTarget())).findAny().orElse(null);


                    if (connectionEnd != null) {
                        Step optionStep = new Step();
                        optionStep.setTargetMxGraphId(connectionEnd.getMxCell().getParent());
                        optionStep.setTargetId(connectionEnd.getMxCell().getParent());
//                        optionStep.setMxCell(optionMxCell);
                        vertex.getOptions().get(i).setStep(optionStep);
                    }


                }
            }


            if(data.getConditions() != null){
                for(int i=0; i< data.getConditions().size(); i++){
                    DocumentContext jsonContext = JsonPath.parse(conditions);
                    Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "Conditions Array : " + conditions.getClass(), "");

                    String conditionParent = jsonContext.read("$.["+i+"].['@id']").toString();
                    Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "ConditionParent : " + conditionParent
                            , "");

                    ConnectionStart connectionStart = connectionStartUnfiltered.stream()
                            .filter(e -> e.getMxCell().getParent().equals(conditionParent))
                            .findAny().orElse(null);

                    MxCell mxCell = mxCells.stream()
                            .filter(e -> e.getSource() != null && e.getSource().equals(connectionStart.getId()))
                            .findAny()
                            .orElse(null);

                    ConnectionEnd connectionEnd = null;

                    if(mxCell != null)
                        connectionEnd = connectionEnds.stream().filter(e -> e.getId().equals(mxCell.getTarget())).findAny().orElse(null);

                    if(null != connectionEnd){
                        Step step = new Step();
                        step.setTargetMxGraphId(connectionEnd.getMxCell().getParent());
                        step.setTargetId(connectionEnd.getMxCell().getParent());
//                        optionStep.setMxCell(optionMxCell);
                        Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "Conditions Length : " + vertex.getConditions(), "");

                        vertex.getConditions().get(i).setStep(step);
                    }

                }
            }

            //todo: Handle Actions
            if(data.getType().equals(VertexType.ACTION) && data.getActions() != null){
                DocumentContext jsonContext = JsonPath.parse(data.getActions());
                List<JSONObject> actionsArray = new ArrayList<>(jsonContext.json());

                Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "Actions  : " +data.getVertexId()+"  "+ jsonContext.read("$.*"), "");

                List<Action> actions = new ArrayList<>();
                vertex.setActions(actions);

                for(int i=0; i<actionsArray.size(); i++){
                    String actionVertexId = data.getVertexId();
                    Action action = new Action();
                    action.setType(VertexActionType.EXTERNAL_REQUEST);
                    ObjectMapper mapper = new ObjectMapper();

                    ExternalRequest externalRequest = new ExternalRequest();
                    externalRequest.setUrl(jsonContext.read("$.["+i+"].externalRequest.url"));
                    externalRequest.setBody(mapper.readValue(new JSONObject(jsonContext.read("$.["+i+"].externalRequest.body")).toString(), ExternalRequestBody.class));
                    externalRequest.setResponse(mapper.readValue(new JSONObject(jsonContext.read("$.["+i+"].externalRequest.response")).toString(), ExternalRequestReponse.class));
                    List<ExternalRequestHeader> headers = new ArrayList<ExternalRequestHeader>(jsonContext.read("$.["+i+"].externalRequest.headers"));
                    externalRequest.setHeaders(headers);
                    externalRequest.setHttpMethod(HttpMethod.resolve(jsonContext.read("$.["+i+"].externalRequest.httpMethod")));

                    ConnectionStart connectionStart = connectionStartUnfiltered.stream()
                            .filter(e -> e.getMxCell().getParent().equals(actionVertexId))
                            .findAny().orElse(null);


                    MxCell mxCell = mxCells.stream()
                            .filter(e -> e.getSource() != null && e.getSource().equals(connectionStart.getId()))
                            .findAny()
                            .orElse(null);

                    ConnectionEnd connectionEnd = null;

                    if(mxCell != null)
                        connectionEnd = connectionEnds.stream().filter(e -> e.getId().equals(mxCell.getTarget())).findAny().orElse(null);

                    if(null != connectionEnd){
                        Step step = new Step();
//                        mxCell.getTarget()
                        step.setTargetMxGraphId(connectionEnd.getMxCell().getParent());
                        step.setTargetId(connectionEnd.getMxCell().getParent());
                        Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "Conditions Length : " + vertex.getConditions(), "");
                        externalRequest.setErrorStep(step);
                        action.setExternalRequest(externalRequest);
                        vertex.getActions().add(action);
                    }

                }


            }


        } catch (Exception e) {
            Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "cannot read targets for option", "", e);
        }
//        vertex.setId(userObject.getId());

        return vertex;
    }

    public Vertex getVertex(UserObject userObject,
            Data data,
            List<Trigger> tiggers) {

        String logprefix = "getVertex";

        Vertex vertex = new Vertex();

        vertex.setMxGraphId(userObject.getId());

        Info info = new Info();

        DocumentContext jsonContext;
        boolean spanExists;
        String infoText = "";

        if(null != userObject.getMxCell().getDiv().getDiv().getDiv().get(1).getDiv().get(1).getSpan())
        {
            spanExists = true;
            jsonContext = JsonPath.parse( userObject.getMxCell().getDiv().getDiv().getDiv().get(1).getDiv().get(1).getSpan());
        }else{
            spanExists = false;
            infoText = userObject.getMxCell().getDiv().getDiv().getDiv().get(1).getDiv().get(1).getDiv().getH4().getText();
            jsonContext = null;
        }
//        if(userObject.getMxCell().getDiv().getDiv().getDiv().get(1).getDiv().size() > 1)
//        {
//            Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "Div___ value : "+ userObject.getMxCell().getDiv().getDiv().getDiv().get(1).getDiv().get(1).toString(), "");
//            jsonContext = JsonPath.parse( userObject.getMxCell().getDiv().getDiv().getDiv().get(1).getDiv().get(1).getSpan());
//        }else{
//            jsonContext = JsonPath.parse( userObject.getMxCell().getDiv().getDiv().getDiv().get(1).getDiv().get(0).getSpan());
//        }


        try {
            info.setTitle(userObject.getMxCell().getDiv().getDiv().getDiv().get(1).getDiv().get(0).getDiv().getH4().getText());
//            info.setText(userObject.getMxCell().getDiv().getDiv().getDiv().get(1).getDiv().get(1).getSpan().getText());
            if(spanExists && jsonContext != null){
                if(data.getType().equals(VertexType.CONDITION)){
                    info.setText(jsonContext.read("$.[0].['#text']"));
                }else{
                    info.setText(jsonContext.read("$['#text']"));
                }
            }else{
                info.setText(infoText);
            }

            info.setType(data.getType());
        } catch (Exception e) {
            Logger.application.error(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "could not read info", "", e);
            return null;
        }

        if (tiggers != null) {
            vertex.setOptions(loadVertexOptions(tiggers, data));
        }
        if(data.getConditions() != null){
            Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "IN SET CONDITIONS, CONDTIONS VALUE : " + loadVertexConditions(data), "");

            vertex.setConditions(loadVertexConditions(data));
        }

        //below is if you are using json path
        //info.setTitle(jsonContext.read("$.userObject.mxCell.div.div.div[1].div[0].div.h4.#text"));
        //info.setText(jsonContext.read("$.userObject.mxCell.div.div.div[1].div[1].span.#text"));
        vertex.setInfo(info);
        return vertex;
    }

    List<Option> loadVertexOptions(List<Trigger> triggers, Data data) {

        String logprefix = "loadVertexOptions";
        List<Option> options = new ArrayList<>();

        Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "button count  : " + triggers.size() , "");


        for (int i = 0; i < triggers.size(); i++) {

            try {
                Trigger trigger = triggers.get(i);
                Button button = data.getButtons().get(i);

                //json-path: $.mxCell.div.div.button.#text
                Option option = new Option();
                Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "button value : " + button.getBtnValue(), "");
                option.setValue(button.getBtnValue());
                option.setText(trigger.getMxCell().getDiv().getDiv().getButton().getText());
                option.setMxGraphId(trigger.getId());
                options.add(option);
            } catch (Exception e) {
                Logger.application.error(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "could not read trigger at index: " + i, "", e);
            }

        }
        return options;
    }

    List<Condition> loadVertexConditions(Data data){
        List<RawCondition> rawConditions = data.getConditions();
        Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, "logprefix", "RAW CONDITIONS : " + rawConditions, "");


        List<Condition> conditions = new ArrayList<>();
        for(int i=0; i<rawConditions.size(); i++){
            Condition condition = new Condition();
            condition.setGroups(rawConditions.get(i).getGroups());
            condition.setOperator(rawConditions.get(i).getOperator());
            condition.setStep(null);
            conditions.add(condition);
        }

    return conditions;
    }


    public Flow saveFlow(Flow flow){
        return flowRespository.save(flow);
    }

    public RawRequest getRawRequest(String flowId){
        Optional<RawRequest> rawRequest =rawRequestRepository.findById(flowId);
        return rawRequest.orElse(null);
    }

    public List<Flow> getFlows(String storeId, String ownerId){
        List<Flow> flows = flowRespository.findAllByStoreIdOwnerID(storeId, ownerId);
        return flows.size() > 0 ? flows : null;
    }

    public Flow reAssignBot(String botId, Flow flow){
        List<Flow> flows = flowRespository.findByBotId(botId);
        flows.forEach(f -> {
            if(null != f.getBotIds() && f.getBotIds().size() > 0)
            {
                f.getBotIds().remove(botId);
                flowRespository.save(f);
            }
        });

        if(flow.getBotIds() == null)
            flow.setBotIds(new ArrayList<String>());
        flow.getBotIds().add(botId);
        flowRespository.save(flow);
//        Optional<Flow> flow = flowRespository.findById(flowId);
//        return flow.orElse(null);
        return flow;
    }

    public Flow findFlowById(String flowId){
        Optional<Flow> flow =flowRespository.findById(flowId);
        return flow.orElse(null);
    }

    public boolean isOptionConnected(List<Option> options){
        if( options != null && !options.isEmpty()){
            for(Option opt : options){
                if(opt.getStep() != null){
                    return true;
                }
            }
        }
        return false;
    }

public void saveRawBody(String flowId, String body){
    RawRequest rawRequest = new RawRequest();
    rawRequest.setFlowId(flowId);
    org.json.JSONObject rawBody = new org.json.JSONObject(body);
//    rawRequest.setRawData("{\"mxGraphModel\":"+rawBody.getJSONObject("mxGraphModel").toString()+ "}");
    org.json.JSONObject mxGraphModel = new org.json.JSONObject();
    mxGraphModel.append("mxGraphModel", rawBody.getJSONObject("mxGraphModel"));
//    rawRequest.setRawData(mxGraphModel.toString().replaceAll("\\\\", ""));
    rawRequestRepository.save(rawRequest);
}

}
