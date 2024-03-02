<%@include file="common/header.jspf"%>
<%@include file="common/navigation.jspf"%>
<div class="container">
    <div class="mt-3">
        <h1>Welcome</h1>
        <hr>
        <h2>Your Todos</h2>
        <%-- <div>Your Todos are ${todos}</div> --%>
        <table class="table">
            <thead>
            <tr>
                <th>Description</th>
                <th>Target Date</th>
                <th>Is Done?</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${todos}" var="todo">
                <tr>
                    <td>${todo.description}</td>
                    <td>${todo.targetDate}</td>
                    <td>${todo.done}</td>
                    <td><a href="delete-todo?id=${todo.id}" class="btn btn-warning">Delete</a></td>
                    <td><a href="update-todo?id=${todo.id}" class="btn btn-success">Update</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <a href="add-todo" class="btn btn-success">Add Todo</a>
    </div>
</div>
<%@include file="common/footer.jspf"%>
