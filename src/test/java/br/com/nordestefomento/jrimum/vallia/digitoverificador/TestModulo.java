package br.com.nordestefomento.jrimum.vallia.digitoverificador;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class TestModulo{

	private static Modulo modulo10  = new Modulo(TipoDeModulo.MODULO10);
	
	private static Modulo modulo11  = new Modulo(TipoDeModulo.MODULO11);

	@Test
	public final void testModuloEnumModulo() {
				
		try {

			new Modulo(null);

			assertTrue(false);
			fail("IllegalArgumentException esperado não ocorreu.");

		} catch (IllegalArgumentException iaex) {

			assertTrue(true);
			System.out.println(iaex.getMessage());
		}

	}

	@Test
	public final void testModuloEnumModuloIntInt() {
		
		try {

			new Modulo(null, 0, 0);

			assertTrue(false);
			fail("IllegalArgumentException esperado não ocorreu.");

		} catch (IllegalArgumentException iaex) {

			assertTrue(true);
			System.out.println(iaex.getMessage());
		}
	}

	@Test
	public final void testCalculeMod11LongIntInt() {
		
		assertEquals(2, Modulo.calculeMod11(654321,2,9));
	}

	@Test
	public final void testCalculeMod11StringIntInt() {
		
		try {

			Modulo.calculeMod11("abc123", 2, 9);

			assertTrue(false);
			fail("IllegalArgumentException esperado não ocorreu.");

		} catch (IllegalArgumentException iaex) {

			assertTrue(true);
			System.out.println(iaex.getMessage());
		}

		assertEquals(2, Modulo.calculeMod11("654321",2,9));
	}

	@Test
	public final void testCalculeSomaSequencialMod11() {
	
		assertEquals(112,Modulo.calculeSomaSequencialMod11("654321", 2, 9));	
	}

	@Test
	public final void testCalculeMod10LongIntInt() {
		
		assertEquals(4, Modulo.calculeMod10(123456,1,2));
	}

	@Test
	public final void testCalculeMod10StringIntInt() {
		
		try {

			Modulo.calculeMod10("acb123", 1, 2);

			assertTrue(false);
			fail("IllegalArgumentException esperado não ocorreu.");

		} catch (IllegalArgumentException iaex) {

			assertTrue(true);
			System.out.println(iaex.getMessage());
		}

		assertEquals(4, Modulo.calculeMod10("123456",1,2));
	}
	
	@Test
	public final void testCalculeSomaSequencialMod10() {
		
		assertEquals(24,Modulo.calculeSomaSequencialMod10("123456", 1, 2));
	}

	@Test
	public final void testCalculeString() {
		
		assertEquals(2, modulo11.calcule("654321"));
		
		assertEquals(4, modulo10.calcule("123456"));
	}

	@Test
	public final void testCalculeLong() {
		
		assertEquals(2, modulo11.calcule(654321));
		
		assertEquals(4, modulo10.calcule(123456));
	}

}
