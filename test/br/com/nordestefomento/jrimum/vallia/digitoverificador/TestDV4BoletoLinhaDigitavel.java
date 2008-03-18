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
 * Created at / Criado em : 19/03/2007 - 15:54:14
 * 
 */

package br.com.nordestefomento.jrimum.vallia.digitoverificador;

import br.com.nordestefomento.jrimum.vallia.digitoverificador.ADigitoVerificador;
import br.com.nordestefomento.jrimum.vallia.digitoverificador.DV4BoletoLinhaDigitavel;

import junit.framework.TestCase;

/**
 * 
 * Teste da classe <code>DV_Boleto_LinhaDigitável</code>
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
public class TestDV4BoletoLinhaDigitavel extends TestCase {
	
	private ADigitoVerificador dv_Validator_LinhaDigitavel;

	protected void setUp() throws Exception {
		
		super.setUp();
		
		dv_Validator_LinhaDigitavel = new DV4BoletoLinhaDigitavel();
	}

	public void testCalcularString() {
		
		try {
					
			dv_Validator_LinhaDigitavel.calcular(null);
			
			fail("IllegalArgumentException esperado não ocorreu.");
			assertTrue(false);
			
		}catch(IllegalArgumentException iaex) {
			
			assertTrue(true);
			System.out.println(iaex.getMessage());
		}
		
		try {
			
			dv_Validator_LinhaDigitavel.calcular("abc123");
			
			fail("IllegalArgumentException esperado não ocorreu.");
			assertTrue(false);
	
		}catch(IllegalArgumentException iaex) {
			
			assertTrue(true);
			System.out.println(iaex.getMessage());
		}
		
		try {
			
			dv_Validator_LinhaDigitavel.calcular("12345678910");
	
			fail("IllegalArgumentException esperado não ocorreu.");
			assertTrue(false);
			
		}catch(IllegalArgumentException iaex) {
			
			assertTrue(true);
			System.out.println(iaex.getMessage());
		}
		
		assertEquals(3, dv_Validator_LinhaDigitavel.calcular("999977721"));
		assertEquals(3, dv_Validator_LinhaDigitavel.calcular("99997.7721"));
		assertEquals(2, dv_Validator_LinhaDigitavel.calcular("3053015008"));
		assertEquals(2, dv_Validator_LinhaDigitavel.calcular("30530.15008"));
	}

	public void testCalcularLong() {
		
		assertEquals(0, dv_Validator_LinhaDigitavel.calcular(0));		
		assertEquals(3, dv_Validator_LinhaDigitavel.calcular(999977721L));
		assertEquals(2, dv_Validator_LinhaDigitavel.calcular(3053015008L));
	}

}
