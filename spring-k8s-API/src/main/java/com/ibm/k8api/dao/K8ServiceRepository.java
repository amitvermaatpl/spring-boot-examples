package com.ibm.k8api.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface K8ServiceRepository extends MongoRepository<K8Service, String> {
	K8Service save(K8Service serviceSaved);
}
