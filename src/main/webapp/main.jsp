<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html>
<body>
<h2>Hello World!</h2>
<h1>S 1 raza?</h1>

<p>Name: ${name}</p>

<p>Request Name: <%= request.getAttribute("name") %></p>
<p>Session Name: <%= session.getAttribute("name") %></p>--%>

<p>Name: ${testBean.name}</p>
<p>Age: ${testBean.age}</p>
</body>
</html>
