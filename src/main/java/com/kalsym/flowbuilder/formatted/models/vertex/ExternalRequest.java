package com.kalsym.flowbuilder.formatted.models.vertex;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.kalsym.flowbuilder.FlowbuilderApplication;
import com.kalsym.flowbuilder.utils.Logger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Getter
@Setter
public class ExternalRequest {

    private String url;
    private HttpMethod httpMethod;
    private List<ExternalRequestHeader> headers;
    private ExternalRequestBody body;

    private ExternalRequestReponse response;

    private Step errorStep;

}
