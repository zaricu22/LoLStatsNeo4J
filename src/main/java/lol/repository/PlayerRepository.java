package lol.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import lol.model.Player;

public interface PlayerRepository extends Neo4jRepository<Player, String> {
	List<Player> findAll();
	
	@Query("MATCH (p:Player) RETURN p")
	List<Player> sviIgraci();
	
	Player findByUsername(@Param("username") String username);
	
	@Query("MATCH (p:Player)-[r:IS_MATCH_PLAYER]->(g:Game)\r\n" + 
			"WHERE p.username = {username}\r\n" + 
			"RETURN sum(r.death)/((count(g))+0.00000000001) as death, sum(r.assist)/((count(g))+0.00000000001) as assist, sum(r.kill)/((count(g))+0.00000000001) as kill, "
			+ "sum(r.doublekill)/((count(g))+0.00000000001) as dkill, sum(r.triplekill)/((count(g))+0.00000000001) as tkill")
	List<Map<String, Object>> playerDKA(@Param("username") String username);
	
	@Query("MATCH (p:Player)-[r:IS_MATCH_PLAYER]->(g:Game)\r\n" + 
			"	WHERE p.username = {username}\r\n" + 
			"	RETURN (sum(r.kill) + sum(r.assist))*1.0 / ((sum(r.death)*1.0)+0.00000000001)")
	Float playerDKARate(@Param("username") String username);
	
	@Query("MATCH (p:Player)-[r:IS_MATCH_PLAYER]->(g:Game)\r\n" + 
			"	WHERE p.username = {username}\r\n" + 
			"	RETURN p")
	Player player(@Param("username") String username);
	
	@Query("MATCH (p:Player)-[r:IS_MATCH_PLAYER]->(g:Game)\r\n" + 
			"WHERE p.username = {username}\r\n" + 
			"RETURN count(g)")
	Integer playerBrMec(@Param("username") String username);
	
	@Query("MATCH (p:Player)-[r:IS_MATCH_PLAYER]->(g:Game)\r\n" + 
			"WHERE p.username = {username} and r.team = g.winner\r\n" + 
			"RETURN count(g)")
	Integer playerBrPob(@Param("username") String username);
	
	@Query("MATCH (p:Player)-[r:IS_MATCH_PLAYER]->(g:Game), (h:Player)-[t:IS_MATCH_PLAYER]->(g)\r\n" + 
			"WHERE p.username = {username} and r.team = t.team\r\n" + 
			"RETURN h.username as username, count(g) as brMec\r\n" + 
			"ORDER BY h.username")
	List<Map<String, Object>> saigraciMec(@Param("username") String username);
	
	@Query("MATCH (p:Player)-[r:IS_MATCH_PLAYER]->(g:Game), (h:Player)-[t:IS_MATCH_PLAYER]->(g)\r\n" + 
			"WHERE p.username = {username} and r.team = t.team and r.team = g.winner\r\n" + 
			"RETURN h.username as username, count(g) as brPob\r\n" + 
			"ORDER BY h.username")
	List<Map<String, Object>> saigraciPob(@Param("username") String username);
	
	@Query("MATCH (p:Player)-[r:IS_MATCH_PLAYER]->(g:Game), (h:Hero)-[t:IS_MATCH_HERO]->(g)\r\n" + 
			"WHERE p.username = {username} and t.position = r.position and r.team = t.team\r\n" + 
			"RETURN h.name as name, h.image as image, count(g) as brMec\r\n" + 
			"ORDER BY h.name")
	List<Map<String, Object>> najHerojiMec(@Param("username") String username);
	
	@Query("MATCH (p:Player)-[r:IS_MATCH_PLAYER]->(g:Game), (h:Hero)-[t:IS_MATCH_HERO]->(g)\r\n" + 
			"WHERE p.username = {username} and t.position = r.position and r.team = t.team and r.team = g.winner\r\n" + 
			"RETURN h.name as name, count(g) as brPob\r\n" + 
			"ORDER BY h.name")
	List<Map<String, Object>> najHerojiPob(@Param("username") String username);
	
	@Query("MATCH (p:Player)-[r:IS_MATCH_PLAYER]->(g:Game), (h:Hero)-[t:IS_MATCH_HERO]->(g)\r\n" + 
			"WHERE p.username = {username} and t.position = r.position and r.team = t.team\r\n" + 
			"RETURN h.name as name, sum(r.death) as death, sum(r.assist) as assist, sum(r.kill) as kill\r\n" + 
			"ORDER BY h.name")
	List<Map<String, Object>> najHerojiDKA(@Param("username") String username);
	
	@Query("MATCH (p:Player)\r\n" + 
			"RETURN p.username as username, p.points as bodovi\r\n" + 
			"ORDER BY p.points DESC\r\n" + 
			"LIMIT 10")
	List<Map<String, Object>> igraciBod();
	
	@Query("MATCH (p:Player)-[r:IS_MATCH_PLAYER]->(g:Game)\r\n" + 
			"RETURN p.username as username, ((sum(r.kill) + sum(r.assist))*1.0) / ((sum(r.death)*1.0)+0.00000000001) as dka\r\n" + 
			"ORDER BY dka DESC\r\n" + 
			"LIMIT 10")
	List<Map<String, Object>> igraciKDA();
	
	@Query("MATCH (p:Player)-[t:IS_MATCH_PLAYER]->(g:Game)\r\n" + 
			"RETURN p.username as username, count(g) as brMec\r\n" +
			"ORDER BY p.username")
	List<Map<String, Object>> igraciMec();
	
	@Query("MATCH (p:Player)-[t:IS_MATCH_PLAYER]->(g:Game)\r\n" + 
			"WHERE t.team = g.winner\r\n" + 
			"RETURN p.username as username, count(g) as brPob\r\n" +
			"ORDER BY p.username")
	List<Map<String, Object>> igraciPob();
}
