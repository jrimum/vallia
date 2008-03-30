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
 * Created at: 30/03/2008 - 18:54:06
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
 * Criado em: 30/03/2008 - 18:54:06
 * 
 */


package br.com.nordestefomento.jrimum.vallia.digitoverificador;

import br.com.nordestefomento.jrimum.vallia.digitoverificador.AModulo;
import br.com.nordestefomento.jrimum.vallia.digitoverificador.EnumModulo;

import junit.framework.TestCase;

/**
 * Teste da classe <code>Módulo10</code>.
 * 
 * @author Gabriel Guimarães
 * @author Gilmar P.S.L
 * @author Misael Barreto
 * @author Rômulo Augusto
 * @since 16/03/2007
 */
public class TestModulo10 extends TestCase {

	private AModulo modulo10;

	protected void setUp() throws Exception {

		super.setUp();

		modulo10 = AModulo.getInstance(EnumModulo.MODULO_10);
	}

	public void testCalcularString() {

		try {

			modulo10.calcular(null);

			assertTrue(false);
			fail("IllegalArgumentException esperado não ocorreu.");

		} catch (IllegalArgumentException iaex) {

			assertTrue(true);
			System.out.println(iaex.getMessage());
		}

		try {

			modulo10.calcular("acb123");

			assertTrue(false);
			fail("IllegalArgumentException esperado não ocorreu.");

		} catch (IllegalArgumentException iaex) {

			assertTrue(true);
			System.out.println(iaex.getMessage());
		}

		assertEquals(4, modulo10.calcular("123456"));

	}

	public void testCalcularLong() {

		assertEquals(4, modulo10.calcular(123456));
	}

}
