package funcional;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({
	PizzaDAOTest.class,
	ClienteDAOTest.class,
	PedidoDAOTest.class
})
public class PizzariaTestSuite {

}
