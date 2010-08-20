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
 * Created at: 30/03/2008 - 18:52:12
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
 * Criado em: 30/03/2008 - 18:52:12
 * 
 */


package org.jrimum.vallia;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.jrimum.vallia.AbstractCPRFValidator.TipoDeCPRF;
import org.junit.Test;


/**
 * 
 * Teste da classe <code>AbstractCPRFValidator</code>.
 * 
 * 
 * @author Gabriel Guimarães
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a> 
 * @author <a href="mailto:misaelbarreto@gmail.com">Misael Barreto</a>
 * @author <a href="mailto:romulomail@gmail.com">Rômulo Augusto</a>
 * 
 * @since JRimum 1.0
 * 
 * @version 1.0
 */
public class TestAbstractCPRFValidator{

	private AbstractCPRFValidator validator;

	@Test
	public void testGetInstance() {

		try {
			
			TipoDeCPRF nulo = null;

			AbstractCPRFValidator.create(nulo);

			fail("IllegalArgumentException esperado não ocorreu.");
			assertTrue(false);

		} catch (IllegalArgumentException iaex) {

			assertTrue(true);
			System.out.println(iaex.getMessage());
		}

		try {

			AbstractCPRFValidator.create("abc123");

			fail("IllegalArgumentException esperado não ocorreu.");
			assertTrue(false);

		} catch (IllegalArgumentException iaex) {

			assertTrue(true);
			System.out.println(iaex.getMessage());
		}

		try {

			AbstractCPRFValidator.create("222333666");

			fail("IllegalArgumentException esperado não ocorreu.");
			assertTrue(false);

		} catch (IllegalArgumentException iaex) {

			assertTrue(true);
			System.out.println(iaex.getMessage());
		}

		try {

			AbstractCPRFValidator.create("112223330001");

			fail("IllegalArgumentException esperado não ocorreu.");
			assertTrue(false);

		} catch (IllegalArgumentException iaex) {

			assertTrue(true);
			System.out.println(iaex.getMessage());
		}
		
		assertNotNull(AbstractCPRFValidator.create("22233366638"));
		assertNotNull(AbstractCPRFValidator.create("222.333.666-38"));

		assertNotNull(AbstractCPRFValidator.create("11222333000181"));
		assertNotNull(AbstractCPRFValidator.create("11.222.333/0001-81"));

	}

	@Test
	public void testIsFisica() {

		validator = AbstractCPRFValidator.create("22233366638");

		assertTrue(validator instanceof CPFValidator);
	}

	@Test
	public void testIsJuridica() {

		validator = AbstractCPRFValidator.create("11222333000181");

		assertTrue(validator instanceof CNPJValidator);

	}

}
