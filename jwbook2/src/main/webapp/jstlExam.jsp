<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html><html><head>
<meta charset="UTF-8">
<title>JSTL 종합 예제</title>
</head>
<body>
 <h2>JSTL 종합 예제</h2>
  <hr>
  <!--  set, out -->
  <h3>set, out</h3>
  <c:set var="product1" value="<b>애플 아이폰</b>"/>
  <c:set var="product2" value="삼성 갤럭시 노트"/>
  <c:set var="intArray" value="${[1,2,3,4,5] }"/>  
  <p>
  product1(jstl):
  <c:out value = "${product1}" default="Not registered" escapeXml="true"/>
  </p>
  <p> product1(el) : ${product1 }
  </p>  
  <p> intArray[2] : ${intArray[2]}
  </p>
  <hr>
  <c:forEach var="num" varStatus="i" items="${intArray }">
    <li>${i.index}-${i.count}-${i.first}-${i.last}-${i.current}-${num}</li>
  </c:forEach>
  <hr>
  <c:set var="checkout" value="true"/>
  <c:if test="${checkout}">
  	<p>주문제품: ${product2 }
  </c:if>
  <c:if test="${!checkout}">
  	<p> 주문제품이 아님
  </c:if>
  
  <c:if test="${!empty product2 }">
  	<p>
  	 <b>${product2} 이미 추가됨</b>
  	</p>
  </c:if>
  <hr>
  <!-- choose, when, otherwise -->
  <h3>choose, when, otherwise</h3>
  <c:choose>
    <c:when test="${checkout}">
       <p>주문제품: ${product2 }</p>
    </c:when>
    <c:otherwise>
       <p> 주문제품이 아님
    </c:otherwise>
  </c:choose>
  <hr>
  <c:catch var="errmsg">
    예외발생 전 <br>
    <%=1 / 0 %> <br>
    예외발생 후
  </c:catch>
  <c:out value="${errmsg}"/>

</body>
</html>