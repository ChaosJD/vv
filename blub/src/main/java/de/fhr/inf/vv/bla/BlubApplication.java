package de.fhr.inf.vv.bla;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
/*
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
*/
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class BlubApplication {


	@Bean
	InitializingBean inizialisiere(KundeRepository repo) {
		return new InitializingBean() {
			@Override
			public void afterPropertiesSet() throws Exception {
				Kunde harry = new Kunde("Harry","Hirsch");
				harry.setAdresse(new Adresse("Hauptstr. 1","Blub Stadt","12345"));
				List<Vertrag> vertraege = new ArrayList<>();
				vertraege.add(new Vertrag(harry, "Haftpficht"));
				vertraege.add(new Vertrag(harry, "BUZ"));
				harry.setVertraege(vertraege);
				repo.save(harry);
				Kunde klaus = new Kunde("Klaus","Rossteuscher");
				klaus.setAdresse(new Adresse("Hauptstr. 34","Bladorf","45688"));
				repo.save(klaus);
				Kunde gerd = new Kunde("Gerd","Beneken");
				gerd.setAdresse(new Adresse("Hochschulstr 1","Tachfurt","34567"));
				repo.save(gerd);
/*
				Account gb = new Account("gerd","maus", true);
				repo2.save(gb);
				Account hb = new Account("heiko","haus", true);
				repo2.save(hb);
*/

			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(
				BlubApplication.class, args);
	}
}

/*
@Service
class AccoutDetailsService implements UserDetailsService {
	private AccountRepository repo;

	@Autowired
	public AccoutDetailsService(AccountRepository repo) {
		this.repo = repo;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repo.findByUsername(username)
				.map(account -> User.withDefaultPasswordEncoder()
						.username(account.getUsername())
						.password(account.getPassword())
						.roles("ROLE ADMIN", "ROLE USER")
						.build()
				).orElseThrow(
						()->new UsernameNotFoundException("Nicht gefunden:" + username) );
	}
}

interface AccountRepository extends JpaRepository<Account, Long>
{
    Optional<Account> findByUsername(String username);
}

@Entity
class Account {
	@Id
	@GeneratedValue
	private Long id;

	private String username, password;
	private boolean active;

	public Account(String username, String password, boolean active) {
		this.username = username;
		this.password = password;
		this.active = active;
	}

	public Account() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
*/
