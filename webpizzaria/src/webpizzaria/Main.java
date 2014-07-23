package webpizzaria;

import dominio.EnumFormaDePagamento;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EnumFormaDePagamento forma = EnumFormaDePagamento.CARTAO_DE_CREDITO;
		
		String nomeDoEnum = forma.name();
		
		EnumFormaDePagamento forma2;
		
		forma2 = EnumFormaDePagamento.valueOf(nomeDoEnum);
		
		System.out.println(forma2.name());
	}

}
