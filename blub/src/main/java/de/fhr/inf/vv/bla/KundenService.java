package de.fhr.inf.vv.bla;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
            method = RequestMethod.GET,
    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Kunde findKunde(@PathVariable("nummer")String nummer) {
        Long nr = Long.parseLong(nummer);
        System.out.println("Suche:" + nr);
        Kunde k = repo.findById(nr).get();
        return k;
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
}
