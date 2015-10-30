<%@ page isErrorPage="true" language="java"
    pageEncoding="UTF-8"
    import="org.springframework.web.context.support.*, 
	                            org.springframework.web.context.*,
	                            java.util.*,
	                            java.text.*" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<body>
		Welcome!
		
		${actionBean.message}
		
		<stripes:label name="labelText" />
	</body>
</html>
