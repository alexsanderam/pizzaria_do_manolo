package funcional;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class PizzariaExecutarTodosTestes {
	
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(PizzariaTestSuite.class);
		
		System.out.println("\nForam encontradas " + result.getFailureCount() + " falhas");
		for (Failure failure: result.getFailures()) {
			System.out.println(failure);
		}
		
		float taxaDeSucesso = (1 - (result.getFailureCount()/result.getRunCount())) * 100;
		
		System.out.println("\nTaxa de sucesso: " + taxaDeSucesso + "%");
	}

}
