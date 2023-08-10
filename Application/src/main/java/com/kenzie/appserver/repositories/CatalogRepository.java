package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.CatalogRecord;
import com.kenzie.appserver.service.model.Game;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@EnableScan
public interface CatalogRepository extends CrudRepository<CatalogRecord, String> {
    Optional<Game> findByTitle (String title);

}
