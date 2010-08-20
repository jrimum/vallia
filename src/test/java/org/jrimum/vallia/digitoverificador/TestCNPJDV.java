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
 * Created at: 30/03/2008 - 18:53:39
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
 * Criado em: 30/03/2008 - 18:53:39
 * 
 */

package org.jrimum.vallia.digitoverificador;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Teste da classe CNPJDV.
 * 
 * @author Gabriel Guimarães
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a> 
 * @author <a href="mailto:misaelbarreto@gmail.com">Misael Barreto</a>
 * @author <a href="mailto:romulomail@gmail.com">Rômulo Augusto</a>
 * 
 * @since JRimum 1.0
 * 
 * @version 1.0
 * 
 */
public class TestCNPJDV{

	private CNPJDV dvCNPJ;

	@Before
	public void setUp() throws Exception {

		dvCNPJ = new CNPJDV();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void quandoNumeroNullDisparaExcecao() {
		dvCNPJ.calcule(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void quandoNumeroEmBrancoDisparaExcecao() {
		dvCNPJ.calcule("  ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void quandoStringComLetrasDisparaExcecao() {
		dvCNPJ.calcule("1A2B3C");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void quandoNumeroApenasZerosDisparaExcecao() {
		dvCNPJ.calcule("000000000000");
	}
	
	@Test
	public void quandoNumeroNoFormatoCorretoSemFormatacaoCalculaCorretamente() {
		assertEquals(81, dvCNPJ.calcule("112223330001"));
	}

	@Test
	public void quandoNumeroNoFormatoCorretoComFormatacaoCalculaCorretamente() {
		assertEquals(81, dvCNPJ.calcule("11.222.333/0001"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void quandoNumeroLongMaiorQue12DigitosDisparaExcecao() {
		dvCNPJ.calcule(1112223330001L);
	}

	@Test
	public void quandoNumeroLongMenorQue12DigitosCalculaCorretamente() {

		assertEquals(81, dvCNPJ.calcule(112223330001L));
		assertEquals(65, dvCNPJ.calcule(2223330001L));
	}
}
