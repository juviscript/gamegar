package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.VideoGameCatalogRecord;


import com.kenzie.appserver.service.model.VideoGame;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@EnableScan
<<<<<<< HEAD

public interface VideoGameCatalogRepository extends CrudRepository<VideoGameCatalogRecord, String> {


=======
@Repository
public interface VideoGameCatalogRepository extends CrudRepository<VideoGameCatalogRecord, String>{
>>>>>>> dcc2baa (Added @Repository annotation to Catalog Repository)
}
