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
		require.invoke(Clojure.read("Tristan"));
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
	//JAVA TESTS:
	/*
	*
	*
	*
	*/
	//Tests for Member
	@Test
	void testNoahMemberJavaTrue() {
		var list = List.of("Alpha", "Beta", "Charlie", "Delta");
		var item = "Beta";
		assertTrue(Tommy.member(item, list));
	}
	@Test
	void testNoahMemberJavaFalse() {
		
		var list = List.of("Alpha", "Beta", "Charlie", "Delta");
		var item = "Zeta";
		assertFalse(Tommy.member(item, list));
	}
    @Test
    void testNoahMemberEmptyList() {
        var empty = List.<String>of();
        assertFalse(Tommy.member("Empty List", empty));
    }

	//Tests for Append 
	@Test
	void testNoahAppendJava() {
		var list1 = List.of("Alpha", "Beta");
		var list2 = List.of("Charlie", "Delta");
		var expected = List.of("Alpha", "Beta", "Charlie", "Delta");
		assertEquals(expected, Tommy.append(list1, list2));
	}
	@Test
		void testNoahAppendJavaTwo() {
		var list1 = List.of(1, 2);
		var list2 = List.of(3, 4);
		var expected = List.of(1, 2, 3, 4);
		assertEquals(expected, Tommy.append(list1, list2));
	}

	//Tests for Map 
	@Test
	void testNoahMap_IntSquared() {
		List<Integer> input = Arrays.asList(1, 2, 3, 4);
		Function<Integer, Integer> squareFunction = x -> x * x;
		List<Integer> result = Tommy.map(squareFunction, input);
		assertEquals(Arrays.asList(1, 4, 9, 16), result);
	}
	@Test 
	void testNoahMap_StringLengths() {
		List<String> input = Arrays.asList("Alpha", "Beta", "Gamma");
		Function<String, Integer> lengthFunction = s -> s.length();
		List<Integer> result = Tommy.map(lengthFunction, input);
		assertEquals(Arrays.asList(5, 4, 5), result);
	}

	//Tests for Same 
	@Test 
	void testNoahSameSizeFalse() {
		var list1 = List.of("Alpha", "Beta", "Charlie", "Gamma");
		var list2 = List.of("Alpha", "Beta", "Delta");
		assertFalse(Tommy.same(list1, list2));
	}
	@Test 
	void testNoahSameContentFalse() {
		var list1 = List.of("Alpha", "Beta", "Charlie");
		var list2 = List.of("Alpha", "Beta", "Delta");
		assertFalse(Tommy.same(list1, list2));
	}
	@Test
	void testNoahSameTrue() {
		var list1 = List.of("Alpha", "Beta", "Charlie");
		var list2 = List.of("Alpha", "Beta", "Charlie");
		assertTrue(Tommy.same(list1, list2));
	}

	//Tests for Intersect 
	@Test
	void testNoahIntersectJava() {
		var list1 = List.of("Alpha", "Beta", "Charlie", "Delta");
		var list2 = List.of("Charlie", "Delta", "Echo", "Foxtrot");
		var expected = List.of("Charlie", "Delta");
		assertEquals(expected, Tommy.intersect(list1, list2));
	}
	void testNoahIntersectJavaTwo() {
		var list1 = List.of("Golf", "Hotel", "India");
		var list2 = List.of("India", "Juliet", "Kilo");
		var expected = List.of("India");
		assertEquals(expected, Tommy.intersect(list1, list2));
	}
	void testNoahIntersectJavaEmpty() {
		var list1 = List.of("Lima", "Mike", "November");
		var list2 = List.<String>of();
		var expected = List.<String>of();
		assertEquals(expected, Tommy.intersect(list1, list2));
	}


	//CLOJURE TESTS:


	//Tests for Clojure Member 
	@Test
	void testMasonmemberClojureTrue() {
		var member = Clojure.var("Tristan", "member?");
		var list = List.of("Alpha", "Beta", "Charlie", "Delta");
		var item = "Beta";
		assertTrue((Boolean) member.invoke(item, list));
	}
	@Test
	void testMasonmemberClojureFalse() {
		var member = Clojure.var("Tristan", "member?");
		var list = List.of("Alpha", "Beta", "Charlie", "Delta");
		var item = "Zeta";
		assertFalse((Boolean) member.invoke(item, list));	
	}
	//Tests for Clojure append 
	@Test 
	void testMasonappendClojure() {
		var append = Clojure.var("Tristan", "append");
		var list1 = List.of("Alpha", "Beta");
		var list2 = List.of("Charlie", "Delta");
		var expected = List.of("Alpha", "Beta", "Charlie", "Delta");
		assertEquals(expected, append.invoke(list1, list2));
	}
	@Test 
	void testMasonappendClojureTwo() {
		var append = Clojure.var("Tristan", "append");
		var list1 = List.of(1, 2);
		var list2 = List.of(3, 4);
		var expected = List.of(1, 2, 3, 4);
		assertEquals(expected, append.invoke(list1, list2));
	}
	//Tests for Clojure Map
	@Test
	void testMasonmymapClojure_IntSquared() {
		var map = Clojure.var("Tristan", "map");
		List<Integer> input = Arrays.asList(1, 2, 3, 4);
		Function<Integer, Integer> squareFunction = x -> x * x;
		List<Integer> result = (List<Integer>) map.invoke(squareFunction, input);
		assertEquals(Arrays.asList(1, 4, 9, 16), result);
	}
	@Test 
	void testMasonmymapClojure_StringLengths() {
		var map = Clojure.var("Tristan", "map");
		List<String> input = Arrays.asList("Alpha", "Beta", "Gamma");
		Function<String, Integer> lengthFunction = s -> s.length();
		List<Integer> result = (List<Integer>) map.invoke(lengthFunction, input);
		assertEquals(Arrays.asList(5, 4, 5), result);
	}
	//Tests for Clojure Same 
	@Test 
	void testMasonsameClojureSizeFalse() {
		var same = Clojure.var("Tristan", "same");
		var list1 = List.of("Alpha", "Beta", "Charlie", "Gamma");
		var list2 = List.of("Alpha", "Beta", "Delta");
		assertFalse((Boolean) same.invoke(list1, list2));
	}
	@Test 
	void testMasonsameClojureContentFalse() {
		var same = Clojure.var("Tristan", "same");
		var list1 = List.of("Alpha", "Beta", "Charlie");
		var list2 = List.of("Alpha", "Beta", "Delta");
		assertFalse((Boolean) same.invoke(list1, list2));
	}
	@Test
	void testMasonsameClojureTrue() {
		var same = Clojure.var("Tristan", "same");
		var list1 = List.of("Alpha", "Beta", "Charlie");
		var list2 = List.of("Alpha", "Beta", "Charlie");
		assertTrue((Boolean) same.invoke(list1, list2));
	}
	//Tests for Clojure Intersect
	@Test
	void testMasonintersectClojure() {
		var intersect = Clojure.var("Tristan", "intersect");
		var list1 = List.of("Alpha", "Beta", "Charlie", "Delta");
		var list2 = List.of("Charlie", "Delta", "Echo", "Foxtrot");
		var expected = List.of("Charlie", "Delta");
		assertEquals(expected, intersect.invoke(list1, list2));
	}
	void testMasonintersectClojureTwo() {
		var intersect = Clojure.var("Tristan", "intersect");
		var list1 = List.of("Golf", "Hotel", "India");
		var list2 = List.of("India", "Juliet", "Kilo");
		var expected = List.of("India");
		assertEquals(expected, intersect.invoke(list1, list2));
	}
	void testMasonintersectClojureEmpty() {
		var intersect = Clojure.var("Tristan", "intersect");
		var list1 = List.of("Lima", "Mike", "November");
		var list2 = List.<String>of();
		var expected = List.<String>of();
		assertEquals(expected, intersect.invoke(list1, list2));
	}	


}
