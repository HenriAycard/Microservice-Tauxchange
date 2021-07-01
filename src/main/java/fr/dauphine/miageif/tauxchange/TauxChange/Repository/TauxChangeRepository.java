package fr.dauphine.miageif.tauxchange.TauxChange.Repository;
import com.sun.xml.bind.v2.model.core.ID;
import fr.dauphine.miageif.tauxchange.TauxChange.Model.TauxChange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// Creation du JPA Repository basé sur Spring Data
//  Permet de "générer" toutes sortes de requêtes JPA, sans implementation
public interface TauxChangeRepository extends  JpaRepository<TauxChange, Long>{
  // Query Method findBy
  List<TauxChange> findBySourceAndDest(String source, String dest);

  Optional<TauxChange> findById(ID id);

  List<TauxChange> findBySource(String source);

  List<TauxChange> findByDest(String dest);

  List<TauxChange> findByDate(String date);

  TauxChange findBySourceAndDestAndDate(String source, String dest, String date);
}
