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

import org.junit.Before;
import org.junit.Test;

/**
 * Teste da classe <code>BoletoLinhaDigitavelDV</code>
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
public class TestBoletoLinhaDigitavelDV{
	
	private AbstractDigitoVerificador dvLinhaDigitavel;

	@Before
	public void setUp() throws Exception {
		
		dvLinhaDigitavel = new BoletoLinhaDigitavelDV();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void quandoNumeroNullDisparaExcecao() {
		dvLinhaDigitavel.calcule(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void quandoStringComLetrasDisparaExcecao() {
		dvLinhaDigitavel.calcule("1A2B3C");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void seNumeroSemPontoMenorQue9DigitosDisparaExcecao() {
		dvLinhaDigitavel.calcule("12345678");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void seNumeroComPontoMenorQue9DigitosDisparaExcecao() {
		dvLinhaDigitavel.calcule("12345.678");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void seNumeroSemPontoMaiorQue10DigitosDisparaExcecao() {
		dvLinhaDigitavel.calcule("12345678901");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void seNumeroComPontoMaiorQue10DigitosDisparaExcecao() {
		dvLinhaDigitavel.calcule("12345.678901");
	}
	
	@Test
	public void quandoNumeroNoFormatoCorretoCalculaDigitoCorretamente_String() {
		
		assertEquals(3, dvLinhaDigitavel.calcule("999977721"));
		assertEquals(3, dvLinhaDigitavel.calcule("99997.7721"));
		assertEquals(2, dvLinhaDigitavel.calcule("3053015008"));
		assertEquals(2, dvLinhaDigitavel.calcule("30530.15008"));
	}
	
	@Test
	public void quandoNumeroNoFormatoCorretoCalculaDigitoCorretamente_Long() {
		
		assertEquals(0, dvLinhaDigitavel.calcule(0));		
		assertEquals(3, dvLinhaDigitavel.calcule(999977721L));
		assertEquals(2, dvLinhaDigitavel.calcule(3053015008L));
	}
}
