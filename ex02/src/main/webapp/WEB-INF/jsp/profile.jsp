<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="edu.school21.cinema.models.Session" %>
<%@ page import="edu.school21.cinema.models.User" %>
<%@ page import="edu.school21.cinema.models.Image" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Session> sessionList = (List<Session>) request.getSession().getAttribute("SessionAttributes"); %>
<% ArrayList<Image> imageList = (ArrayList<Image>) request.getSession().getAttribute("Images"); %>
<% User u = (User) request.getSession().getAttribute("UserAttributes"); %>

<style>
    * {
        background-color: black;
        color: white;
    }
    .header {
        min-height: 40vh;
        background: linear-gradient(to bottom, #ff0000, #000000);
        color: white;
        clip-path: ellipse(100vw 60vh at 50% 50%);
        display: flex;
        align-items: center;
        justify-content: center;
    }
    .details {
        text-align: center;
        background-color: transparent;
    }
    .heading {
        background-color: transparent;
    }
    img {
        min-width: 200px;
        min-height: 200px;
        max-width: 300px;
        max-height: 300px;
        object-fit: cover;
        border-radius: 50%;
    }
    .generalTable {
        display: flex;
    }
    table{
        width: 100%;
        table-layout: fixed;
        padding: 0;
        border-spacing: 0;
        border: 0;
    }
    .tbl-header{
        background-color: rgba(255,255,255,0.3);
    }
    .tbl-content{
        height:300px;
        overflow-x:auto;
        margin-top: 0;
        border: 1px solid rgba(255,255,255,0.3);
    }
    th{
        padding: 20px 15px;
        text-align: center;
        font-weight: 500;
        font-size: 18px;
        color: #fff;
        text-transform: uppercase;
        background: #000000;
    }
    td{
        padding: 15px;
        text-align: center;
        vertical-align:middle;
        font-weight: 300;
        font-size: 15px;
        color: #ffffff;
        border-bottom: solid 1px rgba(255,255,255,0.1);
        word-wrap: break-word;
    }
    ::-webkit-scrollbar {
        width: 6px;
    }
    ::-webkit-scrollbar-track {
        -webkit-box-shadow: inset 0 0 6px rgba(77, 77, 77, 0.3);
    }
    ::-webkit-scrollbar-thumb {
        -webkit-box-shadow: inset 0 0 6px rgba(255, 255, 255, 0.3);
    }
</style>

<html>
<head>
    <title>Profile</title>
</head>
<body>

    <header class="header">
        <div class="details">
            <c:set var="size" value="<%=imageList.size()%>"/>
            <c:choose>
                <c:when test="${size > 0}">
                    <img src="images/<%=imageList.get(imageList.size() - 1).getUuid()%>" id="avatar-image" class="avatar_img" alt="Avatar"/>
                </c:when>
                <c:otherwise>
                    <img src="https://cdn-images-1.medium.com/max/1200/1*OJAuAh5qU6DoJTvt7s7lpw.png" id="avatar-image" class="avatar_img" alt="Default img" />
                </c:otherwise>
            </c:choose>
            <h1 class="heading">
                <%= u.getFirstName() + " " + u.getLastName() %>
            </h1>
            <h1 class="heading">
                <%= u.getEmail() %>
            </h1>
        </div>
    </header>

    <div class="generalTable">

        <div>
            <h1 align="center">Uploads</h1>
            <div class="tbl-header" style="float: left;margin-right:10px">
                <table>
                    <thead>
                    <tr>
                        <th>File name</th>
                        <th>Size</th>
                        <th>MIME</th>
                    </tr>
                    </thead>
                </table>
            </div>

            <div class="tbl-content" style="float: left;margin-right:10px">
                <table>
                    <tbody>
                        <c:forEach var="i" items="<%= imageList %>">
                            <tr>
                                <td> <a href="images/${i.uuid.toString()}" target="_blank">${i.name}</a></td>
                                <td>${i.sizeInStr}</td>
                                <td>${i.mime}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div>
            <h1 align="center">History</h1>
            <div class="tbl-header" style="float: right;">
                <table style="float: left">
                    <thead>
                    <tr>
                        <th>Date</th>
                        <th>Time</th>
                        <th>IP</th>
                    </tr>
                    </thead>
                </table>
            </div>

            <div class="tbl-content" style="float: right;">
                <table>
                    <tbody>
                        <c:forEach var="s" items="<%= sessionList %>">
                            <tr>
                                <td>${s.formatedDate}</td>
                                <td>${s.formatedTime}</td>
                                <td>${s.ip}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <br>
    <h3>Avatar upload:</h3>
    <form action = "images/" method = "post" enctype = "multipart/form-data">
        <input type="file" name="file" accept="image/*"/>
        <br>
        <br>
        <input type = "submit" value = "Upload File" />
    </form>
</body>
</html>
