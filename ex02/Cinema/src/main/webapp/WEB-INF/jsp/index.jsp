<%@ page import="edu.school21.cinema.models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% User u = (User) request.getSession().getAttribute("UserAttributes"); %>
<style>
    html {
        height:100%;
        color: white;
    }
    * {
        background-image: linear-gradient(to bottom, #ff0000, #000000);
        background-repeat: no-repeat;
        margin: 0;
        padding: 0;
    }
    body {
        background: transparent;
    }
    .box {
        border-radius: 25px;
        border: 10px solid #000;
        background: black;
        width: 500px;
        margin: 65px auto 10px;
    }
    .content h1 {
        background: black;
        padding: 35px;
        text-align: center;
        font-weight: 300;
    }
    .action {
        background: black;
        display: -webkit-box;
        display: flex;
        -webkit-box-orient: horizontal;
        -webkit-box-direction: normal;
    }
    .action a {
        width: 100%;
        background: transparent;
    }
    .action button {
        border-radius: 25px;
        width: 100%;
        padding: 18px;
        margin: 5px;
        cursor: pointer;
        text-transform: uppercase;
        background: white;
        color: black;
        -webkit-transition: all .3s;
        transition: all .3s;
    }
</style>

<html>
<head>
    <title>Main page</title>
</head>
<body>
    <div class="box">
        <div class="content">
            <c:set var="user" value="<%= u == null %>"/>
            <c:choose>
                <c:when test="${user == true}">
                    <h1>You're not authorized</h1>
                    <div class="action">
                        <a href="signIn">
                            <button>Sign In</button>
                        </a>
                    </div>
                    <div class="action">
                        <a href="signUp">
                            <button>Sign Up</button>
                        </a>
                    </div>
                </c:when>
                <c:otherwise>
                    <h1>You're already authorized</h1>
                    <div class="action">
                        <a href="profile">
                            <button>Profile</button>
                        </a>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</body>
</html>
