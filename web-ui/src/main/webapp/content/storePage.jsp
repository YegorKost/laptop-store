<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<html>
<head>
    <title>Admin store page</title>
    <style>
        div {
            text-align: center;
            background-color: #4CAF50;
        }
        tr {height: 20px; width: 70%;}
        table {width: 90%; border-spacing: 10px; border: #4CAF50}
        input[type=text], select {
            width: 100%;
            padding: 5px 5px;
            display: inline-block;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type=number], select {
            width: 100%;
            padding: 5px 5px;
            display: inline-block;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type=submit] {
            width: 100%;
            background-color: #4CAF50;
            color: white;
            padding: 5px 5px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[name=addition] {width: 30%;}
        input[name=deleting] {width: 30%;}
        input[name=buy] {width: 30%;}
        input[name=items] {width: 60%;}
        input[name=uploadImage] {width: 30%;}
    </style>
</head>
<body>

<div>
    <a href="login"><h3 align="left">To login page</h3></a>
    <h1>
        Store page
        <br/>
        <sec:authentication property="principal.username"/>
    </h1>
</div>

<sec:authorize access="hasAuthority('admin')">
    <form action="laptopAction" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td>Image:</td>
                <td>Make:</td>
                <td>Model:</td>
                <td>Screen:</td>
                <td>Processor:</td>
                <td>Memory:</td>
                <td>Amount:</td>
                <td>Price:</td>
            </tr>
            <tr>
                <td><input type="file" name="file"/></td>
                <td><input type="text" name="make" placeholder="Make" required/></td>
                <td><input type="text" name="model" placeholder="Model" required/></td>
                <td><input type="number" name="screen" placeholder="Screen" required/></td>
                <td><input type="text" name="processor" placeholder="Processor" required/></td>
                <td><input type="number" name="memory" placeholder="Memory(Gb)" required/></td>
                <td><input type="number" name="amount" placeholder="Amount" required/></td>
                <td><input type="number" name="price" placeholder="Price" required/></td>
                <td><input type="submit" name="add" value="Add" width="100%"/></td>
            </tr>
        </table>
    </form>
</sec:authorize>

<table>
    <c:forEach items="${laptops}" var="laptop">
        <tr>
            <td rowspan="4" height="250" width="320" bgcolor="#d3d3d3"><img width="100%" src="<c:url value="${laptop.image}"/>"/></td>
            <td colspan="7" height="50"><h3>Laptop: ${laptop.make} ${laptop.model}</h3></td>
        </tr>
        <tr>
            <td>Make:</td>
            <td width="20%">Model:</td>
            <td>Screen:</td>
            <td>Processor:</td>
            <td>Memory(Gb):</td>
            <td>Amount:</td>
            <td>Price:</td>
        </tr>
        <tr>
            <td>${laptop.make}</td>
            <td width="20%">${laptop.model}</td>
            <td>${laptop.screen}</td>
            <td>${laptop.processor}</td>
            <td>${laptop.memory}</td>
            <td>${laptop.amount}</td>
            <td>${laptop.price}</td>
        </tr>
        <tr>
            <sec:authorize access="hasAuthority('admin')">
                <td colspan="5" >
                    <form action="laptopAction" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="model" value="${laptop.model}"/>
                        <input type="file" name="file"/>
                        <input type="submit" name="uploadImage" value="Upload image"/>
                    </form>
                </td>
                <td>
                    <form action="addItem" method="post">
                        <input type="hidden" name="model" value="${laptop.model}"/>
                        <input type="number" name="items" min="1" value="1"/>
                        <input type="submit" name="addition" value="Add"/>
                    </form>
                </td>
                <td>
                    <form action="delItem" method="post">
                        <input type="hidden" name="model" value="${laptop.model}"/>
                        <input type="number" name="items" min="1" value="1"/>
                        <input type="submit" name="deleting" value="Del"/>
                    </form>
                </td>
                <td>
                    <form action="removeLaptop" method="post">
                        <input type="hidden" name="model" value="${laptop.model}"/>
                        <input type="submit" name="remove" value="Remove laptop"/>
                    </form>
                </td>
            </sec:authorize>
            <sec:authorize access="hasAuthority('user') or hasAuthority('anonymous')">
                <td>
                    <form action="buyLaptop" method="post">
                        <input type="hidden" name="model" value="${laptop.model}"/>
                        <input type="number" name="items" min="1" value="1"/>
                        <input type="submit" name="buy" value="Buy"/>
                    </form>
                </td>
            </sec:authorize>
        </tr>
    </c:forEach>
</table>

</body>
</html>
