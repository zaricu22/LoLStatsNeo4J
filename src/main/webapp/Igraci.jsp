<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IGRAČI</title>
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

		<div id="body" style="height: 700px;">
		
			<div style="width: 1500px; display: block; position: relative; bottom: 200px; margin: auto;">
		
				<div class="box" style="width: 360px; min-height:630px;">
					<p class="podnaslov">NAJJAČI HEROJI</p><hr>
					<c:forEach begin="0" end="4" items="${najHeroji }" var="items">
						<div id="row">
							<div id="left" style="padding-left: 20px;">
								<img src="${items[1] }" width="50" height="50"> 
								<h3   style="position: relative; bottom: 20px;">${items[0] }</h3>
							</div>
							<div id="middle" style="position: relative; bottom: 20px; padding-left: 25px;"><h2>${items[2] }%</h2><p>POBEDA</p></div>
							<div id="right" style="position: relative; bottom: 20px; padding-left: 25px;"><h2>${items[3] }</h2><p>MEČEVA</p></div>
							<div id="right" style="position: relative; bottom: 20px; padding-left: 25px;"><h2>${items[4] }</h2><p>KDA</p></div>
						</div>
					</c:forEach>
					<c:remove var="najHeroji"/>
				</div>
			
				<div style="width: 670px; display: inline-block;">
				
					<div class="dropdown">
					    <button class="dropbtn">Izbor igrača</button>
					    <div class="dropdown-content">
					    	<c:forEach items="${players }" var="i">
					        	<a href="/LoL/players/playerStats?username=${i.username }">${i.username }</a>
					        </c:forEach>
					    </div>
					</div>
					
					<div class="box" style="width: 640px; text-align: center; top: 400px;">
						
						<div id="row">
							<div style="display: table-cell; border-right: 2px solid gray; padding: 0px 5px;">
								<img src="https://ddragon.leagueoflegends.com/cdn/9.16.1/img/profileicon/22.png" width="70" height="70" 
									 style="margin: 5px 5px; position: relative; top: 40px;">
								<h4 style="padding-left: 5px; position: relative; top: 20px;">${userStats[10] }</h4>
								<h4 style="padding-left: 5px; position: relative;">PTS:${userStats[11] }</h4>
								<h4 style="padding-left: 5px; position: relative; bottom: 20px;">LVL:${userStats[12] }</h4>
							</div>
							<div style="display: table-cell; padding-left: 10px; text-align: center; position: relative; bottom: 25px;">
								<h4>Death</h4><p>${userStats[0] }</p><h4>Mečeva</h4><p>${userStats[6] }</p>
							</div>
							<div style="display: table-cell; padding-left: 10px; text-align: center; position: relative; bottom: 25px;">
								<h4>Assist</h4><p>${userStats[1] }</p><h4>Pobeda</h4><p>${userStats[7] }</p>
							</div>
							<div style="display: table-cell; padding-left: 10px; text-align: center; position: relative; bottom: 25px;">
								<h4>Kill</h4><p>${userStats[2] }</p><h4>Poraza</h4><p>${userStats[8] }</p>
							</div>
							<div style="display: table-cell; padding-left: 10px; text-align: center; position: relative; bottom: 25px;">
								<h4>DoubleKill</h4><p>${userStats[3] }</p>
							</div>
							<div style="display: table-cell; padding: 0px 10px; text-align: center; position: relative; bottom: 25px;">
								<h4>TripleKill</h4><p>${userStats[4] }</p>
							</div>
							<div id="right" style="border-left: 2px solid gray; padding: 0px 5px;"><h1>${userStats[9] }%</h1><h3>POBEDA</h3></div>
							<div id="right" style="border-left: 2px solid gray; padding: 0px 15px;"><h1>${userStats[5] }</h1><h3>KDA</h3></div>
						</div>
						<c:remove var="userStats"/>
					</div>
			
				</div>
			
				<div class="box" style="width: 370px; min-height:630px;">
					<p class="podnaslov">NAJBOLJI SAIGRAČI</p><hr>
					<c:forEach begin="0" end="4" items="${listaSaigraca }" var="items">
						<div id="row">
							<div id="left" style="padding-left: 20px;">
								<img src="https://ddragon.leagueoflegends.com/cdn/9.16.1/img/profileicon/1113.png" width="50" height="50"> 
								<h3   style="position: relative; bottom: 20px;">${items[0] }</h3>
							</div>
							<div id="middle" style="position: relative; bottom: 20px; padding-left: 35px;"><h2>${items[1] }%</h2><p>POBEDA</p></div>
							<div id="right" style="position: relative; bottom: 20px; padding-left: 35px;"><h2>${items[2] }</h2><p>MEČEVA</p></div>
						</div>
					</c:forEach>
					<c:remove var="listaSaigraca"/>
				</div>
			
			</div>
			
		</div>

		<div id="footer">
			<p style="margin-left: 30px; float: left; font-weight: bold;"><br><br>LEAGUE OF LEGENDS &copy; 2019</p>
			<p style="margin-right: 30px; float: right; font-style: italic;"><br>" Podrška svakodnevnom igranju <br>Svi podaci na jednom mestu<br>Za brže napredovanje "</p>
		</div>
		
	</div>
</body>
</html>