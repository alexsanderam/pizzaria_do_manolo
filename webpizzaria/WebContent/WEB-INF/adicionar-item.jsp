<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<link rel="stylesheet" type="text/css" href="pizza.css" />
<link rel="stylesheet" type="text/css" href="adicionar-item.css" />

<head>

<link rel="stylesheet" type="text/css" href="pizza.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Adicionar Item ao Pedido</title>
</head>
<body>


	<div class="conteudo-div">

		<div id="cardapio">
			<h2>Adicionar Item ao Pedido</h2>
			<c:if test="${erro}">
				<div class="erro"><c:out value="${mensagem}" /></div>
			</c:if>
			<div id="itens-cardapio">
				<c:forEach items="${pizzas}" var="pizza">
					<div class="itens-cardapio-linha">
						<form method="post" action="Pedido">
							<div class="itens-cardapio-cell">
								<input type="submit" value="Adicionar">
								<input type="hidden" name="idPizza" value="${pizza.id}">
								<input type="hidden" name="action" value="adicionarItem">
							</div>

							<div class="itens-cardapio-cell">
								<input id="inputQuantidade" type="number" name="quantidade"
									value="1">
							</div>

							<div class="itens-cardapio-cell">
								<div class=pizza>
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

							</div>
						</form>
					</div>

				</c:forEach>
			</div>
		</div>


	</div>
</body>
</html>