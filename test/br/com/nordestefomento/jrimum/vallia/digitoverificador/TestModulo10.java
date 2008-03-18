/*
 * Copyright 2007, JMatryx Group
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * <a href="http://www.apache.org/licenses/LICENSE-2.0">
 * http://www.apache.org/licenses/LICENSE-2.0 </a>
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * Copyright 2007, Grupo JMatryx
 * 
 * Licenciado sob a licença da Apache, versão 2.0 (a “licença”); você não pode
 * usar este arquivo exceto na conformidade com a licença. Você pode obter uma
 * cópia da licença em
 * 
 * <a href="http://www.apache.org/licenses/LICENSE-2.0">
 * http://www.apache.org/licenses/LICENSE-2.0 </a>
 * 
 * A menos que seja requerido pela aplicação da lei ou esteja de acordo com a
 * escrita, o software distribuído sob esta licença é distribuído “COMO É”
 * BASE,SEM AS GARANTIAS OU às CONDIÇÕES DO TIPO, expresso ou implicado. Veja a
 * licença para as permissões sobre a línguagem específica e limitações quando
 * sob licença.
 * 
 * 
 * Created at / Criado em : 17/03/2007 - 17:53:25
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
