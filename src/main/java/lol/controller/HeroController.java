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

import lol.repository.HeroRepository;

@Controller
@RequestMapping(value="/heroes")
public class HeroController {
	@Autowired
	HeroRepository hr;
	
	@RequestMapping(value="/heroStats", method=RequestMethod.GET)
	public String heroStats(HttpServletRequest request, String name) {
		List<Map<String, Object>> stats = hr.heroDKA(name);
		List<String> heroStats = new ArrayList<String>();
		heroStats.add(String.valueOf(name));
		for (Map<String, Object> map : stats) {
			heroStats.add(map.get("pos").toString());
			heroStats.add(map.get("img").toString());
			heroStats.add(map.get("death").toString());
			heroStats.add(map.get("assist").toString());
			heroStats.add(map.get("kill").toString());
			heroStats.add(map.get("dkill").toString());
			heroStats.add(map.get("tkill").toString());
		}
		Integer brMec = hr.heroBrMec(name);
		heroStats.add(String.valueOf(brMec));
		Integer brPob = hr.heroBrPob(name);
		Integer procPob = Math.round(Float.parseFloat(Double.toString(brPob*1.0/(brMec*1.0/100))));
		heroStats.add(String.valueOf(procPob));
		List<Map<String, Object>> pos = hr.heroPos(name);
		if(pos.size() > 0)
			heroStats.add(pos.get(0).get("pos").toString());
		request.getSession().setAttribute("heroStats", heroStats);
		
		// --------------------------	JAK i SLAB PROTIV	-------------------------------------
		List<Map<String, Object>> protivBrMec = hr.protivMec(name);
		List<Map<String, Object>> protivBrPob = hr.protivPob(name);
		Iterator<Map<String, Object>> it1 = protivBrMec.iterator();
		Iterator<Map<String, Object>> it2 = protivBrPob.iterator();
		List<ArrayList<String>> jakProtiv = new ArrayList<ArrayList<String>>();
		boolean prev = false; // dve liste nisu uvek jednake 
		Map<String, Object> map2 = new HashMap<String, Object>(); // npr. ako sa horejem nema pobede druga lista je kraca
		while (it1.hasNext() && it2.hasNext()) {
			Map<String, Object> map1 = it1.next();
			if(!prev)
				map2 = it2.next();
			ArrayList<String> tempList = new ArrayList<String>();
			tempList.add(map1.get("name").toString());
			tempList.add(map1.get("img").toString());
			if(map1.get("name").toString().equals(map2.get("name").toString())) {
				prev = false;
				tempList.add(String.valueOf(Math.round((Float.parseFloat(map2.get("brPob").toString()) / (Float.parseFloat(map1.get("brMec").toString()) / 100)))));
			} else {
				prev = true;
				tempList.add(String.valueOf(0));
			}
			tempList.add(map1.get("brMec").toString());
			jakProtiv.add(tempList);
		}
		jakProtiv.sort(new Comparator<ArrayList<String>>() {
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
		request.getSession().setAttribute("jakProtiv", jakProtiv);
		
		ArrayList<ArrayList<String>> slabProtiv = new ArrayList<>(jakProtiv);
		slabProtiv.sort(new Comparator<ArrayList<String>>() {
			@Override
			public int compare(ArrayList<String> o1, ArrayList<String> o2) {
				if(Integer.parseInt(o1.get(2)) == Integer.parseInt(o2.get(2)))
					return 0;
				if(Integer.parseInt(o1.get(2)) > Integer.parseInt(o2.get(2)))
					return 1;
				else
					return -1;
			}
		});
		request.getSession().setAttribute("slabProtiv", slabProtiv);
		
		// --------------------------	NAJBOLJI IGRACI	-------------------------------------
		List<Map<String, Object>> najigracBrMec = hr.najigracMec(name);
		List<Map<String, Object>> najigracBrPob = hr.najigracPob(name);
		Iterator<Map<String, Object>> it11 = najigracBrMec.iterator();
		Iterator<Map<String, Object>> it12 = najigracBrPob.iterator();
		List<ArrayList<String>> najIgraci = new ArrayList<ArrayList<String>>();
		boolean prev3 = false; // dve liste nisu uvek jednake 
		while (it11.hasNext() && it12.hasNext()) {
			Map<String, Object> map1 = it11.next();
			if(!prev3)
				map2 = it12.next();
			ArrayList<String> tempList = new ArrayList<String>();
			tempList.add(map1.get("username").toString());
			if(map1.get("username").toString().equals(map2.get("username").toString())) {
				prev3 = false;
				tempList.add(String.valueOf(Math.round((Float.parseFloat(map2.get("brPob").toString()) / (Float.parseFloat(map1.get("brMec").toString()) / 100)))));
			} else {
				prev3 = true;
				tempList.add(String.valueOf(0));
			}
			tempList.add(map1.get("brMec").toString());
			najIgraci.add(tempList);
		}
		najIgraci.sort(new Comparator<ArrayList<String>>() {
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
		request.getSession().setAttribute("najIgraci", najIgraci);
		
		return "Heroji";
	}
}
