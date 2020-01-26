package lol.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import lol.model.Game;
import lol.model.Player;

public interface GameRepository extends Neo4jRepository<Game, String> {
	List<Game> findAll();
	
	@Query("MATCH (g:Game) RETURN g")
	List<Game> sviMecevi();
	
	Player findByDate(@Param("date") String date);
	
	@Query("CREATE (p:Player {idS: randomUUID(), username: {username}, level: {level}, points: {points}})\r\n" + 
			"RETURN p")
	Player dodajIgraca(@Param("username") String username, @Param("level") int level, @Param("points") int points);
	
	@Query("CREATE (g:Game {idS: randomUUID(), date: {date}, duration: {duration}, winner: {winner}})\r\n" + 
			"RETURN g")
	Game dodajMec(@Param("date") String date, @Param("duration") int duration, @Param("winner") String winner);
	
	@Query("MATCH (p:Player)\r\n" + 
			"WHERE exists(p.idS)\r\n" + 
			"RETURN p.username as username")
	List<Map<String, Object>> igraci();
	
	@Query("MATCH (p:Player)\r\n" + 
			"WHERE p.username = {user}\r\n" + 
			"RETURN p.username as username, p.level as level, p.points as points")
	List<Map<String, Object>> podaciIzabranog(String user);
	
	@Query("MATCH (p:Player)-[]->(g:Game)\r\n" + 
			"WHERE p.username = {user}\r\n" + 
			"RETURN g.date as date")
	List<String> meceviIzabranog(String user);
	
	@Query("MATCH (p:Player {username: {user}})\r\n" + 
			"MATCH (g:Game {date: {date}})\r\n" + 
			"CREATE (p)-[r:IS_MATCH_PLAYER]->(g)")
	void povezi(String user, String date);
	
	@Query("MATCH (p:Player {username: {user}})-[r:IS_MATCH_PLAYER]->(g:Game {date: {date}})\r\n" + 
			"DELETE r")
	void razvezi(String user, String date);
	
	@Query("MATCH (p:Player)\r\n" + 
			"WHERE p.username = {user}\r\n" + 
			"SET p.level = {level}, p.points = {points}")
	List<Map<String, Object>> izmeniIgraca(String user, Integer level, Integer points);
	
	@Query("MATCH (p:Player)\r\n" + 
			"WHERE p.username = {user}\r\n" + 
			"DETACH DELETE p")
	List<Map<String, Object>> izbrisiIgraca(String user);
	
	@Query("MATCH (g:Game)\r\n" + 
			"WHERE exists(g.idS)\r\n" + 
			"RETURN g.date as date")
	List<String> mecevi();
}
