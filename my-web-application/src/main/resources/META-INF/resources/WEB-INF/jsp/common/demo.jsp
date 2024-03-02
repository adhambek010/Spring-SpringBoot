<!DOCTYPE html>
<html>
<head>
    <title>Simple JSP Example</title>
</head>
<body>
<h1>Hello, World!</h1>
<p>This is a simple JSP example.</p>
<%-- This is a JSP comment --%>

<%
    String name = "Adham";
    int age = 23;
%>

<p>Name: <%= name %></p>
<p>Age: <%= age %></p>
</body>
</html>
