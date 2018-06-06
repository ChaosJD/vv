package de.fhr.inf.vv.bla;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by be on 16.05.2018.
 */
@RestController
public class KundenService {
    @RequestMapping(value = "tach/{name}")
    public String helloAgain(@PathVariable("name") String wer) {
        return "Hello Again: " +wer ;
    }
    @Autowired
    public KundeRepository repo;

    @RequestMapping(value = "kunden/{nummer}",
            method = RequestMethod.GET)
    public ResponseEntity<Kunde>
        findKunde(@PathVariable("nummer")String nummer) {
        Long nr = Long.parseLong(nummer);
        System.out.println("Suche:" + nr);
        Kunde k = repo.findById(nr).get();
        return new ResponseEntity<Kunde>(k, HttpStatus.OK);
    }


    @RequestMapping(value = "kunden",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Kunde> findKunden() {
        Iterable<Kunde> iterator = repo.findAll();
        List<Kunde> result = new ArrayList<>();
        for (Kunde k: iterator) {
            result.add(k);
        }
        return result;
    }


    @RequestMapping(value = "/kunden/{paul}", method= RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Kunde> aendereKunde(
            @PathVariable String paul, @RequestBody Kunde kNeu) {
        Kunde kAlt = repo.findById(Long.parseLong(paul)).get();
        kAlt.setVorname(kNeu.getVorname());
        kAlt.setNachname(kNeu.getNachname());
        System.out.println("Id " + paul);
        repo.save(kAlt);
        return new ResponseEntity<Kunde> (kAlt, HttpStatus.OK);
    }

    @RequestMapping(value = "/kunden/{id}", method= RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Kunde> loescheKunde(@PathVariable String id) {
        Kunde kAlt = repo.findById(Long.parseLong(id)).get();
        if (kAlt != null) repo.delete(kAlt);
        return new ResponseEntity<Kunde> (kAlt, HttpStatus.OK);
    }

    @RequestMapping(value = "/kunden", method= RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void>  neuerKunde(
            @RequestBody Kunde k, UriComponentsBuilder ucBuilder) {
        repo.save(k);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/kunden/{id}")
                .buildAndExpand(k.getNummer()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


}
