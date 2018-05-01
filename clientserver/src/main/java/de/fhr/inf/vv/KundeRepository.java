package de.fhr.inf.vv;

import org.hibernate.internal.IteratorImpl;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by be on 11.05.2017.
 */
public interface KundeRepository extends
        CrudRepository<Kunde, Long> {

    Iterable<Kunde> findByNachname(String nachname);
    Iterable<Kunde> findByVornameAndNachname(
            String vorname, String nachname);
}
