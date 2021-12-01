/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kalsym.flowbuilder.mxgraph.models;

import com.kalsym.flowbuilder.mxgraph.models.data.Data;
import java.util.ArrayList;
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
public class MxRequest {

    ArrayList<Data> data;

    private MxGraphModel mxGraphModel;
}
