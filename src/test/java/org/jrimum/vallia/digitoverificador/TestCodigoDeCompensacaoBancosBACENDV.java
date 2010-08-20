/* 
 * Copyright 2008 JRimum Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * Created at: 15/06/2008 - 12:00:00
 *
 * ================================================================================
 *
 * Direitos autorais 2008 JRimum Project
 *
 * Licenciado sob a Licença Apache, Versão 2.0 ("LICENÇA"); você não pode 
 * usar esse arquivo exceto em conformidade com a esta LICENÇA. Você pode obter uma 
 * cópia desta LICENÇA em http://www.apache.org/licenses/LICENSE-2.0 A menos que 
 * haja exigência legal ou acordo por escrito, a distribuição de software sob esta 
 * LICENÇA se dará “COMO ESTÁ”, SEM GARANTIAS OU CONDIÇÕES DE QUALQUER TIPO, sejam 
 * expressas ou tácitas. Veja a LICENÇA para a redação específica a reger permissões 
 * e limitações sob esta LICENÇA.
 * 
 * Criado em: 15/06/2008 - 12:00:00
 * 
 */

package org.jrimum.vallia.digitoverificador;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang.StringUtils;
import org.jrimum.utilix.text.Strings;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;



/**
 * 
 * <p>
 * Testa o calculo dos códigos de compensação dos bancos.
 * </p>
 * 
 * @author <a href="http://gilmatryx.googlepages.com">Gilmar P.S.L.</a>
 * @author <a href="mailto:misaelbarreto@gmail.com">Misael Barreto</a>
 * 
 * @since 0.2
 * 
 * @version 0.2
 */
	
public class TestCodigoDeCompensacaoBancosBACENDV {

	private CodigoDeCompensacaoBancosBACENDV dv4CodigoCompensacaoBanco = null;
	
	@Before
	public void setUp() throws Exception {
		dv4CodigoCompensacaoBanco = new CodigoDeCompensacaoBancosBACENDV();
	}	
		
	@After
	public void tearDown() throws Exception {
		dv4CodigoCompensacaoBanco = null;
	}	
		
	@Test
	public void testCalculeString() {
		
		assertEquals(9, dv4CodigoCompensacaoBanco.calcule("001"));//BancoDoBrasil
		assertEquals(0, dv4CodigoCompensacaoBanco.calcule("104"));//CaixaEconomicaFederal
		assertEquals(2, dv4CodigoCompensacaoBanco.calcule("237"));//Bradesco
		assertEquals(7, dv4CodigoCompensacaoBanco.calcule("341"));//Itau
		assertEquals(5, dv4CodigoCompensacaoBanco.calcule("356"));//BancoReal
		assertEquals(0, dv4CodigoCompensacaoBanco.calcule("409"));//Unibanco
		assertEquals(7, dv4CodigoCompensacaoBanco.calcule("422"));//BancoSafra
		assertEquals(0, dv4CodigoCompensacaoBanco.calcule("748"));//Sicredi
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCalculeStringNull() {
		
		String codigo = null;
		dv4CodigoCompensacaoBanco.calcule(codigo);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCalculeStringEmpty() {
		dv4CodigoCompensacaoBanco.calcule(StringUtils.EMPTY);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCalculeStringBlank() {
		dv4CodigoCompensacaoBanco.calcule(Strings.WHITE_SPACE);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCalculeStringNaoNumerico() {
		dv4CodigoCompensacaoBanco.calcule("abc");
	}

	@Test
	public void testCalculeLong() {
		
		assertEquals(9, dv4CodigoCompensacaoBanco.calcule(1L));//BancoDoBrasil
		assertEquals(0, dv4CodigoCompensacaoBanco.calcule(104L));//CaixaEconomicaFederal
		assertEquals(2, dv4CodigoCompensacaoBanco.calcule(237L));//Bradesco
		assertEquals(7, dv4CodigoCompensacaoBanco.calcule(341L));//Itau
		assertEquals(5, dv4CodigoCompensacaoBanco.calcule(356L));//BancoReal
		assertEquals(0, dv4CodigoCompensacaoBanco.calcule(409L));//Unibanco
		assertEquals(7, dv4CodigoCompensacaoBanco.calcule(422L));//BancoSafra
		assertEquals(0, dv4CodigoCompensacaoBanco.calcule(748L));//Sicredi
	}

	@Test
	public void testCalculeInt() {
		
		assertEquals(9, dv4CodigoCompensacaoBanco.calcule(1));//BancoDoBrasil
		assertEquals(0, dv4CodigoCompensacaoBanco.calcule(104));//CaixaEconomicaFederal
		assertEquals(2, dv4CodigoCompensacaoBanco.calcule(237));//Bradesco
		assertEquals(7, dv4CodigoCompensacaoBanco.calcule(341));//Itau
		assertEquals(5, dv4CodigoCompensacaoBanco.calcule(356));//BancoReal
		assertEquals(0, dv4CodigoCompensacaoBanco.calcule(409));//Unibanco
		assertEquals(7, dv4CodigoCompensacaoBanco.calcule(422));//BancoSafra
		assertEquals(0, dv4CodigoCompensacaoBanco.calcule(748));//Sicredi
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCalculeCodigoDeCompensacaoMenorQueLimiteMinimo() {
		dv4CodigoCompensacaoBanco.calcule(CodigoDeCompensacaoBancosBACENDV.LIMITE_MINIMO - 1);
	}	

	@Test(expected=IllegalArgumentException.class)
	public void testCalculeCodigoDeCompensacaoMaiorQueLimiteMaximo() {
		dv4CodigoCompensacaoBanco.calcule(CodigoDeCompensacaoBancosBACENDV.LIMITE_MAXIMO + 1);
	}
	
	@Test
	public void testIsCodigoValidoString() {
		assertTrue(dv4CodigoCompensacaoBanco.isCodigoValido("001"));
	}

	@Test
	public void testIsCodigoValidoStringNull() {
		
		String codigo = null;
		assertFalse(dv4CodigoCompensacaoBanco.isCodigoValido(codigo));
	}
	
	@Test
	public void testIsCodigoValidoStringEmpty() {
		assertFalse(dv4CodigoCompensacaoBanco.isCodigoValido(StringUtils.EMPTY));
	}
	
	@Test
	public void testIsCodigoValidoStringBlank() {
		assertFalse(dv4CodigoCompensacaoBanco.isCodigoValido(Strings.WHITE_SPACE));
	}
	
	@Test
	public void testIsCodigoValidoStringNaoNumerico() {
		assertFalse(dv4CodigoCompensacaoBanco.isCodigoValido("abc"));
	}
	
	@Test
	public void testIsCodigoValidoStringMenorQueLimiteMinimo() {
		assertFalse(dv4CodigoCompensacaoBanco.isCodigoValido("000"));
	}
	
	@Test
	public void testIsCodigoValidoStringMaiorQueLimiteMaximo() {
		assertFalse(dv4CodigoCompensacaoBanco.isCodigoValido("1000"));
	}
	
	@Test
	public void testIsCodigoValidoInt() {
		assertTrue(dv4CodigoCompensacaoBanco.isCodigoValido(1));
	}

	@Test
	public void testIsCodigoValidoIntMenorQueLimiteMinimo() {
		assertFalse(dv4CodigoCompensacaoBanco.isCodigoValido(0));
	}
	
	@Test
	public void testIsCodigoValidoIntMaiorQueLimiteMaximo() {
		assertFalse(dv4CodigoCompensacaoBanco.isCodigoValido(1000));
	}
	
	@Test
	public void testIsCodigoValidoLong() {
		assertTrue(dv4CodigoCompensacaoBanco.isCodigoValido(1L));
	}

	@Test
	public void testIsCodigoValidoLongMenorQueLimiteMinimo() {
		assertFalse(dv4CodigoCompensacaoBanco.isCodigoValido(0L));
	}
	
	@Test
	public void testIsCodigoValidoLongMaiorQueLimiteMaximo() {
		assertFalse(dv4CodigoCompensacaoBanco.isCodigoValido(1000L));
	}
}
