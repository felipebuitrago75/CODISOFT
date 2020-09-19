import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TestJunit5 {
	@Test
	public void testJunit5() {
		assertThrows(NullPointerException.class, () -> {
			throw new NullPointerException("test");
		}, "test");
	}
}
