/*
 * Copyright 2008 JRimum Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * Created at: 30/03/2008 - 18:53:54
 * 
 * ================================================================================
 * 
 * Direitos autorais 2008 JRimum Project
 * 
 * Licenciado sob a Licença Apache, Versão 2.0 ("LICENÇA"); você não pode usar
 * esse arquivo exceto em conformidade com a esta LICENÇA. Você pode obter uma
 * cópia desta LICENÇA em http://www.apache.org/licenses/LICENSE-2.0 A menos que
 * haja exigência legal ou acordo por escrito, a distribuição de software sob
 * esta LICENÇA se dará “COMO ESTÁ”, SEM GARANTIAS OU CONDIÇÕES DE QUALQUER
 * TIPO, sejam expressas ou tácitas. Veja a LICENÇA para a redação específica a
 * reger permissões e limitações sob esta LICENÇA.
 * 
 * Criado em: 30/03/2008 - 18:53:54
 */

package org.jrimum.vallia.digitoverificador;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class TestModulo {

	private static Modulo modulo10 = new Modulo(TipoDeModulo.MODULO10);

	private static Modulo modulo11 = new Modulo(TipoDeModulo.MODULO11);

	@Test(expected = IllegalArgumentException.class)
	public void quandoTipoDeModuloNullDisparaExcecao() {
		new Modulo(null);
		new Modulo(null, 0, 0);
	}

	@Test
	public final void testCalculeMod11LongIntInt() {

		assertEquals(2, Modulo.calculeMod11(654321, 2, 9));
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

		assertEquals(2, Modulo.calculeMod11("654321", 2, 9));
	}

	@Test
	public final void testCalculeSomaSequencialMod11() {

		assertEquals(112, Modulo.calculeSomaSequencialMod11("654321", 2, 9));
	}

	@Test
	public final void testCalculeMod10LongIntInt() {

		assertEquals(4, Modulo.calculeMod10(123456, 1, 2));
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

		assertEquals(4, Modulo.calculeMod10("123456", 1, 2));
	}

	@Test
	public final void testCalculeSomaSequencialMod10() {

		assertEquals(24, Modulo.calculeSomaSequencialMod10("123456", 1, 2));
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
