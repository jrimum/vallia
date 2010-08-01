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
 * Created at: 30/03/2008 - 18:53:28
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
 * Criado em: 30/03/2008 - 18:53:28
 * 
 */


package org.jrimum.vallia.digitoverificador;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * Teste da classe <code>DV_Boleto_LinhaDigitável</code>
 * 
 * 
 * @author Gabriel Guimarães
 * @author Gilmar P.S.L
 * @author <a href="mailto:misaelbarreto@gmail.com">Misael Barreto</a>
 * @author <a href="mailto:romulomail@gmail.com">Rômulo Augusto</a>
 * 
 * @since JRimum 1.0
 * 
 * @version 1.0
 */
public class TestBoletoLinhaDigitavelDV{
	
	private AbstractDigitoVerificador dv_Validator_LinhaDigitavel;

	@Before
	public void setUp() throws Exception {
		
		dv_Validator_LinhaDigitavel = new BoletoLinhaDigitavelDV();
	}

	@Test
	public void testCalculeString() {
		
		try {
					
			dv_Validator_LinhaDigitavel.calcule(null);
			
			fail("IllegalArgumentException esperado não ocorreu.");
			assertTrue(false);
			
		}catch(IllegalArgumentException iaex) {
			
			assertTrue(true);
			System.out.println(iaex.getMessage());
		}
		
		try {
			
			dv_Validator_LinhaDigitavel.calcule("abc123");
			
			fail("IllegalArgumentException esperado não ocorreu.");
			assertTrue(false);
	
		}catch(IllegalArgumentException iaex) {
			
			assertTrue(true);
			System.out.println(iaex.getMessage());
		}
		
		try {
			
			dv_Validator_LinhaDigitavel.calcule("12345678910");
	
			fail("IllegalArgumentException esperado não ocorreu.");
			assertTrue(false);
			
		}catch(IllegalArgumentException iaex) {
			
			assertTrue(true);
			System.out.println(iaex.getMessage());
		}
		
		assertEquals(3, dv_Validator_LinhaDigitavel.calcule("999977721"));
		assertEquals(3, dv_Validator_LinhaDigitavel.calcule("99997.7721"));
		assertEquals(2, dv_Validator_LinhaDigitavel.calcule("3053015008"));
		assertEquals(2, dv_Validator_LinhaDigitavel.calcule("30530.15008"));
	}

	@Test
	public void testCalculeLong() {
		
		assertEquals(0, dv_Validator_LinhaDigitavel.calcule(0));		
		assertEquals(3, dv_Validator_LinhaDigitavel.calcule(999977721L));
		assertEquals(2, dv_Validator_LinhaDigitavel.calcule(3053015008L));
	}

}
