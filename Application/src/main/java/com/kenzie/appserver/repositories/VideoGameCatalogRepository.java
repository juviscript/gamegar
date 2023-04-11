<<<<<<< HEAD
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
=======
//package com.kenzie.appserver.repositories;
//
//import com.kenzie.appserver.repositories.model.VideoGameCatalogRecord;
//
//import com.kenzie.appserver.service.model.VideoGame;
//import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;
//
//@EnableScan
//@Repository
//public interface VideoGameCatalogRepository extends CrudRepository<VideoGameCatalogRecord, String>{
//}
>>>>>>> 230d7be (Completely recreated all classes that have to do with the Video Game Catalog. Kept original classes but COMMENTED THEM OUT SO THEY DON'T AFFECT CODE: VideoGameCreateRequest -> CatalogCreateRequest, VideoGameResponse -> CatalogResponse, VideoGameUpdateRequest -> CatalogUpdateRequest, CatalogControllerOriginal -> CatalogController, VideoGameCatalogRepository -> CatalogRepository, VideoGame -> Game , VideoGameCatalogService -> CatalogService. Commented out ALL tests to run app. Successfully able to Post, Get, Put, and Delete.)
