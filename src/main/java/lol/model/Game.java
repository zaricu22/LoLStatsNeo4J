package lol.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@NodeEntity
public class Game {
	
	@Id
	@GeneratedValue
	private Long id;
	private int duration;
	@DateString("yyyy-MM-dd HH:mm:ss")
	private Date date;
	private String winner;
	
	
	@JsonIgnoreProperties("game")
	@Relationship(type = "IS_MATCH_HERO", direction = Relationship.INCOMING)
	private List<Hero> heroes  = new ArrayList<>();
	
	
	@JsonIgnoreProperties("game")
	@Relationship(type = "IS_MATCH_PLAYER", direction = Relationship.INCOMING)
	private List<Player> players  = new ArrayList<>();
	
	
	public Game() {
		super();
	}

	public Game(int duration, Date date, String winner) {
		super();
		this.duration = duration;
		this.date = date;
		this.winner = winner;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public List<Hero> getHeroes() {
		return heroes;
	}

	public void setHeroes(List<Hero> heroes) {
		this.heroes = heroes;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public boolean add(Hero e) {
		return heroes.add(e);
	}

	public boolean add(Player e) {
		return players.add(e);
	}
	
}
