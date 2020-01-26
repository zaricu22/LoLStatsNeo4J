package lol.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lol.repository.*;

import lol.model.*;

@Controller
@RequestMapping(value="/crud")
public class CRUDController {
	@Autowired
	GameRepository gr;
	
	@Autowired
	HeroRepository hr;
	
	@RequestMapping(value="/dodajIgraca", method=RequestMethod.POST)
	public String dodajIgraca(HttpServletRequest request, String uname) {
		Player p = gr.dodajIgraca(uname, 0, 0);

		return "index";
	}
	
	@RequestMapping(value="/dodajMec", method=RequestMethod.POST)
	public String dodajMec(HttpServletRequest request, Integer dur, String win) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Game g = gr.dodajMec(formatter.format(date), dur, win);

		return "index";
	}
	
	@RequestMapping(value="/igrmec", method=RequestMethod.GET)
	public String igraci(HttpServletRequest request) {
		List<Map<String, Object>> list = gr.igraci();
		request.getSession().setAttribute("igraci", list);
		List<String> list2 = gr.mecevi();
		request.getSession().setAttribute("mecevi", list2);
		
		return "index";
	}
	
	@RequestMapping(value="/povezi", method=RequestMethod.GET)
	public String povezi(HttpServletRequest request, String user, String game) {
		gr.povezi(user, game);
		
		return "index";
	}
	
	@RequestMapping(value="/razvezi", method=RequestMethod.GET)
	public String razvezi(HttpServletRequest request, String user, String game) {
		gr.razvezi(user, game);
		
		return "index";
	}
	
	@RequestMapping(value="/izaberiIgraca", method=RequestMethod.GET)
	public String izaberiIgraca(HttpServletRequest request, String user) {
		request.getSession().setAttribute("izabranIgrac", user);
		request.getSession().setAttribute("podaciIzabranog", gr.podaciIzabranog(user));
		List<String> list = gr.meceviIzabranog(user);
		List<String> list2 = gr.mecevi();
		List<ArrayList<String>> list3 = new ArrayList<ArrayList<String>>();
		for (String string : list2) {
			if(list.contains(string)) {
				ArrayList<String> temp = new ArrayList<String>();
				temp.add(string);
				temp.add(user);
				list3.add(temp);
			} else {
				ArrayList<String> temp = new ArrayList<String>();
				temp.add(string);
				list3.add(temp);
			}
		}
		request.getSession().setAttribute("meceviIzabranog", list3);
		
		return "index";
	}
	
	@RequestMapping(value="/izmeniIgraca", method=RequestMethod.POST)
	public String izmeniIgraca(HttpServletRequest request, String user, Integer level, Integer points, String izbrisi) {
		if(izbrisi != null) {
			gr.izbrisiIgraca(user);
			request.getSession().setAttribute("izbrisan", true);
		} else
			gr.izmeniIgraca(user, level, points);
		
		return "index";
	}
}
