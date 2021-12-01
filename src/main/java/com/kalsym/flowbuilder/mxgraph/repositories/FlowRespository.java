package com.kalsym.flowbuilder.mxgraph.repositories;

import com.kalsym.flowbuilder.mxgraph.models.flows.Flow;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface FlowRespository extends MongoRepository<Flow, String> {

    @Query(value = "{'botIds' : ?0}")
    public List<Flow> findByBotId(String botId);

    @Query(value = " { '$or' : [{ 'storeId' : ?0} , {'ownerId' : ?1}] }")
    public List<Flow> findAllByStoreIdOwnerID(String storeId, String ownerId);
}
