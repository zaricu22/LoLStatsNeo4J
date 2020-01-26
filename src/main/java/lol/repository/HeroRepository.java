package lol.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import lol.model.Hero;
import lol.model.Player;


public interface HeroRepository extends Neo4jRepository<Hero, String> {
	List<Hero> findAll();
	
	@Query("MATCH (h:Hero) RETURN h")
	List<Player> sviIgraci();
	
	Hero findByName(@Param("name") String name);
	
	@Query("MATCH (h:Hero)-[t:IS_MATCH_HERO]->(g:Game), (p:Player)-[r:IS_MATCH_PLAYER]->(g)\r\n" + 
			"WHERE h.name = {name} and t.position = r.position and r.team = t.team\r\n" + 
			"RETURN h.position as pos, h.image as img, sum(r.death)/count(g) as death, sum(r.assist)/count(g) as assist, sum(r.kill)/count(g) as kill, "
			+ "sum(r.doublekill)/count(g) as dkill, sum(r.triplekill)/count(g) as tkill")
	List<Map<String, Object>> heroDKA(@Param("name") String username);
	
	@Query("MATCH (h:Hero)-[t:IS_MATCH_HERO]->(g:Game)\r\n" + 
			"WHERE h.name = {name}\r\n" + 
			"RETURN count(g)")
	Integer heroBrMec(@Param("name") String name);
	
	@Query("MATCH (h:Hero)-[t:IS_MATCH_HERO]->(g:Game), (p:Player)-[r:IS_MATCH_PLAYER]->(g)\r\n" + 
			"WHERE h.name = {name} and t.position = r.position and r.team = t.team and r.team = g.winner\r\n" + 
			"RETURN count(g)")
	Integer heroBrPob(@Param("name") String name);
	
	@Query("MATCH (h:Hero)-[t:IS_MATCH_HERO]->(g:Game)\r\n" + 
			"WHERE h.name = {name}\r\n" + 
			"RETURN t.position as pos, count(t.position)\r\n" + 
			"ORDER BY count(t.position) DESC\r\n" + 
			"LIMIT 1")
	List<Map<String, Object>> heroPos(@Param("name") String name);
	
	@Query("MATCH (h:Hero)-[t:IS_MATCH_HERO]->(g:Game), (d:Hero)-[r:IS_MATCH_HERO]->(g)\r\n" + 
			"WHERE h.name = {name} and r.position = t.position\r\n" + 
			"RETURN d.name as name, d.image as img, count(g) as brMec\r\n" + 
			"ORDER BY d.name")
	List<Map<String, Object>> protivMec(@Param("name") String name);
	
	@Query("MATCH (h:Hero)-[t:IS_MATCH_HERO]->(g:Game), (d:Hero)-[r:IS_MATCH_HERO]->(g)\r\n" + 
			"WHERE h.name = {name} and r.position = t.position and t.team = g.winner\r\n" + 
			"RETURN d.name as name, d.image as img, count(g) as brPob\r\n" + 
			"ORDER BY d.name")
	List<Map<String, Object>> protivPob(@Param("name") String name);
	
	@Query("MATCH (h:Hero)-[t:IS_MATCH_HERO]->(g:Game), (p:Player)-[r:IS_MATCH_PLAYER]->(g)\r\n" + 
			"WHERE h.name = {name} and r.position = t.position and r.team = t.team\r\n" + 
			"RETURN p.username as username, count(g) as brMec\r\n" + 
			"ORDER BY p.username")
	List<Map<String, Object>> najigracMec(@Param("name") String name);
	
	@Query("MATCH (h:Hero)-[t:IS_MATCH_HERO]->(g:Game), (p:Player)-[r:IS_MATCH_PLAYER]->(g)\r\n" + 
			"WHERE h.name = {name} and r.position = t.position and r.team = t.team and t.team = g.winner\r\n" + 
			"RETURN p.username as username, count(g) as brPob\r\n" + 
			"ORDER BY p.username")
	List<Map<String, Object>> najigracPob(@Param("name") String name);
	
	@Query("MATCH (h:Hero)-[t:IS_MATCH_HERO]->(g:Game), (p:Player)-[r:IS_MATCH_PLAYER]->(g)\r\n" + 
			"WHERE t.position = r.position and r.team = t.team\r\n" + 
			"RETURN h.name as name, (sum(r.kill) + sum(r.assist))*1.0 / sum(r.death) as dka\r\n" + 
			"ORDER BY dka DESC\r\n" + 
			"LIMIT 10")
	List<Map<String, Object>> herojiKDA();
	
	@Query("MATCH (h:Hero)-[t:IS_MATCH_HERO]->(g:Game)\r\n" + 
			"RETURN h.name as name, count(g) as pop\r\n" + 
			"ORDER BY pop DESC\r\n" + 
			"LIMIT 10")
	List<Map<String, Object>> herojiPop();
	
	@Query("MATCH (h:Hero)-[t:IS_MATCH_HERO]->(g:Game)\r\n" + 
			"RETURN h.name as name, count(g) as brMec\r\n" + 
			"ORDER BY h.name")
	List<Map<String, Object>> herojiMec();
	
	@Query("MATCH (d:Game)\r\n" + 
			"RETURN count(d)")
	Integer herojiUku();
	
	@Query("MATCH (h:Hero)-[t:IS_MATCH_HERO]->(g:Game), (p:Player)-[r:IS_MATCH_PLAYER]->(g)\r\n" + 
			"WHERE t.position = r.position and r.team = t.team and r.team = g.winner\r\n" + 
			"RETURN h.name as name, count(g) as brPob\r\n" + 
			"ORDER BY h.name")
	List<Map<String, Object>> herojiPob();
}