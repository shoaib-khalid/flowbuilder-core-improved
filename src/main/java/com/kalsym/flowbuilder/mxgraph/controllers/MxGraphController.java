package com.kalsym.flowbuilder.mxgraph.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.kalsym.flowbuilder.FlowbuilderApplication;
import com.kalsym.flowbuilder.formatted.models.vertex.*;
import com.kalsym.flowbuilder.models.HttpReponse;
import com.kalsym.flowbuilder.mxgraph.models.RawRequest;
import com.kalsym.flowbuilder.mxgraph.models.flows.Flow;
import com.kalsym.flowbuilder.mxgraph.services.MxGraphService;
import com.kalsym.flowbuilder.mxgraph.models.MxRequest;
import com.kalsym.flowbuilder.mxgraph.models.userobject.MxGrahVertex;
import com.kalsym.flowbuilder.utils.Logger;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController()
@RequestMapping("/flows")
public class MxGraphController {

    @Autowired
    MxGraphService mxGraphService;


    @PostMapping(path = {"/"}, name = "post-flows")
    public ResponseEntity<HttpReponse> postFlows(HttpServletRequest request,
                                                 @RequestBody Flow body){
        String logprefix = request.getRequestURI();
        HttpReponse response = new HttpReponse(request.getRequestURI());
        Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "uri: " + logprefix, "");

        try{
            response.setData(mxGraphService.saveFlow(body));
        }catch (Exception e) {
            Logger.application.error(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "Flow could not be saved", "", e);
            response.setErrorStatus(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(path = {"/{flowId}/mxgraph"}, name = "post-mxgraph")
    public ResponseEntity<HttpReponse> postMxGraph(HttpServletRequest request,
                                            @RequestBody String body,
                                            @PathVariable String flowId) {
        String logprefix = request.getRequestURI();
        HttpReponse response = new HttpReponse(request.getRequestURI());
        Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "uri: " + logprefix, "");

        MxGrahVertex mxGraphVertex = null;

        MxRequest mxRequest = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            mxRequest = objectMapper.readValue(body, MxRequest.class);

            List<Vertex> vertexes = mxGraphService.generateFormattedVertexes(mxRequest, flowId);
            mxGraphService.saveRawBody(flowId,body);
//            Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "rawBody : " + , "");
            response.setData(vertexes);

        } catch (Exception e) {
            Logger.application.error(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "json could not be conversted", "", e);
            response.setErrorStatus(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "json converteduccessfully", "");

        response.setSuccessStatus(HttpStatus.OK);

        Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "response :"+response, "");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping(path = {"/{flowId}/mxgraph"}, name = "get-mxgraph")
    public ResponseEntity<HttpReponse> getRawMxGraph(HttpServletRequest request,
                                                  @PathVariable String flowId){
        String logprefix = request.getRequestURI();
        HttpReponse response = new HttpReponse(request.getRequestURI());
        Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "uri: " + logprefix, "");

        RawRequest rawRequest = mxGraphService.getRawRequest(flowId);

        if(null == rawRequest){
            response.setErrorStatus(HttpStatus.NOT_FOUND);
            response.setData("Raw Json with flowId : "+flowId+" not found !");

            Logger.application.error(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "No flows found ", "");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        response.setData(rawRequest.getRawData());
        response.setSuccessStatus(HttpStatus.OK);

        Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "response  : "+response, "");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping (path = {"/"}, name = "get-flows")
    public ResponseEntity<HttpReponse> getFlows(HttpServletRequest request,
                                                @RequestParam String storeId,
                                                @RequestParam String ownerId){
        String logprefix = request.getRequestURI();
        HttpReponse response = new HttpReponse(request.getRequestURI());
        Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "uri: " + logprefix, "");


        List<Flow> flows = mxGraphService.getFlows(storeId, ownerId);

        if(null == flows){
            response.setErrorStatus(HttpStatus.NOT_FOUND);
            response.setData("No records found !");
            Logger.application.error(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "No flows found ", "");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "flows size: " + flows.size(), "");

        response.setData(flows);
        response.setSuccessStatus(HttpStatus.OK);

        Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "response: " + response, "");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping (path = {"/{flowId}/publish"}, name = "publish-flow")
    public ResponseEntity<HttpReponse> publishFlow(HttpServletRequest request,
                                                @PathVariable String flowId,
                                                @RequestParam String botId)
    {
        String logprefix = request.getRequestURI();
        HttpReponse response = new HttpReponse(request.getRequestURI());
        Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "uri: " + logprefix, "");

        Flow flow = mxGraphService.findFlowById(flowId);

        if( null == flow ){
            response.setErrorStatus(HttpStatus.NOT_FOUND);
            response.setData("Flow with flowId : "+flowId+" not found !");
            Logger.application.error(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "Flow with flowId : "+flowId+" not found !", "");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }



        RawRequest rawRequest = mxGraphService.getRawRequest(flowId);
        if(null == rawRequest){
            response.setErrorStatus(HttpStatus.NOT_FOUND);
            response.setData("Raw Json with flowId : "+flowId+" not found !");
            Logger.application.error(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "Raw Json with flowId : "+flowId+" not found !", "");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        MxRequest mxRequest = rawRequest.getMxRequest();
        List<Vertex> vertices = mxGraphService.generateFormattedVertexes(mxRequest, flowId);
        Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "saved formatted vertices, size : " + vertices.size(), "");


        flow = mxGraphService.reAssignBot(botId, flow);

        Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "Assigned bot : " + botId + " to Flow : "+flow, "");

        response.setSuccessStatus(HttpStatus.OK);
        response.setData(vertices);

        Logger.application.info(Logger.pattern, FlowbuilderApplication.VERSION, logprefix, "response: " + response, "");


        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



//    Vertex prepareVertex(String json) {
//
//        DocumentContext jsonContext = JsonPath.parse(json);
//
//        Vertex vertex = new Vertex();
//
//        vertex.setId(jsonContext.read("$.userObject.@id"));
//
//        Info info = new Info();
//
//        info.setTitle(jsonContext.read("$.userObject.mxCell.div.div.div[1].div[0].div.h4.#text"));
//        info.setText(jsonContext.read("$.userObject.mxCell.div.div.div[1].div[1].span.#text"));
//
//        vertex.setInfo(info);
//        return vertex;
//    }
    
    
    

}
