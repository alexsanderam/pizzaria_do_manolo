<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="dominio.EnumFormaDePagamento"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<link rel="stylesheet" type="text/css" href="home.css" />
<link rel="stylesheet" type="text/css" href="pizza.css" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home - Pizzaria do Manolo</title>
</head>
<body>
	<div class=conteudo-div>
		<div class="esquerda">
			<div id="novo-pedido">
				<h2>Realizar Pedido</h2>
				<c:if test="${erro}">
					<div class="erro">
						<c:out value="${mensagem}" />
					</div>
				</c:if>
				<div class="item-novo-pedido">
					<div class="cabecalho-novo-pedido"></div>
					<div class="cabecalho-novo-pedido">Preço unitário</div>
					<div class="cabecalho-novo-pedido">Quantidade</div>
					<div class="cabecalho-novo-pedido">Subtotal</div>
				</div>
				<c:forEach items="${novoPedido.itensDoPedido}" var="itemPedido">

					<div class="item-novo-pedido">
						<div class="pizza-novo-pedido">
							<div class="pizza">
								<div class="pizza-nome">
									<c:out value="${itemPedido.pizza.nomePizza}" />
								</div>
								<div class="pizza-ingredientes">
									<c:out value="${itemPedido.pizza.ingredientes}" />
								</div>
							</div>
						</div>

						<div class="preco-novo-pedido">
							R$
							<c:out value="${itemPedido.pizza.preco}" />
						</div>

						<div class="quantidade-novo-pedido">
							<c:out value="${itemPedido.quantidade}" />
						</div>

						<div class="subtotal-novo-pedido">
							R$
							<c:out value="${itemPedido.subtotal}" />
						</div>
					</div>
				</c:forEach>
				<div class="pizza-novo-pedido">
					<form method="post" action="Pedido">
						<input type="submit" value="Adicionar pizza">
					</form>
				</div>
				<div class="preco-novo-pedido"></div>
				<div class="quantidade-novo-pedido">Total:</div>
				<div class="subtotal-novo-pedido">
					R$
					<c:out value="${novoPedido.total}" />
				</div>

				<div class="item-novo-pedido">
					<form name="fechar-pedido" method="post" action="Pedido">
						<input type="hidden" name="action" value="fecharPedido">
						Forma de pagamento:
						<select name="formaDePagamento" id="formaDePagamento">
							<option value="DINHEIRO_SEM_TROCO">Dinheiro sem troco</option>
							<option value="DINHEIRO_COM_TROCO">Dinheiro com troco</option>
							<option value="CARTAO_DE_CREDITO">Cartão de crédito</option>
							<option value="CARTAO_DE_DEBITO">Cartão de débito</option>
						</select> <br>	
						Valor a receber: <input type="text" name="valorRecebido"> <br>
						
						<input type="submit" value="Fechar pedido">
					</form>
				</div>

			</div>
		</div>
		<div class="direita">
			<div id="dados-cliente">
				<div id="nome">
					<c:out value="${cliente.nome}" />
				</div>
				<div class="atributo">
					<c:out value="${cliente.email}" />
				</div>
				<div class="atributo">
					<c:out value="${cliente.telefone}" />
				</div>
				<div class="atributo">
					<c:out value="${cliente.endereco}" />
				</div>
			</div>

			<h2>Histórico de Pedidos</h2>
			<div id="historico-pedido">
				<c:forEach items="${pedidos}" var="pedido">
					<div class="pedido">

						<div class="pedido-data">
							<fmt:formatDate value="${pedido.dataHora}"
								pattern="dd/MM/yyyy HH:mm:ss" />
						</div>


						<c:forEach items="${pedido.itensDoPedido}" var="itemPedido">
							<div class="pedido-item">
								<div class="nome-pizza">
									<c:out value="${itemPedido.pizza.nomePizza}"></c:out>
								</div>
								<div class="quantidade-pizza">
									<c:out value="${itemPedido.quantidade}"></c:out>
								</div>
							</div>
						</c:forEach>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>