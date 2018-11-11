package vv.projekti.suoritusApp.model;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SuoritusRepository extends CrudRepository<Suoritus, Long> {

    List<Suoritus> findByLaji(@Param(value= "laji") String laji);
    List<Suoritus> findByViikonpaiva(@Param(value= "viikonpaiva") Viikonpaiva viikonpaiva);
    
}
