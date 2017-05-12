package de.fhr.inf.vv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
public class KundeService {

    @Autowired
    private KundeRepository repo;

    @RequestMapping(value = "/kunden/{id}", method= RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Kunde> findById(@PathVariable String id) {
        Kunde k = repo.findOne(Long.parseLong(id));
        return new ResponseEntity<Kunde> (k, HttpStatus.OK);
    }

    @RequestMapping(value = "/kunden", method= RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity< List<Kunde>> findAll() {
        List<Kunde> liste = new ArrayList<>();
        Iterable<Kunde> iterator = repo.findAll();
        iterator.forEach(liste::add);
        return new ResponseEntity<List<Kunde>>(liste, HttpStatus.OK);
    }

    @RequestMapping(value = "/kunden/{id}", method= RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Kunde> aendereKunde(
            @PathVariable String id, @RequestBody Kunde kNeu) {
        Kunde kAlt = repo.findOne(Long.parseLong(id));
        kAlt.setVorname(kNeu.getVorname());
        kAlt.setNachname(kNeu.getNachname());
        kAlt.setGeburtstag(kNeu.getGeburtstag());
        repo.save(kAlt);
        return new ResponseEntity<Kunde> (kAlt, HttpStatus.OK);
    }

    @RequestMapping(value = "/kunden/{id}", method= RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Kunde> loescheKunde(@PathVariable String id) {
        Kunde kAlt = repo.findOne(Long.parseLong(id));
        if (kAlt != null) repo.delete(kAlt);
        return new ResponseEntity<Kunde> (kAlt, HttpStatus.OK);
    }

    @RequestMapping(value = "/kunden", method= RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void>  neuerKunde(@RequestBody Kunde k,
                                            UriComponentsBuilder ucBuilder) {
        repo.save(k);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/kunden/{id}").buildAndExpand(k.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

}
