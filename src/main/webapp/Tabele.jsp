<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TABELE</title>
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

		<div id="body">

			<div id="table">
				<div id="row">
					<div id="left">
						<div class="tablebox">
							<p class="podnaslov">IGRAČI PO BODOVIMA</p><hr>
							<div id="table">
								<c:forEach items="${igraciBod }" var="items" varStatus="loop">
									<c:if test="${loop.index % 2 == 0 }">
										<div id="row"><div id="levo">${items["username"] }</div><div id="desno">${items["bodovi"] }</div></div>
									</c:if>
									<c:if test="${loop.index % 2 == 1 }">
										<div id="nepar"><div id="levo">${items["username"] }</div><div id="desno">${items["bodovi"] }</div></div>
									</c:if>
								</c:forEach>
								<c:remove var="igraciBod"/>
							</div>
						</div>
					</div>
					<div id="middle">
						<div class="tablebox">
							<p class="podnaslov">IGRAČI PO ODNOSU POBEDA</p><hr>
							<div id="table">
								<c:forEach begin="0" end="9"  items="${igraciPob }" var="items" varStatus="loop">
									<c:if test="${loop.index % 2 == 0 }">
										<div id="row"><div id="levo">${items["username"] }</div><div id="desno">${items["procPob"] }%</div></div>
									</c:if>
									<c:if test="${loop.index % 2 == 1 }">
										<div id="nepar"><div id="levo">${items["username"] }</div><div id="desno">${items["procPob"] }%</div></div>
									</c:if>
								</c:forEach>
								<c:remove var="igraciPob"/>
							</div>
						</div>
					</div>
					<div id="right">
						<div class="tablebox">
							<p class="podnaslov">IGRAČI PO DKA</p><hr>
							<div id="table">
								<c:forEach items="${igraciKDA }" var="items" varStatus="loop">
									<c:if test="${loop.index % 2 == 0 }">
										<div id="row"><div id="levo">${items["username"] }</div><div id="desno">${items["dka"] }</div></div>
									</c:if>
									<c:if test="${loop.index % 2 == 1 }">
										<div id="nepar"><div id="levo">${items["username"] }</div><div id="desno">${items["dka"] }</div></div>
									</c:if>
								</c:forEach>
								<c:remove var="igraciKDA"/>
							</div>
						</div>
					</div>
				</div>
				<div id="row">
					<div id="left">
						<div class="tablebox">
							<p class="podnaslov">HEROJI PO DKA</p><hr>
							<div id="table">
								<c:forEach items="${herojiKDA }" var="items" varStatus="loop">
									<c:if test="${loop.index % 2 == 0 }">
										<div id="row"><div id="levo">${items["name"] }</div><div id="desno">${items["dka"] }</div></div>
									</c:if>
									<c:if test="${loop.index % 2 == 1 }">
										<div id="nepar"><div id="levo">${items["name"] }</div><div id="desno">${items["dka"] }</div></div>
									</c:if>
								</c:forEach>
								<c:remove var="herojiKDA"/>
							</div>
						</div>
					</div>
					<div id="middle">
						<div class="tablebox">
							<p class="podnaslov">HEROJI PO POPULARNOSTI</p><hr>
							<div id="table">
								<c:forEach items="${herojiPop }" var="items" varStatus="loop">
									<c:if test="${loop.index % 2 == 0 }">
										<div id="row"><div id="levo">${items["name"] }</div><div id="desno">${items["pop"] }%</div></div>
									</c:if>
									<c:if test="${loop.index % 2 == 1 }">
										<div id="nepar"><div id="levo">${items["name"] }</div><div id="desno">${items["pop"] }%</div></div>
									</c:if>
								</c:forEach>
								<c:remove var="herojiPop"/>
							</div>
						</div>
					</div>
					<div id="right">
						<div class="tablebox">
							<p class="podnaslov">HEROJI PO ODNOSU POBEDA</p><hr>
							<div id="table">
								<c:forEach begin="0" end="9"  items="${herojiPob }" var="items" varStatus="loop">
									<c:if test="${loop.index % 2 == 0 }">
										<div id="row"><div id="levo">${items["name"] }</div><div id="desno">${items["procPob"] }%</div></div>
									</c:if>
									<c:if test="${loop.index % 2 == 1 }">
										<div id="nepar"><div id="levo">${items["name"] }</div><div id="desno">${items["procPob"] }%</div></div>
									</c:if>
								</c:forEach>
								<c:remove var="herojiPob"/>
							</div>
						</div>
					</div>
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