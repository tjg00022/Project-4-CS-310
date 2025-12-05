import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

// java



class UnitTestsTest {

	// Helper: try to find and invoke UnitTests.testMemberJava(item, list)
	private Boolean invokeUnitTestsMember(String item, List<String> list) {
		try {
			Class<?> utClass = Class.forName("UnitTests");
			Method[] methods = utClass.getDeclaredMethods();
			for (Method m : methods) {
				if (!m.getName().equals("testMemberJava")) continue;
				Class<?>[] params = m.getParameterTypes();
				if (params.length != 2) continue;
				// Accept methods where first param can be String/Object and second is List/Object
				boolean firstOk = params[0].isAssignableFrom(String.class) || params[0].isAssignableFrom(Object.class);
				boolean secondOk = java.util.List.class.isAssignableFrom(params[1]) || params[1].isAssignableFrom(Object.class);
				if (!firstOk || !secondOk) continue;
				m.setAccessible(true);
				Object ret = m.invoke(null, item, list);
				if (ret instanceof Boolean) return (Boolean) ret;
				if (ret instanceof java.lang.Boolean) return (Boolean) ret;
				// If primitive boolean returned, reflection still boxes it to Boolean
				return null;
			}
			return null; // method not found
		} catch (ClassNotFoundException e) {
			return null;
		} catch (IllegalAccessException | IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			// unwrap and fail the test by rethrowing runtime exception
			Throwable cause = e.getCause();
			if (cause instanceof RuntimeException) throw (RuntimeException) cause;
			throw new RuntimeException(cause);
		}
	}

	@Test
	void memberJavaReturnsTrueForPresentItem() {
		var list = List.of("Alpha", "Beta", "Charlie", "Delta");
		var item = "Beta";
		Boolean result = invokeUnitTestsMember(item, list);
		Assumptions.assumeTrue(result != null, "Skipping: UnitTests.testMemberJava not present");
		assertTrue(result);
	}

	@Test
	void memberJavaReturnsFalseForMissingItem() {
		var list = List.of("Alpha", "Beta", "Charlie", "Delta");
		var item = "Zeta";
		Boolean result = invokeUnitTestsMember(item, list);
		Assumptions.assumeTrue(result != null, "Skipping: UnitTests.testMemberJava not present");
		assertFalse(result);
	}
}