package lol.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lol.repository.PlayerRepository;
import lol.model.Player;

@Controller
@RequestMapping(value="/players")
public class PlayerController {
	@Autowired
	PlayerRepository pr;
	
	@RequestMapping(value="/playerStats", method=RequestMethod.GET)
	public String playerStats(HttpServletRequest request, String username) {
		List<Map<String, Object>> stats = pr.playerDKA(username);
		List<String> userStats = new ArrayList<String>();
		for (Map<String, Object> map : stats) {
			userStats.add(String.valueOf(Math.round(Float.parseFloat(map.get("death").toString()))));
			userStats.add(String.valueOf(Math.round(Float.parseFloat(map.get("assist").toString()))));
			userStats.add(String.valueOf(Math.round(Float.parseFloat(map.get("kill").toString()))));
			userStats.add(String.valueOf(Math.round(Float.parseFloat(map.get("dkill").toString()))));
			userStats.add(String.valueOf(Math.round(Float.parseFloat(map.get("tkill").toString()))));
		}
		String dkaRate = String.format ("%,.2f", pr.playerDKARate(username));
		userStats.add(dkaRate);
		
		Integer brMec = pr.playerBrMec(username);
		userStats.add(String.valueOf(brMec));
		Integer brPob = pr.playerBrPob(username);
		userStats.add(String.valueOf(brPob));
		Integer brPor = brMec - brPob;
		userStats.add(String.valueOf(brPor));
		Integer procPob = Math.round(Float.parseFloat(Double.toString(brPob*1.0/(brMec*1.0/100))));
		userStats.add(String.valueOf(procPob));
		userStats.add(String.valueOf(username));
		Player p = pr.player(username);
		userStats.add(String.valueOf(p.getPoints()));
		userStats.add(String.valueOf(p.getLevel()));
		request.getSession().setAttribute("userStats", userStats);

		// --------------------------	NAJBOLJI SAIGRACI	-------------------------------------
		List<Map<String, Object>> saigracBrMec = pr.saigraciMec(username);
		List<Map<String, Object>> saigracBrPob = pr.saigraciPob(username);
		Iterator<Map<String, Object>> it1 = saigracBrMec.iterator();
		Iterator<Map<String, Object>> it2 = saigracBrPob.iterator();
		List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		boolean prev = false; // dve liste nisu uvek jednake 
		Map<String, Object> map2 = new HashMap<String, Object>(); // npr. ako sa sigracem nema pobede druga lista je kraca
		while (it1.hasNext() && it2.hasNext()) {
			Map<String, Object> map1 = it1.next();
			if(!prev)
				map2 = it2.next();
			ArrayList<String> tempList = new ArrayList<String>();
			tempList.add(map1.get("username").toString());
			if(map1.get("username").toString().equals(map2.get("username").toString())) {
				prev = false;
				tempList.add(String.valueOf(Math.round((Float.parseFloat(map2.get("brPob").toString()) / (Float.parseFloat(map1.get("brMec").toString()) / 100)))));
			} else {
				prev = true;
				tempList.add(String.valueOf(0));
			}
			tempList.add(map1.get("brMec").toString());
			list.add(tempList);
		}
		list.sort(new Comparator<ArrayList<String>>() {
			@Override
			public int compare(ArrayList<String> o1, ArrayList<String> o2) {
				if(Integer.parseInt(o1.get(1)) == Integer.parseInt(o2.get(1)))
					return 0;
				if(Integer.parseInt(o1.get(1)) < Integer.parseInt(o2.get(1)))
					return 1;
				else
					return -1;
			}
		});
		request.getSession().setAttribute("listaSaigraca", list);
		
		
		// --------------------------	NAJBOLJI HEROJI	-------------------------------------
		List<Map<String, Object>> najHerojiBrMec = pr.najHerojiMec(username);
		List<Map<String, Object>> najHerojiBrPob = pr.najHerojiPob(username);
		List<Map<String, Object>> najHerojiDKA = pr.najHerojiDKA(username);
		it1 = najHerojiBrMec.iterator();
		it2 = najHerojiBrPob.iterator();
		Iterator<Map<String, Object>> it3 = najHerojiDKA.iterator();
		List<ArrayList<String>> list2 = new ArrayList<ArrayList<String>>();
		boolean prev2 = false; // dve liste nisu uvek jednake 
		while (it1.hasNext() && it2.hasNext() && it3.hasNext()) {
			Map<String, Object> map1 = it1.next();
			if(!prev2)
				map2 = it2.next();
			Map<String, Object> map3 = it3.next();
			ArrayList<String> tempList = new ArrayList<String>();
			tempList.add(map1.get("name").toString());
			tempList.add(map1.get("image").toString());
			if(map1.get("name").toString().equals(map2.get("name").toString())) {
				prev2 = false;
				tempList.add(String.valueOf(Math.round((Float.parseFloat(map2.get("brPob").toString()) / (Float.parseFloat(map1.get("brMec").toString()) / 100)))));
				
			} else {
				prev2 = true;
				tempList.add(String.valueOf(0));
			}
			tempList.add(map1.get("brMec").toString());
			tempList.add(String.format ("%,.2f",(Float.parseFloat(map3.get("kill").toString())+Float.parseFloat(map3.get("assist").toString()))/Float.parseFloat(map3.get("death").toString())));
			list2.add(tempList);
		}
		list2.sort(new Comparator<ArrayList<String>>() {
			@Override
			public int compare(ArrayList<String> o1, ArrayList<String> o2) {
				if(Integer.parseInt(o1.get(2)) == Integer.parseInt(o2.get(2)))
					return 0;
				if(Integer.parseInt(o1.get(2)) < Integer.parseInt(o2.get(2)))
					return 1;
				else
					return -1;
			}
		});
		request.getSession().setAttribute("najHeroji", list2);
		return "Igraci";
	}
	
	@RequestMapping(value="/igraci", method=RequestMethod.GET)
	public String igraci(HttpServletRequest request) {
		List<Player> players = pr.sviIgraci();
		request.getSession().setAttribute("players", players);
		
		return "Igraci";
	}
}
