<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="edu.school21.cinema.models.Session" %>
<%@ page import="edu.school21.cinema.models.User" %>
<%@ page import="edu.school21.cinema.models.Image" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Session> sessionList = (List<Session>) request.getSession().getAttribute("SessionAttributes"); %>
<% List<Image> imageList = (List<Image>) request.getSession().getAttribute("Images"); %>
<% User u = (User) request.getSession().getAttribute("UserAttributes"); %>

<style>
    .info-table table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
    }
    .info-table th, td {
        padding: 5px;
        text-align: left;
    }
    .scroll-table-body {
        height: 300px;
        overflow-x: auto;
        margin-top: 0px;
        margin-bottom: 20px;
        border-bottom: 1px solid #eee;
    }
    .scroll-table table {
        width:50%;
        table-layout: fixed;
        border: none;
    }
    .scroll-table thead th {
        font-weight: bold;
        text-align: left;
        border: none;
        padding: 10px 15px;
        background: #d8d8d8;
        font-size: 14px;
        border-left: 1px solid #ddd;
        border-right: 1px solid #ddd;
    }
    .scroll-table tbody td {
        text-align: left;
        border-left: 1px solid #ddd;
        border-right: 1px solid #ddd;
        padding: 10px 15px;
        font-size: 14px;
        vertical-align: top;
    }
    .scroll-table tbody tr:nth-child(even){
        background: #f3f3f3;
    }

    ::-webkit-scrollbar {
        width: 6px;
    }
    ::-webkit-scrollbar-track {
        box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
    }
    ::-webkit-scrollbar-thumb {
        box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
    }
</style>

<html>
<head>
    <title>Profile</title>
</head>
<body>

    <span class="info-table">
        <table>
            <tr>
                <th>First name:</th>
                <td><%=u.getFirstName()%></td>
            </tr>
            <tr>
                <th>Last name:</th>
                <td><%=u.getLastName()%></td>
            </tr>
            <tr>
                <th>Email:</th>
                <td><%=u.getEmail()%></td>
            </tr>
            <tr>
                <th>Telephone:</th>
                <td><%=u.getPhoneNumber()%></td>
            </tr>
        </table>
    </span>

    <span class="scroll-table">
        <table style="display: inline-table">
            <thead>
                <tr>
                    <th>Date</th>
                    <th>Time</th>
                    <th>IP</th>
                </tr>
            </thead>
        </table>
        <span class="scroll-table-body">
            <table style="display: inline-table">
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
        </span>
    </span>

    <span class="scroll-table">
        <table style="display: inline-table">
            <thead>
            <tr>
                <th>File name</th>
                <th>Size</th>
                <th>MIME</th>
            </tr>
            </thead>
        </table>
        <span class="scroll-table-body">
            <table style="display: inline-table">
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
        </span>
    </span>

    <br>
    <h3>File Upload:</h3>
    Select a file to upload: <br><br>
    <form action = "images/" method = "post" enctype = "multipart/form-data">
        <input type="file" name="file" size="10485760" accept="image/*"/>
        <br>
        <br>
        <input type = "submit" value = "Upload File" />
    </form>
</body>
</html>
