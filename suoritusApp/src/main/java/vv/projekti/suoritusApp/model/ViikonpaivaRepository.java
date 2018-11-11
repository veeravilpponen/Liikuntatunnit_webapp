package vv.projekti.suoritusApp.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ViikonpaivaRepository extends CrudRepository<Viikonpaiva, Long> {
	
	List<Viikonpaiva> findByNimi(@Param(value= "nimi")String nimi);
	
}
