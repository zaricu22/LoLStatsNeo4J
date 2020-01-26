<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HEROJI</title>
<link rel="shortcut icon" type="image/png" href="/LoL/images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="/LoL/styles/styles.css">
</head>
<body>
	<!-- POZADINA -->
	<div id="bg">
		<img src="/LoL/images/background.png" alt="">
	</div>

	<div id="container">
	
		<div id="header">
			<a href="/LoL/index.jsp">
				<span class=logo><img src="/LoL/images/logo.png" height="60"
				width="120" style="position: relative; bottom: 6px; left: 22px;"></span>
			</a>
			<a href="/LoL/Heroji.jsp"><span class=button><p>HEROJI</p></span></a>
			<a href="/LoL/players/igraci"><span class=button><p>IGRAČI</p></span></a>
			<a href="/LoL/tables/tabele"><span class=button><p>TABELE</p></span></a>
		</div>

		<div id="body" style="height: 900px;">

			<div style="width: 1700px; display: block; position: relative; bottom: 265px; margin: auto;">

				<div class="box" style="width: 340px; min-height:630px;">
					<p class="podnaslov">JAK PROTIV</p><hr>
					<c:forEach begin="0" end="4" items="${jakProtiv }" var="items">
						<div id="row">
							<div id="left" style="padding-left: 20px;">
								<img src="${items[1] }" width="50" height="50"> 
								<h3   style="position: relative; bottom: 20px;">${items[0] }</h3>
							</div>
							<div id="middle" style="position: relative; bottom: 20px; padding-left: 45px;"><h2>${items[2] }%</h2><p>POBEDA</p></div>
							<div id="right" style="position: relative; bottom: 20px; padding-left: 45px;"><h2>${items[3] }</h2><p>MEČEVA</p></div>
						</div>
					</c:forEach>
					<c:remove var="jakProtiv"/>
				</div>
				
				<div style="width: 500px; display: inline-block; position: relative; top: 420px;">
				
					<div class="box" style="width: 420px;">
					
						<div id="row">
							<div style="display: table-cell; border-right: 2px solid gray; padding: 0px 5px; text-align: center;">
								<img src="${heroStats[2] }" width="70" height="70" 
								     style="margin: 5px 5px; position: relative; top: 25px; ">
								<h4 style="padding-left: 5px;">${heroStats[0] } (${heroStats[1] })</h4>
							</div>
							<div style="display: table-cell; padding-left: 10px; text-align: center; position: relative; bottom: 20px;">
								<h4>Death</h4><p>${heroStats[3] }</p>
							</div>
							<div style="display: table-cell; padding-left: 10px; text-align: center; position: relative; bottom: 20px;">
								<h4>Assist</h4><p>${heroStats[4] }</p>
							</div>
							<div style="display: table-cell; padding-left: 10px; text-align: center; position: relative; bottom: 20px;">
								<h4>Kill</h4><p>${heroStats[5] }</p>
							</div>
							<div style="display: table-cell; padding-left: 10px; text-align: center; position: relative; bottom: 20px;">
								<h4>DoubleKill</h4><p>${heroStats[6] }</p>
							</div>
							<div style="display: table-cell; padding: 0px 10px; text-align: center; position: relative; bottom: 20px;">
								<h4>TripleKill</h4><p>${heroStats[7] }</p>
							</div>
						</div>
						
					</div>
						
					<div class="box" style="width: 350px; text-align: center;">
						<div id="left" style="padding: 0px 10px;"><h1>${heroStats[9] }% / ${heroStats[8] }</h1><h3>POBEDA / MEČEVA</h3></div>
						<div id="right" style="border-left: 2px solid gray; padding: 0px 5px;"><h1>${heroStats[10] }</h1><h3>POZICIJA</h3></div>
					</div>
					<c:remove var="heroStats"/>
				</div>				
								
				<div class="box" style="width: 340px; min-height:630px;">
							
					<p class="podnaslov">SLAB PROTIV</p><hr>
					<c:forEach begin="0" end="4" items="${slabProtiv }" var="items">
						<div id="row">
							<div id="left" style="padding-left: 20px;">
								<img src="${items[1] }" width="50" height="50"> 
								<h3   style="position: relative; bottom: 20px;">${items[0] }</h3>
							</div>
							<div id="middle" style="position: relative; bottom: 20px; padding-left: 45px;"><h2>${items[2] }%</h2><p>POBEDA</p></div>
							<div id="right" style="position: relative; bottom: 20px; padding-left: 45px;"><h2>${items[3] }</h2><p>MEČEVA</p></div>
						</div>
					</c:forEach>
					<c:remove var="slabProtiv"/>
				</div>
				
				<div class="box" style="width: 360px; min-height:630px;">
			
					<p class="podnaslov">NAJBOLJI IGRAČI</p><hr>
					<c:forEach begin="0" end="4" items="${najIgraci }" var="items">
						<div id="row">
							<div id="left" style="padding-left: 20px;">
								<img src="https://ddragon.leagueoflegends.com/cdn/9.16.1/img/profileicon/4072.png" width="50" height="50"> 
								<h3   style="position: relative; bottom: 20px;">${items[0] }</h3>
							</div>
							<div id="middle" style="position: relative; bottom: 20px; padding-left: 45px;"><h2>${items[1] }%</h2><p>POBEDA</p></div>
							<div id="right" style="position: relative; bottom: 20px; padding-left: 45px;"><h2>${items[2] }</h2><p>MEČEVA</p></div>
						</div>
					</c:forEach>
					<c:remove var="najIgraci"/>
				</div>
				
			</div>
			
			<table style="text-align: center; min-height: 100px; background-color: #ffffff; opacity: 0.8; border-radius: 2px; box-shadow: 0 0 2px 2px black; margin:auto; position: relative; bottom: 200px;">
				<tr>
					<td><img src="https://opgg-static.akamaized.net/images/lol/champion/Aatrox.png?image=w_140&v=1" width="50" height="50"></td>
					<td><img src="https://opgg-static.akamaized.net/images/lol/champion/Chogath.png?image=w_140&v=1" width="50" height="50"></td>
					<td><img src="https://opgg-static.akamaized.net/images/lol/champion/Darius.png?image=w_140&v=1" width="50" height="50"></td>
					<td><img src="https://opgg-static.akamaized.net/images/lol/champion/Fiora.png?image=w_140&v=1" width="50" height="50"></td>
					<td><img src="https://opgg-static.akamaized.net/images/lol/champion/Gangplank.png?image=w_140&v=1" width="50" height="50"></td>
					<td><img src="https://opgg-static.akamaized.net/images/lol/champion/Garen.png?image=w_140&v=1" width="50" height="50"></td>
					<td><img src="https://opgg-static.akamaized.net/images/lol/champion/Irelia.png?image=w_140&v=1" width="50" height="50"></td>
					<td><img src="https://opgg-static.akamaized.net/images/lol/champion/Jayce.png?image=w_140&v=1" width="50" height="50"></td>
					<td><img src="https://opgg-static.akamaized.net/images/lol/champion/Kassadin.png?image=w_140&v=1" width="50" height="50"></td>
					<td><img src="https://opgg-static.akamaized.net/images/lol/champion/Leblanc.png?image=w_140&v=1" width="50" height="50"></td>
					<td><img src="https://opgg-static.akamaized.net/images/lol/champion/LeeSin.png?image=w_140&v=1" width="50" height="50"></td>
					<td><img src="https://opgg-static.akamaized.net/images/lol/champion/MasterYi.png?image=w_140&v=1" width="50" height="50"></td>
				</tr>
				<tr>
					<td><a href="/LoL/heroes/heroStats?name=Aatrox">Aatrox(Top)</a></td>
					<td><a href="/LoL/heroes/heroStats?name=Cho'Gath">Cho'Gath(Top)</a></td>
					<td><a href="/LoL/heroes/heroStats?name=Darius">Darius(Top)</a></td>
					<td><a href="/LoL/heroes/heroStats?name=Fiora">Fiora(Top)</a></td>
					<td><a href="/LoL/heroes/heroStats?name=Gankplank">Gankplank(Top)</a></td>
					<td><a href="/LoL/heroes/heroStats?name=Garen">Garen(Middle)</a></td>
					<td><a href="/LoL/heroes/heroStats?name=Irelia">Irelia(Middle)</a></td>
					<td><a href="/LoL/heroes/heroStats?name=Jayce">Jayce(Middle)</a></td>
					<td><a href="/LoL/heroes/heroStats?name=Kassadin">Kassadin(Middle)</a></td>
					<td><a href="/LoL/heroes/heroStats?name=LeBlanc">LeBlanc(Middle)</a></td>
					<td><a href="/LoL/heroes/heroStats?name=Lee Sin">Lee Sin(Jungle)</a></td>
					<td><a href="/LoL/heroes/heroStats?name=Master Yi">Master Yi(Jungle)</a></td>
				</tr>

				<tr>
					<td><img src="https://opgg-static.akamaized.net/images/lol/champion/Rengar.png?image=w_140&v=1" width="50" height="50"></td>
					<td><img src="https://opgg-static.akamaized.net/images/lol/champion/Shaco.png?image=w_140&v=1" width="50" height="50"></td>
					<td><img src="https://opgg-static.akamaized.net/images/lol/champion/Shen.png?image=w_140&v=1" width="50" height="50"></td>
					<td><img src="https://opgg-static.akamaized.net/images/lol/champion/Taric.png?image=w_140&v=1" width="50" height="50"></td>
					<td><img src="https://opgg-static.akamaized.net/images/lol/champion/Veigar.png?image=w_140&v=1" width="50" height="50"></td>
					<td><img src="https://opgg-static.akamaized.net/images/lol/champion/Xerath.png?image=w_140&v=1" width="50" height="50"></td>
					<td><img src="https://opgg-static.akamaized.net/images/lol/champion/Zyra.png?image=w_140&v=1" width="50" height="50"></td>
					<td><img src="https://opgg-static.akamaized.net/images/lol/champion/Xayah.png?image=w_140&v=1" width="50" height="50"></td>
					<td><img src="https://opgg-static.akamaized.net/images/lol/champion/Varus.png?image=w_140&v=1" width="50" height="50"></td>
					<td><img src="https://opgg-static.akamaized.net/images/lol/champion/Sivir.png?image=w_140&v=1" width="50" height="50"></td>
					<td><img src="https://opgg-static.akamaized.net/images/lol/champion/Lucian.png?image=w_140&v=1" width="50" height="50"></td>
					<td><img src="https://opgg-static.akamaized.net/images/lol/champion/Kalista.png?image=w_140&v=1" width="50" height="50"></td>
				</tr>
				<tr>
					<td><a href="/LoL/heroes/heroStats?name=Rengar">Rengar(Jungle)</a></td>
					<td><a href="/LoL/heroes/heroStats?name=Shaco">Shaco(Jungle)</a></td>
					<td><a href="/LoL/heroes/heroStats?name=Shen">Shen(Support)</a></td>
					<td><a href="/LoL/heroes/heroStats?name=Taric">Taric(Support)</a></td>
					<td><a href="/LoL/heroes/heroStats?name=Veigar">Veigar(Support)</a></td>
					<td><a href="/LoL/heroes/heroStats?name=Xerath">Xerath(Support)</a></td>
					<td><a href="/LoL/heroes/heroStats?name=Zyra">Zyra(Support)</a></td>
					<td><a href="/LoL/heroes/heroStats?name=Xayah">Xayah(Bottom)</a></td>
					<td><a href="/LoL/heroes/heroStats?name=Varus">Varus(Bottom)</a></td>
					<td><a href="/LoL/heroes/heroStats?name=Sivir">Sivir(Bottom)</a></td>
					<td><a href="/LoL/heroes/heroStats?name=Lucian">Lucian(Bottom)</a></td>
					<td><a href="/LoL/heroes/heroStats?name=Kalista">Kalista(Bottom)</a></td>
				</tr>
			</table>
			
		</div>

		<div id="footer">
			<p style="margin-left: 30px; float: left; font-weight: bold;">
				<br>
				<br>LEAGUE OF LEGENDS &copy; 2019
			</p>
			<p style="margin-right: 30px; float: right; font-style: italic;">
				<br>" Podrška svakodnevnom igranju <br>Svi podaci na
				jednom mestu<br>Za brže napredovanje "
			</p>
		</div>

	</div>
</body>
</html>