package unitario;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({
	ClienteTest.class,
	ItemPedidoTest.class,
	PagamentoTest.class,
	PedidoTest.class,
	PizzaTest.class
})
public class PizzariaTestSuite {

}
