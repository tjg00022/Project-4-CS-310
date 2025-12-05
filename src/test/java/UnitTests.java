import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Arrays;


import java.util.List;
import java.util.function.Function;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clojure.java.api.Clojure;
import clojure.spec.alpha.*;


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

	//Tests for Member

	@Test
	void testMemberJavaTrue() {
		var list = List.of("Alpha", "Beta", "Charlie", "Delta");
		var item = "Beta";
		assertTrue(Tommy.member(item, list));
	}
	@Test
	void testMemberJavaFalse() {
		
		var list = List.of("Alpha", "Beta", "Charlie", "Delta");
		var item = "Zeta";
		assertFalse(Tommy.member(item, list));
	}
    @Test
    void testMemberEmptyList() {
        var empty = List.<String>of();
        assertFalse(Tommy.member("Empty List", empty));
    }

	//Tests for Append 
	@Test
	void testAppendJava() {
		var list1 = List.of("Alpha", "Beta");
		var list2 = List.of("Charlie", "Delta");
		var expected = List.of("Alpha", "Beta", "Charlie", "Delta");
		assertEquals(expected, Tommy.append(list1, list2));
	}
	@Test
		void testAppendJavaFalse() {
		var list1 = List.of("Echo", "Foxtrot");
		var list2 = List.of("Golf", "Hotel");
		var expected = List.of("Echo", "Foxtrot", "Golf", "Hotel");
		assertEquals(expected, Tommy.append(list1, list2));
	}

	//Tests for Map 
	@Test
	void testMap_IntSquared() {
		List<Integer> input = Arrays.asList(1, 2, 3, 4);
		Function<Integer, Integer> squareFunction = x -> x * x;
		List<Integer> result = Tommy.map(squareFunction, input);
		assertEquals(Arrays.asList(1, 4, 9, 16), result);
	}
	@Test 
	void testMap_StringLengths() {
		List<String> input = Arrays.asList("Alpha", "Beta", "Gamma");
		Function<String, Integer> lengthFunction = s -> s.length();
		List<Integer> result = Tommy.map(lengthFunction, input);
		assertEquals(Arrays.asList(5, 4, 5), result);
	}

	//Tests for Same 
	@Test 
	void testSameSizeFalse() {
		var list1 = List.of("Alpha", "Beta", "Charlie", "Gamma");
		var list2 = List.of("Alpha", "Beta", "Delta");
		assertFalse(ListFunctions.same(list1, list2));
	}
	@Test 
	void testSameContentFalse() {
		var list1 = List.of("Alpha", "Beta", "Charlie");
		var list2 = List.of("Alpha", "Beta", "Delta");
		assertFalse(ListFunctions.same(list1, list2));
	}
	@Test
	void testSameTrue() {
		var list1 = List.of("Alpha", "Beta", "Charlie");
		var list2 = List.of("Alpha", "Beta", "Charlie");
		assertTrue(ListFunctions.same(list1, list2));
	}

	//Tests for Intersect 
	@Test
	void testIntersectJava() {
		var list1 = List.of("Alpha", "Beta", "Charlie", "Delta");
		var list2 = List.of("Charlie", "Delta", "Echo", "Foxtrot");
		var expected = List.of("Charlie", "Delta");
		assertEquals(expected, ListFunctions.intersect(list1, list2));
	}
	void testIntersectJavaTwo() {
		var list1 = List.of("Golf", "Hotel", "India");
		var list2 = List.of("India", "Juliet", "Kilo");
		var expected = List.of("India");
		assertEquals(expected, ListFunctions.intersect(list1, list2));
	}
	void testIntersectJavaEmpty() {
		var list1 = List.of("Lima", "Mike", "November");
		var list2 = List.<String>of();
		var expected = List.<String>of();
		assertEquals(expected, ListFunctions.intersect(list1, list2));
	}








}
