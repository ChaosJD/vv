package de.fhr.inf.vv.bla;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by be on 16.05.2018.
 */
public interface KundeRepository
        extends CrudRepository<Kunde,Long> {

    public Iterable<Kunde> findByNachname(String nachname);
}
