<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>POCETNA</title>
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

		<div id="body" style="height: 600px;">
		
		<div style="width: 900px; display: block; position: relative; margin: auto;">
			<div id="left">
				<div style="background: white; padding: 10px; margin:10px; width: 200px; border:2px solid black; border-radius: 3px;">
					<fieldset>
					<legend style="color:black;">DODAJ IGRACA:</legend>
						<form action="/LoL/crud/dodajIgraca" method="post">
							<input type="text" value="Username" name="uname">
							<input type="submit" value="Potvrdi">
						</form>
					</fieldset>
				</div>
				<div style="background: white; padding: 10px; margin:10px; width: 200px; border:2px solid black; border-radius: 3px;">
					<fieldset style="background: white; opacity: 0.8; width:100px">
					<legend style="color:black;">DODAJ MEC:</legend>
						<form action="/LoL/crud/dodajMec" method="post">
							<input type="text" value="Duration" name="dur">
							<input type="text" value="Winner" name="win">
							<input type="submit" value="Potvrdi">
						</form>
					</fieldset>
				</div>
			</div>
			
			<div id="middle">
				<a href="/LoL/crud/igrmec"><button>OSVEZI</button></a>
				<div id="left" style="background: white; padding: 10px; margin:10px; width: 200px; border:2px solid black; border-radius: 3px;">
					<fieldset>
					<legend style="color:black;">IGRACI:</legend>
					<c:forEach items="${igraci }" var="items">
						<c:set var="user" value="${items[\"username\"] }"/>
						<a href="/LoL/crud/izaberiIgraca?user=${user }" 
						<c:if test="${user == izabranIgrac }">style="text-decoration: none; background:blue; color:white; font-weight: bold;"</c:if>>
							${user }
						</a><br>
					</c:forEach>
					</fieldset>
				</div>
				<div id="right" style="background: white; padding: 10px; margin:10px; width: 200px; border:2px solid black; border-radius: 3px;">
					<fieldset>
					<legend style="color:black;">MECEVI:</legend>
					<c:if test="${!empty meceviIzabranog }">
						<c:forEach items="${meceviIzabranog }" var="items">
							<c:if test="${!empty items[1] }">
								<a href="/LoL/crud/razvezi?user=${izabranIgrac }&game=${items[0] }" style="text-decoration: none; background:red; color:white; font-weight: bold;">
								${items[0] }</a><br>
							</c:if>
							<c:if test="${empty items[1] }">
								<a href="/LoL/crud/povezi?user=${izabranIgrac }&game=${items[0] }" style="text-decoration: none; font-weight: bold;">
								${items[0] }</a><br>
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${empty meceviIzabranog }">
						<c:forEach items="${mecevi }" var="item">
							<a style="text-decoration: none; font-weight: bold;">
							${item }</a><br>
						</c:forEach>
					</c:if>
					</fieldset>
				</div>
			</div>
			
			<c:if test="${!izbrisan && (!empty podaciIzabranog)}">
			<div id="right" style="background: white; padding: 10px; margin:10px; width: 200px; border:2px solid black; border-radius: 3px;">
				<c:forEach items="${podaciIzabranog }" var="items">
					<fieldset style="background: white; opacity: 0.8; width:100px">
					<legend style="color:black;">IZMENI IGRACA:</legend>
						<form action="/LoL/crud/izmeniIgraca" method="post">
							<c:set var="user" value="${items[\"username\"] }"/>
							<c:set var="level" value="${items[\"level\"] }"/>
							<c:set var="points" value="${items[\"points\"] }"/>
							<p> ${user } </p>
							<input type="hidden" value="${user}" name="user">
							Level:
							<input type="text" value="${level}" name="level">
							Points:
							<input type="text" value="${points}" name="points">
							<input type="submit" value="Potvrdi">
							<input type="submit" name="izbrisi" value="Obrisi Igraca" style="background: red; color: black;">
						</form>
					</fieldset>
				</c:forEach>
			</div>
			</c:if>
			<c:remove var="izabranIgrac"/>
			<c:remove var="podaciIzabranog"/>
			<c:remove var="meceviIzabranog"/>
			<c:remove var="izbrisan"/>
		</div>	
		</div>



		<div id="footer">
			<p style="margin-left: 30px; float: left; font-weight: bold;">
				<br> <br>LEAGUE OF LEGENDS &copy; 2019
			</p>
			<p style="margin-right: 30px; float: right; font-style: italic;">
				<br>" Podrška svakodnevnom igranju <br>Svi podaci na
				jednom mestu<br>Za brže napredovanje "
			</p>
		</div>

	</div>
</body>
</html>