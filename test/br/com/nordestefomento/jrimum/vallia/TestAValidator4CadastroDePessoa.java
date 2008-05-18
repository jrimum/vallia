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


package br.com.nordestefomento.jrimum.vallia;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import br.com.nordestefomento.jrimum.vallia.AValidator4CPRF.EnumCPRF;

/**
 * 
 * Teste da classe <code>AValidator4CPRF</code>.
 * 
 * 
 * @author Gabriel Guimarães
 * @author Gilmar P.S.L
 * @author Misael Barreto
 * @author Rômulo Augusto
 * 
 * @since JMatryx 1.0
 * 
 * @version 1.0
 */
public class TestAValidator4CadastroDePessoa{

	private AValidator4CPRF validator;

	@Test
	public void testGetInstance() {

		try {
			
			EnumCPRF nulo = null;

			AValidator4CPRF.getInstance(nulo);

			fail("IllegalArgumentException esperado não ocorreu.");
			assertTrue(false);

		} catch (IllegalArgumentException iaex) {

			assertTrue(true);
			System.out.println(iaex.getMessage());
		}

		try {

			AValidator4CPRF.getInstance("abc123");

			fail("IllegalArgumentException esperado não ocorreu.");
			assertTrue(false);

		} catch (IllegalArgumentException iaex) {

			assertTrue(true);
			System.out.println(iaex.getMessage());
		}

		try {

			AValidator4CPRF.getInstance("222333666");

			fail("IllegalArgumentException esperado não ocorreu.");
			assertTrue(false);

		} catch (IllegalArgumentException iaex) {

			assertTrue(true);
			System.out.println(iaex.getMessage());
		}

		try {

			AValidator4CPRF.getInstance("112223330001");

			fail("IllegalArgumentException esperado não ocorreu.");
			assertTrue(false);

		} catch (IllegalArgumentException iaex) {

			assertTrue(true);
			System.out.println(iaex.getMessage());
		}
		
		assertNotNull(AValidator4CPRF.getInstance("22233366638"));
		assertNotNull(AValidator4CPRF.getInstance("222.333.666-38"));

		assertNotNull(AValidator4CPRF.getInstance("11222333000181"));
		assertNotNull(AValidator4CPRF.getInstance("11.222.333/0001-81"));

	}

	@Test
	public void testSetCadastroDePessoa() {

		// Um validador de cpf não pode aceitar um cnpj

		validator = AValidator4CPRF.getInstance("222.333.666-38");

		try {

			validator.setCodigoDoCadastro("11.222.333/0001-81");

			fail("IllegalArgumentException esperado não ocorreu.");
			assertTrue(false);

		} catch (IllegalArgumentException iaex) {

			assertTrue(true);
		}

	}

	@Test
	public void testIsFisica() {

		validator = AValidator4CPRF.getInstance("22233366638");

		assertTrue(validator instanceof Validator4CPF);
	}

	@Test
	public void testIsJuridica() {

		validator = AValidator4CPRF.getInstance("11222333000181");

		assertTrue(validator instanceof Validator4CNPJ);

	}

}
