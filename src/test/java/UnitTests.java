import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clojure.java.api.Clojure;

class UnitTests {
	@BeforeAll
	static void requires() {
        var require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read("Alice"));
        // require.invoke(Clojure.read("Bob"));
	}

	@Test
	void testAliceThirdClojure() {
		var third = Clojure.var("Alice", "third");
		var list = List.of("A", "B", "C", "D", "E");
		assertEquals("C", third.invoke(list));
	}

	@Test
	void testAliceThirdJava() {
		var list = List.of("A", "B", "C", "D", "E");
		assertEquals("C", Alice.third(list));
	}
}
