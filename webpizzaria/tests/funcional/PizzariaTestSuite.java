package funcional;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({
	ManterPizzaTest.class,
	ManterClienteTest.class,
	ManterPedidoTest.class
})
public class PizzariaTestSuite {

}
