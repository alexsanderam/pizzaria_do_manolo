<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>

span.erro {
	color: red;
}

</style>
<html>
	<head>
		<title>Login</title>
	</head>
	<body>
		<form method="post"	action="Login">
			<table>
				<tr>
					<td>Telefone</td>
					<td><input type="text" name="email" /></td>
				</tr>
				<tr>
					<td>Senha</td>
					<td><input type="password" name="senha" /></td>
				</tr>
				<c:if test="${falhaNaAutenticacao}">
				<span class="erro">
					<c:out value="${mensagem}"/>
				</span>
				</c:if>
				<tr>
					<td><a href="index.jsp" >Home</a></td>
					<td><input type="submit" value="Login" /></td>
				</tr>
			</table>
			<br><br>
			
		</form>
	</body>
</html>