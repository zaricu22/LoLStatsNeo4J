package lol.model;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "IS_MATCH_HERO")
public class HeroMatch {
	
	private String position;
	private String team;
	
	@StartNode
	private Hero hero;

	@EndNode
	private Game game;
	
	public HeroMatch() {
	}

	public HeroMatch(String position, String team) {
		this.position = position;
		this.team = team;
	}

	public HeroMatch(String position, String team, Hero hero, Game game) {
		this.position = position;
		this.team = team;
		this.hero = hero;
		this.game = game;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	
}
