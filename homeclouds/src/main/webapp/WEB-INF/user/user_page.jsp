<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="header.jsp"></jsp:include>
    <body>
        <h1>${login}</h1>
        <h2>${path}</h2>
        <div class = "files">
            <p>Файлы:</p><b>
            <p>${files}</p>
        </div>
    </body>

<jsp:include page="footer.jsp"></jsp:include>