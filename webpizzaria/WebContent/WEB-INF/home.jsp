<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<style rel="stylesheet" type="text/css">
.conteudo-div {
	display: table;
	clear: both;
}

.esquerda {
	position: absolute;
	left: 0;
	top: 0;
	margin: 20px;
	margin-left: 10%;
	width: 70%;
}

.direita {
	position: absolute;
	right: 0;
	top: 0;
	margin: 20px;
	width: 30%;
}

#historico-pedidos {
	display: table;
}

.pedido {
	display: table;
	margin: 10px;
}

.pedido-cabecalho {
	display: table-row;
}

.pedido-data {
	font-size: 16px;
	font-decoration: bold;
	align: left;
	display: table-caption;
}

.pedido-item {
	display: table-row;
	font-size: 12px;
}

.nome-pizza {
	display: table-cell;
}

.quantidade-pizza {
	display: table-cell;
}

#dados-cliente {
	border: 1px;
	border-style: solid;
	padding: 10px;
	display: block;
}

#dados-cliente #nome {
	font-size: 22px;
	margin: 5px;
}

#dados-cliente .atributo {
	font-size: 12px;
	margin: 2px;
	margin-left: 12px;
}

#dados-cliente table td {
	padding: 4px;
}
</style>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home - Pizzaria do Manolo</title>
</head>
<body>
	<div class=conteudo-div>
		<div class="esquerda">

			<h2>Histórico de Pedidos</h2>
			<div id="historico-pedido">
				<c:forEach items="${pedidos}" var="pedido">
					<div class="pedido">
						<div class="pedido-cabecalho">
							<div class="pedido-data">
								<fmt:formatDate value="${pedido.dataHora}"
									pattern="dd/MM/yyyy HH:mm:ss" />
							</div>
						</div>

						<c:forEach items="${pedido.itensDoPedido}" var="itemPedido">
							<div class="pedido-pizza">
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
		<div class="direita">
			<div id="dados-cliente">
				<div id="nome">
					<c:out value="${cliente.nome }" />
				</div>
				<div class="atributo">
					<c:out value="${cliente.email }" />
				</div>
				<div class="atributo">
					<c:out value="${cliente.telefone }" />
				</div>
				<div class="atributo">
					<c:out value="${cliente.endereco }" />
				</div>
			</div>
		</div>
	</div>
</body>
</html>