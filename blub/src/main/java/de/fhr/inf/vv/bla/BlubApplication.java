package de.fhr.inf.vv.bla;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlubApplication {


	@Bean
	InitializingBean inizialisiere(KundeRepository repo) {
       return () -> {
		   repo.save(new Kunde("Harry","Hirsch"));
		   repo.save(new Kunde("Klaus","Rossteuscher"));
		   repo.save(new Kunde("Gerd","Beneken"));
	   };
	}

	public static void main(String[] args) {
		SpringApplication.run(
				BlubApplication.class, args);
	}
}
