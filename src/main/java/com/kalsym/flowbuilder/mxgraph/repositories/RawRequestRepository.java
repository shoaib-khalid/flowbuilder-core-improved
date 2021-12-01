package com.kalsym.flowbuilder.mxgraph.repositories;

import com.kalsym.flowbuilder.mxgraph.models.RawRequest;
import com.kalsym.flowbuilder.mxgraph.models.flows.Flow;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RawRequestRepository extends MongoRepository<RawRequest, String> {
}
