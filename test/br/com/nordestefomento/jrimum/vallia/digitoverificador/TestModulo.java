package br.com.nordestefomento.jrimum.vallia.digitoverificador;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class TestModulo {

	private static Modulo modulo10  = new Modulo(EnumModulo.MODULO10);
	
	private static Modulo modulo11  = new Modulo(EnumModulo.MODULO11);

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
	public final void testCalcularMod11LongIntInt() {
		
		assertEquals(2, Modulo.calcularMod11(654321,2,9));
	}

	@Test
	public final void testCalcularMod11StringIntInt() {
		
		try {

			Modulo.calcularMod11("abc123", 2, 9);

			assertTrue(false);
			fail("IllegalArgumentException esperado não ocorreu.");

		} catch (IllegalArgumentException iaex) {

			assertTrue(true);
			System.out.println(iaex.getMessage());
		}

		assertEquals(2, Modulo.calcularMod11("654321",2,9));
	}

	@Test
	public final void testCalcularSomaSequencialMod11() {
	
		assertEquals(112,Modulo.calcularSomaSequencialMod11("654321", 2, 9));	
	}

	@Test
	public final void testCalcularMod10LongIntInt() {
		
		assertEquals(4, Modulo.calcularMod10(123456,1,2));
	}

	@Test
	public final void testCalcularMod10StringIntInt() {
		
		try {

			Modulo.calcularMod10("acb123", 1, 2);

			assertTrue(false);
			fail("IllegalArgumentException esperado não ocorreu.");

		} catch (IllegalArgumentException iaex) {

			assertTrue(true);
			System.out.println(iaex.getMessage());
		}

		assertEquals(4, Modulo.calcularMod10("123456",1,2));
	}
	
	@Test
	public final void testCalcularSomaSequencialMod10() {
		
		assertEquals(24,Modulo.calcularSomaSequencialMod10("123456", 1, 2));
	}

	@Test
	public final void testCalcularString() {
		
		assertEquals(2, modulo11.calcular("654321"));
		
		assertEquals(4, modulo10.calcular("123456"));
	}

	@Test
	public final void testCalcularLong() {
		
		assertEquals(2, modulo11.calcular(654321));
		
		assertEquals(4, modulo10.calcular(123456));
	}

}
