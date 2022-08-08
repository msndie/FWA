<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="edu.school21.cinema.models.Session" %>
<%@ page import="edu.school21.cinema.models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Session> sessionList = (List<Session>) request.getSession().getAttribute("SessionAttributes"); %>

<style>
    .scroll-table-body {
        height: 300px;
        overflow-x: auto;
        margin-top: 0px;
        margin-bottom: 20px;
        border-bottom: 1px solid #eee;
    }
    .scroll-table table {
        width:100%;
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

    /* Стили для скролла */
    ::-webkit-scrollbar {
        width: 6px;
    }
    ::-webkit-scrollbar-track {
        box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
    }
    ::-webkit-scrollbar-thumb {
        box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
    }
    /*body {*/
    /*    background-color: black;*/
    /*}*/

    /*tbody tr:nth-child(odd) {*/
    /*    background-color: #ff33cc;*/
    /*}*/

    /*tbody tr:nth-child(even) {*/
    /*    background-color: #e495e4;*/
    /*}*/

    /*tbody tr {*/
    /*    display:block;*/
    /*    overflow:auto;*/
    /*    height:200px;*/
    /*    width:100%;*/
    /*}*/

    /*thead tr {*/
    /*    display: block;*/
    /*}*/

    /*table {*/
    /*    background-color: #ff33cc;*/
    /*    text-align: center;*/
    /*    margin: auto;*/
    /*    width: 50vw;*/
    /*}*/

    /*#zhaba {*/
    /*    color: #ff33cc;*/
    /*    text-align: center;*/
    /*}*/
</style>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <% User u = (User) request.getSession().getAttribute("UserAttributes"); %>
    <H1>
        <%=String.format("Welcome back, %s %s\n%s\n", u.getFirstName(), u.getLastName(), u.getEmail())%>
    </H1>
    <div class="scroll-table">
        <table>
            <thead>
                <tr>
                    <th>Date</th>
                    <th>Time</th>
                    <th>IP</th>
                </tr>
            </thead>
        </table>
        <div class="scroll-table-body">
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
