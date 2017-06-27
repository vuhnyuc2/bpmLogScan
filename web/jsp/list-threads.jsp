<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <link rel="stylesheet" href="../css/bootstrap.min.css"/>
    <script src="../js/bootstrap.min.js"></script>
</head>

<body>
<div class="container">
    <h2>Threads</h2>


    <!--Thread List-->
    <form action="/threads" method="post" id="threadForm" role="form" >

        <c:choose>
            <c:when test="${not empty threadList}">
                <table  class="table table-striped">
                    <thead>
                    <tr>
                        <td>Threads</td>

                    </tr>
                    </thead>
                    <c:forEach var="thread" items="${threadList}">

                        <tr >
                            <td>${thread.getMessageType()}</td>
                        </tr>

                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <br>
                <div class="alert alert-info">
                    No threads
                </div>
            </c:otherwise>
        </c:choose>
    </form>

</div>
</body>
</html>