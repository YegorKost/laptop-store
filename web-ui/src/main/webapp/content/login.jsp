<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login page</title>
    <style>
        table {
            width: 90%;
            background-color: #4CAF50;
            color: white;
            padding: 5px 5px;
            border-radius: 4px;
            cursor: pointer;
        }
        input {
            width: 20%;
            background-color: #feffeb;
            color: #a0a0a0;
            padding: 5px 5px;
            border-radius: 4px;
            cursor: pointer;
        }
        button {
            width: 20%;
            background-color: rgba(104, 120, 157, 0.71);
            color: #ffffff;
            padding: 5px 5px;
            border-radius: 4px;
            cursor: pointer;
        }
        div {
            text-align: center;
        }
    </style>
</head>
<body>
<br>
    <c:url value="/spring_security" var="loginUrl"/>
    <form action="${loginUrl}" method="post">
        <div>
            <table align="center">
                <tr><td><div><h2>Please sign in to the store</h2></div></td></tr>
                <tr><td><div><input type="text" name="login" placeholder="Login" autofocus value=""></div></td></tr>
                <tr><td><div><input type="password" name="password" placeholder="Password" value=""></div></td></tr>
                <tr><td><div><button type="submit">Log in</button></div></td></tr>
                <tr><td><div><button type="submit">Anonymous</button></div></td></tr>

            </table>
        </div>
    </form>
</body>
</html>
