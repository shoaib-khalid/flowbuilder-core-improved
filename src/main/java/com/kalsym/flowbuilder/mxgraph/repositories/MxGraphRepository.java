package com.kalsym.flowbuilder.mxgraph.repositories;

import com.kalsym.flowbuilder.formatted.models.vertex.Vertex;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MxGraphRepository extends MongoRepository<Vertex, String> {
}
