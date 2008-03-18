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
 * Created at / Criado em : 22/03/2007 - 10:51:02
 * 
 */

package br.com.nordestefomento.jrimum.vallia;

import br.com.nordestefomento.jrimum.vallia.AValidator4CadastroDePessoa;
import br.com.nordestefomento.jrimum.vallia.Validator4CNPJ;
import br.com.nordestefomento.jrimum.vallia.Validator4CPF;
import br.com.nordestefomento.jrimum.vallia.AValidator4CadastroDePessoa.EnumCadastroDePessoa;

import junit.framework.TestCase;

/**
 * 
 * Teste da classe <code>AValidator4CadastroDePessoa</code>.
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
public class TestAValidator4CadastroDePessoa extends TestCase {

	private AValidator4CadastroDePessoa validator;

	public void testGetInstance() {

		try {
			
			EnumCadastroDePessoa nulo = null;

			AValidator4CadastroDePessoa.getInstance(nulo);

			fail("IllegalArgumentException esperado não ocorreu.");
			assertTrue(false);

		} catch (IllegalArgumentException iaex) {

			assertTrue(true);
			System.out.println(iaex.getMessage());
		}

		try {

			AValidator4CadastroDePessoa.getInstance("abc123");

			fail("IllegalArgumentException esperado não ocorreu.");
			assertTrue(false);

		} catch (IllegalArgumentException iaex) {

			assertTrue(true);
			System.out.println(iaex.getMessage());
		}

		try {

			AValidator4CadastroDePessoa.getInstance("222333666");

			fail("IllegalArgumentException esperado não ocorreu.");
			assertTrue(false);

		} catch (IllegalArgumentException iaex) {

			assertTrue(true);
			System.out.println(iaex.getMessage());
		}

		try {

			AValidator4CadastroDePessoa.getInstance("112223330001");

			fail("IllegalArgumentException esperado não ocorreu.");
			assertTrue(false);

		} catch (IllegalArgumentException iaex) {

			assertTrue(true);
			System.out.println(iaex.getMessage());
		}
		
		assertNotNull(AValidator4CadastroDePessoa.getInstance("22233366638"));
		assertNotNull(AValidator4CadastroDePessoa.getInstance("222.333.666-38"));

		assertNotNull(AValidator4CadastroDePessoa.getInstance("11222333000181"));
		assertNotNull(AValidator4CadastroDePessoa.getInstance("11.222.333/0001-81"));

	}

	public void testSetCadastroDePessoa() {

		// Um validador de cpf não pode aceitar um cnpj

		validator = AValidator4CadastroDePessoa.getInstance("222.333.666-38");

		try {

			validator.setCodigoDoCadastro("11.222.333/0001-81");

			fail("IllegalArgumentException esperado não ocorreu.");
			assertTrue(false);

		} catch (IllegalArgumentException iaex) {

			assertTrue(true);
		}

	}

	public void testIsFisica() {

		validator = AValidator4CadastroDePessoa.getInstance("22233366638");

		assertTrue(validator instanceof Validator4CPF);
	}

	public void testIsJuridica() {

		validator = AValidator4CadastroDePessoa.getInstance("11222333000181");

		assertTrue(validator instanceof Validator4CNPJ);

	}

}
