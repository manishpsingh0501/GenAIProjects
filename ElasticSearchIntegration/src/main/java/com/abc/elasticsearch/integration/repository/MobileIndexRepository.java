package com.abc.elasticsearch.integration.repository;

import com.abc.elasticsearch.integration.model.entity.MobileIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MobileIndexRepository extends ElasticsearchRepository<MobileIndex, String> {

    Iterable<MobileIndex> findByApiinteractionid(String apiinteractionid);
}
