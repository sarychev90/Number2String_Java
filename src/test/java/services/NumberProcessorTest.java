package services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumberProcessorTest {

	private static final String TEXT_NUMBER = "\u0428\u0456\u0441\u0442\u0434\u0435\u0441\u044f\u0442 \u0448\u0456\u0441\u0442\u044c \u0433\u0440\u043d\u002e\u002c \u0035\u0036 \u043a\u043e\u043f\u002e";

	@Test
	public void testDigits2Text() {
		String s = "66,56";
		String textNumber = NumberProcessor.digits2Text(s);
		assertEquals(TEXT_NUMBER, textNumber);
	}
}
