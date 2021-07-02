package fr.dauphine.miageif.tauxchange.TauxChange.Controller;
import fr.dauphine.miageif.tauxchange.TauxChange.Model.TauxChange;
import fr.dauphine.miageif.tauxchange.TauxChange.Repository.TauxChangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ChangeController {


  // Spring se charge de la création d'instance
  @Autowired
  private TauxChangeRepository repository;

  // GET
    // curl -X GET "http://localhost:8000/taux-change"
    @GetMapping("/taux-change")
    public ResponseEntity<List<TauxChange>> getAllTauxChange(){
      try {
          List<TauxChange> tauxChanges = new ArrayList<TauxChange>();
          repository.findAll().forEach(tauxChanges::add);

          if (tauxChanges.isEmpty()) {
              return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }

          return new ResponseEntity<>(tauxChanges, HttpStatus.OK);
      } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    // curl -X GET "http://localhost:8000/taux-change/source/EUR"
    @GetMapping("/taux-change/source/{source}")
    public ResponseEntity<List<TauxChange>> getTauxChangeBySource
          (@PathVariable String source){
      try{
        List<TauxChange> tauxChanges = repository.findBySource(source);

        if (tauxChanges.isEmpty()) {
              return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }
          return new ResponseEntity<>(tauxChanges, HttpStatus.OK);
      } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    // curl -X GET "http://localhost:8000/taux-change/dest/USD"
    @GetMapping("/taux-change/dest/{dest}")
    public ResponseEntity<List<TauxChange>> getTauxChangeByDest
            (@PathVariable String dest){
        try{
            List<TauxChange> tauxChanges = repository.findByDest(dest);

            if (tauxChanges.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tauxChanges, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // curl -X GET "http://localhost:8000/taux-change/date/2021-05-25"
    @GetMapping("/taux-change/date/{date}")
    public ResponseEntity<List<TauxChange>> getTauxChangeByDate
            (@PathVariable String date){
        try{
            List<TauxChange> tauxChanges = repository.findByDate(date);

            if (tauxChanges.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tauxChanges, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // curl -X GET "http://localhost:8000/taux-change/source/EUR/dest/USD"
    @GetMapping("/taux-change/source/{source}/dest/{dest}")
    public ResponseEntity<List<TauxChange>> getTauxChangeBySourceAndDest
    (@PathVariable String source, @PathVariable String dest){
      try{
          List<TauxChange> tauxChange = repository.findBySourceAndDest(source, dest);

          if (tauxChange == null) {
              return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }
          return new ResponseEntity<>(tauxChange, HttpStatus.OK);
      } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    // curl -X GET "http://localhost:8000/taux-change/source/EUR/dest/USD/date/2021-06-23"
    @GetMapping("/taux-change/source/{source}/dest/{dest}/date/{date}")
    public ResponseEntity<TauxChange> getTauxChangeBySourceAndDestAndDate
    (@PathVariable String source, @PathVariable String dest, @PathVariable String date){
        try{
            TauxChange tauxChange = repository.findBySourceAndDestAndDate(source, dest, date);

            if (tauxChange == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tauxChange, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // curl -X GET "http://localhost:8000/taux-change/id/10001"
    @GetMapping("/taux-change/id/{id}")
    public ResponseEntity<TauxChange> getTauxChangeByID
            (@PathVariable Long id){
        Optional<TauxChange> tauxChange = repository.findById(id);

        if (tauxChange.isPresent()) {
            return new ResponseEntity<>(tauxChange.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // POST
    // curl -X POST -H "Content-type: application/json" -d "{\"source\" : \"AUD\", \"dest\" : \"USD\", \"taux\" : 0.7586, \"date\" : \"2021-06-25\"}" "http://localhost:8000/taux-change"
    @PostMapping(value = "/taux-change",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<TauxChange> createTauxChange(@RequestBody TauxChange tauxChange){
        TauxChange verif = repository.findBySourceAndDestAndDate(tauxChange.getSource(), tauxChange.getDest(), tauxChange.getDate());
        if (verif == null) {
            try {
                TauxChange _tauxChange = repository.save(tauxChange);
                return new ResponseEntity<>(_tauxChange, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    // DELETE
    // curl -X DELETE "http://localhost:8000/taux-change/id/10016"
    @DeleteMapping("/taux-change/id/{id}")
    public ResponseEntity<HttpStatus> deleteTauxChange(@PathVariable long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // PUT
    // curl -X PUT -H "Content-type: application/json" -d "{\"source\" : \"AUD\", \"dest\" : \"USD\", \"taux\" : 0.7582, \"date\" : \"2021-06-24\"}" "http://localhost:8000/taux-change/id/10016"
    @PutMapping("/taux-change/id/{id}")
    public ResponseEntity<TauxChange> updateTauxChange(@PathVariable long id, @RequestBody TauxChange tauxChange) {

        Optional<TauxChange> tauxChangeData = repository.findById(id);

        if (tauxChangeData.isPresent()) {
            TauxChange _tauxChange = tauxChangeData.get();
            _tauxChange.setSource(tauxChange.getSource());
            _tauxChange.setDestination(tauxChange.getDest());
            _tauxChange.setTaux(tauxChange.getTaux());
            _tauxChange.setDate(tauxChange.getDate());
            return new ResponseEntity<>(repository.save(_tauxChange), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    // curl -X PUT "http://localhost:8000/taux-change/id/10016/date/2020-06-25"
    @PutMapping("/taux-change/id/{id}/date/{date}")
    public ResponseEntity<TauxChange> updateTauxChangeForDate(@PathVariable long id, @PathVariable String date) {
        Optional<TauxChange> tauxChange = repository.findById(id);

        if (tauxChange.isPresent()) {
            TauxChange _tauxChange = tauxChange.get();
            _tauxChange.setDate(date);
            return new ResponseEntity<>(repository.save(_tauxChange), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // curl -X PUT "http://localhost:8000/taux-change/id/10016/taux/0.7582"
    @PutMapping("/taux-change/id/{id}/taux/{taux}")
    public ResponseEntity<TauxChange> updateTauxChangeForDate(@PathVariable long id, @PathVariable BigDecimal taux) {
        Optional<TauxChange> tauxChange = repository.findById(id);

        if (tauxChange.isPresent()) {
            TauxChange _tauxChange = tauxChange.get();
            _tauxChange.setTaux(taux);
            return new ResponseEntity<>(repository.save(_tauxChange), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
