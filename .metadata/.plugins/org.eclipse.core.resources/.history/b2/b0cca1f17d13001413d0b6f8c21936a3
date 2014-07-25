<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<link rel="stylesheet" type="text/css" href="pizza.css" />
<link rel="stylesheet" type="text/css" href="index.css" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home - Pizzaria do Manolo</title>
</head>
<body>
	<div class=conteudo-div>
		<div class="esquerda">
			<div id="cardapio">
				<h2>Cardápio</h2>

				<div id="itens-cardapio">
					<c:forEach items="${pizzas}" var="pizza">
						<div class="pizza">
							<div class="pizza-nome">
								<c:out value="${pizza.nomePizza}" />
							</div>
							<div class="pizza-preco">
								R$
								<c:out value="${pizza.preco}" />
							</div>
							<div class="pizza-ingredientes">
								<c:out value="${pizza.ingredientes }" />
							</div>
						</div>
					</c:forEach>
				</div>
			</div>

		</div>
		<div class="direita">
				<div id="form-login">
					<form method="post" action="Login">
						<div class="form-line">
							<div class="label form-cell">Email</div>
							<div class="field form-cell">
								<input type="text" name="email" />
							</div>
						</div>
						<div class="form-line">
							<div class="label form-cell">Senha</div>
							<div class="field form-cell">
								<input type="password" name="senha" />
							</div>
						</div>
						<div class="form-line">
							<c:if test="${falhaNaAutenticacao}">
								<div class="erro"><c:out value="${mensagem}" /></div>
							</c:if>
						</div>
						<div class="form-line">
							<div id="cadastro" class="form-cell">
								<a href="cadastro.jsp">Cadastre-se</a>
							</div>
							<div id="realizar-login" class="form-cell">
								<input type="submit" value="Login" />
							</div>
						</div>


					</form>
				</div>
			</div>
		</div>
</body>
</html>