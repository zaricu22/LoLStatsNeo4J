package lol.controller;

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
import lol.repository.PlayerRepository;

@Controller
@RequestMapping(value="/tables")
public class TabelaController {
	@Autowired
	PlayerRepository pr;
	
	@Autowired
	HeroRepository hr;
	
	@RequestMapping(value="/tabele", method=RequestMethod.GET)
	public String igraci(HttpServletRequest request) {
		//	-------------------------	IGRACI BODOVI	-----------------------
		List<Map<String, Object>> igraciBod = pr.igraciBod();
		request.getSession().setAttribute("igraciBod", igraciBod);
		
		//	-------------------------	IGRACI DKA	-----------------------
		List<Map<String, Object>> igraciKDA = pr.igraciKDA();
		Iterator<Map<String, Object>> it1 = igraciKDA.iterator();
		while (it1.hasNext()) {
			Map<String, Object> map1 = it1.next();
			map1.replace("dka", String.format ("%,.2f", Float.parseFloat(map1.get("dka").toString())));
		}
		request.getSession().setAttribute("igraciKDA", igraciKDA);
		
		//	-------------------------	IGRACI POBEDE	-----------------------
		List<Map<String, Object>> igraciMec = pr.igraciMec();
		List<Map<String, Object>> igraciPob = pr.igraciPob();
		it1 = igraciMec.iterator();
		Iterator<Map<String, Object>> it2 = igraciPob.iterator();
		boolean prev = false; // dve liste nisu uvek jednake 
		Map<String, Object> map2 = new HashMap<String, Object>(); // npr. ako sa horejem nema pobede druga lista je kraca
		while (it1.hasNext() && it2.hasNext()) {
			Map<String, Object> map1 = it1.next();
			if(!prev)
				map2 = it2.next();
			if(map1.get("username").toString().equals(map2.get("username").toString())) {
				prev = false;
				map1.put("procPob", String.valueOf(String.format ("%,.2f", (Float.parseFloat(map2.get("brPob").toString()) / (Float.parseFloat(map1.get("brMec").toString()) / 100)))));
			} else {
				prev = true;
				map1.put("procPob", String.valueOf(0));
			}
		}
		igraciMec.sort(new Comparator<Map<String, Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				if(Float.parseFloat(o1.get("procPob").toString()) == Float.parseFloat(o2.get("procPob").toString()))
					return 0;
				if(Float.parseFloat(o1.get("procPob").toString()) < Float.parseFloat(o2.get("procPob").toString()))
					return 1;
				else
					return -1;
			}
		});
		request.getSession().setAttribute("igraciPob", igraciMec);
		
		//	-------------------------	HEROJI DKA	-----------------------
		List<Map<String, Object>> herojiKDA = hr.herojiKDA();
		it1 = herojiKDA.iterator();
		while (it1.hasNext()) {
			Map<String, Object> map1 = it1.next();
			map1.replace("dka", String.format ("%,.2f", Float.parseFloat(map1.get("dka").toString())));
		}
		request.getSession().setAttribute("herojiKDA", herojiKDA);
		
		//	-------------------------	HEROJI POPULARNOST	-----------------------
		List<Map<String, Object>> herojiPop = hr.herojiPop();
		it2 = herojiPop.iterator();
		Integer ukupno = hr.herojiUku();
		while (it2.hasNext()) {
			Map<String, Object> map1 = it2.next();
			map1.replace("pop", String.format ("%,.2f", Integer.parseInt(map1.get("pop").toString()) / (ukupno*1.0 / 100)));
		}
		request.getSession().setAttribute("herojiPop", herojiPop);
		
		//	-------------------------	HEROJI POBEDE	-----------------------
		List<Map<String, Object>> herojiMec = hr.herojiMec();
		List<Map<String, Object>> herojiPob = hr.herojiPob();
		it1 = herojiMec.iterator();
		it2 = herojiPob.iterator();
		prev = false; // dve liste nisu uvek jednake 
		map2 = new HashMap<String, Object>(); // npr. ako sa horejem nema pobede druga lista je kraca
		while (it1.hasNext() && it2.hasNext()) {
			Map<String, Object> map1 = it1.next();
			if(!prev)
				map2 = it2.next();
			if(map1.get("name").toString().equals(map2.get("name").toString())) {
				prev = false;
				map1.put("procPob", String.valueOf(String.format ("%,.2f", (Float.parseFloat(map2.get("brPob").toString()) / (Float.parseFloat(map1.get("brMec").toString()) / 100)))));
			} else {
				prev = true;
				map1.put("procPob", String.valueOf(0));
			}
		}
		herojiMec.sort(new Comparator<Map<String, Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				if(Float.parseFloat(o1.get("procPob").toString()) == Float.parseFloat(o2.get("procPob").toString()))
					return 0;
				if(Float.parseFloat(o1.get("procPob").toString()) < Float.parseFloat(o2.get("procPob").toString()))
					return 1;
				else
					return -1;
			}
		});
		request.getSession().setAttribute("herojiPob", herojiMec);
		
		return "Tabele";
	}
}
