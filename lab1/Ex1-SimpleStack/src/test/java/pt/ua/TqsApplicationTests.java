package pt.ua;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class TqsApplicationTests {

	@SuppressWarnings("rawtypes")
	TqsStack stack;

	@BeforeEach
	public void setUp() {
		stack = new TqsStack<>();
	}

	@DisplayName("A stack is empty on construction")
	@Test
	public void isEmpty() {
		Assertions.assertTrue(stack.isEmpty());
	}

	@DisplayName("A stack has size 0 on construction.")
	@Test
	public void sizeIsZero() {
		Assertions.assertTrue(stack.size() == 0);
	}

	@SuppressWarnings("unchecked")
	@DisplayName("After n pushes to an empty stack, n > 0, the stack is not empty and its size is n")
	@Test
	public void pushNotEmpty() {
		int n = 2;
		stack.push(3);
		stack.push(4);

		Assertions.assertAll(
				() -> assertFalse(stack.isEmpty()),
				() -> assertEquals(n, stack.size())
		);
	}

	@SuppressWarnings("unchecked")
	@DisplayName("If one pushes x then pops, the value popped is x.")
	@Test
	public void PushXThenPop() {
		stack.push("a");
		stack.push(45);
		Assertions.assertEquals(45, stack.pop());
	}

	@SuppressWarnings("unchecked")
	@DisplayName("If one pushes x then peeks, the value returned is x, but the size stays the same")
	@Test
	public void PushXThenPeek() {
		stack.push(20);
		stack.push(21);
		stack.push(23);
		Assertions.assertAll(
				() -> assertEquals(23, stack.peek()),
				() -> assertEquals(3, stack.size())
		);
	}

	@SuppressWarnings("unchecked")
	@DisplayName("If the size is n, then after n pops, the stack is empty and has a size 0")
	@Test
	public void PopN() {
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.pop();
		stack.pop();
		stack.pop();
		Assertions.assertAll(
				() -> assertTrue(stack.isEmpty()),
				() -> assertEquals(0, stack.size())
		);
	}

	@DisplayName("Popping from an empty stack does throw a NoSuchElementException")
	@Test
	public void PopEmptyStackThrowException() {
		Assertions.assertThrows(NoSuchElementException.class, () -> stack.pop());
	}

	@DisplayName("Peeking into an empty stack does throw a NoSuchElementException")
	@Test
	public void PeekEmptyStackThrowException() {
		Assertions.assertThrows(NoSuchElementException.class, () -> stack.peek());
	}

	@DisplayName("For bounded stacks only: pushing onto a full stack does throw\n" +
			"an IllegalStateException")
	@Test
	public void PushingToBoundedStack() {
		Stack<Integer> BoundedStack = new TqsStack<>(2);
		BoundedStack.push(1);
		BoundedStack.push(2);
		Assertions.assertThrows(IllegalStateException.class, () -> BoundedStack.push(3)); // não tá a funcionar porque ele não tá a reconhecer o override na classe TqsStack
	}
}
