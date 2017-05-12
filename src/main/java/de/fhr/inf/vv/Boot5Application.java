package de.fhr.inf.vv;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Boot5Application {
    @Bean
	InitializingBean seedDatabase(KundeRepository repo) {
		return () -> {
            repo.save(new Kunde("Harry","Hirsch", "2001-10-12"));
			repo.save(new Kunde("Willi","Winzig", "1954-12-08"));
			repo.save(new Kunde("Bernd","Winzig", "2001-03-18"));
		};
	}

    @Bean
	CommandLineRunner beispiel(KundeRepository repo) {
       return args -> {
		   repo.findByNachname("Winzig")
				   .forEach(System.out::println);
		   repo.findByVornameAndNachname("Harry", "Hirsch")
				   .forEach(System.out::println);
	   };
	}


	public static void main(String[] args) {
		SpringApplication.run(Boot5Application.class, args);
	}
}
