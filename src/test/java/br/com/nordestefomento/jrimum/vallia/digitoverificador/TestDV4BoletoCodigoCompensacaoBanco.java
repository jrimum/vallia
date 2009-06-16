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

package br.com.nordestefomento.jrimum.vallia.digitoverificador;

import static org.junit.Assert.assertEquals;

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
 * @author Misael
 * 
 * @since 0.2
 * 
 * @version 0.2
 */
	
public class TestDV4BoletoCodigoCompensacaoBanco {

	private final int CODIGO_COMPENSACAO_LIMITE_MINIMO = 1; 
	private final int CODIGO_COMPENSACAO_LIMITE_MAXIMO = 999; 
	
	private DV4CodigoDeCompensacaoBancosBACEN dv4CodigoCompensacaoBanco = null;
	
	@Before
	public void setUp() throws Exception {
		dv4CodigoCompensacaoBanco = new DV4CodigoDeCompensacaoBancosBACEN();
	}	
		
	@After
	public void tearDown() throws Exception {
		dv4CodigoCompensacaoBanco = null;
	}	
		
	@Test
	public final void testCalculeString() {
		
		assertEquals(9, dv4CodigoCompensacaoBanco.calcule("001"));//BancoDoBrasil
		assertEquals(0, dv4CodigoCompensacaoBanco.calcule("104"));//CaixaEconomicaFederal
		assertEquals(2, dv4CodigoCompensacaoBanco.calcule("237"));//Bradesco
		assertEquals(7, dv4CodigoCompensacaoBanco.calcule("341"));//Itau
		assertEquals(5, dv4CodigoCompensacaoBanco.calcule("356"));//BancoReal
		assertEquals(0, dv4CodigoCompensacaoBanco.calcule("409"));//Unibanco
		assertEquals(7, dv4CodigoCompensacaoBanco.calcule("422"));//BancoSafra
		assertEquals(0, dv4CodigoCompensacaoBanco.calcule("748"));//Sicredi
		
	}

	@Test
	public final void testCalculeLong() {
		
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
	public final void testCalculeCodigoDeCompensacaoMenorQueLimiteMinimo() {
		dv4CodigoCompensacaoBanco.calcule(CODIGO_COMPENSACAO_LIMITE_MINIMO - 1);
	}	

	@Test(expected=IllegalArgumentException.class)
	public final void testCalculeCodigoDeCompensacaoMaiorQueLimiteMaximo() {
		dv4CodigoCompensacaoBanco.calcule(CODIGO_COMPENSACAO_LIMITE_MAXIMO + 1);
	}	
}
