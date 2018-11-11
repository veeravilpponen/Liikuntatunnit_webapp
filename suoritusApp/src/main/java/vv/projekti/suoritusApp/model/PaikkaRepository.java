package vv.projekti.suoritusApp.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PaikkaRepository extends CrudRepository<Paikka, Long> {

	List<Paikka> findByNimi(@Param(value= "nimi")String nimi);
	
}
