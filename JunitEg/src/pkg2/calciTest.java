package pkg2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class calciTest {

	@Test
	void test() {
		calci obj=new calci();
		assertEquals(10, obj.addition(5,6));
	}

}