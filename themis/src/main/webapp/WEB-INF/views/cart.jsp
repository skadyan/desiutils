<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<ns:html>
<ns:head>
	<ns:title>Items</ns:title>
</ns:head>
<ns:body>
	<h3="">Items:</h>
  <c:foreach var="item" items="${items}">
	<ns:div>${item.articleNo} @ ${item.price}, qty: ${item.quantity}  
			<ns:a href="/cart/removefromcart/${item.articleNo}">[-] remove</ns:a> 
			<ns:a href="/cart/addtocart/${item.articleNo}">[+] add</ns:a>
	</ns:div>
  </c:foreach>
  <ns:a href="/cart/addtocart/${add}">add "${add}"</ns:a>
</ns:body>
</ns:html>
