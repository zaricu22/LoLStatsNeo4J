package lol.model;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "IS_MATCH_PLAYER")
public class PlayerMatch {

	private String position;
	private String team;
	private int death;
	private int assist;
	private int kill;
	private int doublekill;
	private int triplekill;

	@StartNode
	private Player player;

	@EndNode
	private Game game;

	public PlayerMatch() {
	}

	public PlayerMatch(String position, String team, int death, int assist, int kill, int doublekill, int triplekill) {
		this.position = position;
		this.team = team;
		this.death = death;
		this.assist = assist;
		this.kill = kill;
		this.doublekill = doublekill;
		this.triplekill = triplekill;
	}

	public PlayerMatch(String position, String team, int death, int assist, int kill, int doublekill, int triplekill,
			Player player, Game game) {
		this.position = position;
		this.team = team;
		this.death = death;
		this.assist = assist;
		this.kill = kill;
		this.doublekill = doublekill;
		this.triplekill = triplekill;
		this.player = player;
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

	public int getDeath() {
		return death;
	}

	public void setDeath(int death) {
		this.death = death;
	}

	public int getAssist() {
		return assist;
	}

	public void setAssist(int assist) {
		this.assist = assist;
	}

	public int getKill() {
		return kill;
	}

	public void setKill(int kill) {
		this.kill = kill;
	}

	public int getDoublekill() {
		return doublekill;
	}

	public void setDoublekill(int doublekill) {
		this.doublekill = doublekill;
	}

	public int getTriplekill() {
		return triplekill;
	}

	public void setTriplekill(int triplekill) {
		this.triplekill = triplekill;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	

}
