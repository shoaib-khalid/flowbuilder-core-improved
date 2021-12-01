package com.kalsym.flowbuilder.formatted.models.vertex;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExternalRequestReponse {

    DataFomat format;

    List<ExternalRequestResponseMapping> mapping;
}
