import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
	@Test 
	void testMemberJavaTrue() {
		
		var list = List.of("Alpha", "Beta", "Charlie", "Delta");
		var item = "Beta";
		assertTrue(ListFunctions.member(item, list));
	}
	void testMemberJavaFalse() {
		
		var list = List.of("Alpha", "Beta", "Charlie", "Delta");
		var item = "Zeta";
		assertFalse(ListFunctions.member(item, list));
	}
    @Test
    void testMemberEmptyList() {
        var empty = List.<String>of();
        assertFalse(ListFunctions.member("Anything", empty));
    }

    @Test
    void testMemberMissingItem() {
        var list = List.of("Alpha", "Beta");
        assertFalse(ListFunctions.member("Zeta", list));
    }



}
