package lol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories("lol.repository")
public class LoLStatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoLStatsApplication.class, args);
	}

}
