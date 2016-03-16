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
 * Created at: 30/03/2008 - 18:52:37
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
 * Criado em: 30/03/2008 - 18:52:37
 * 
 */

package org.jrimum.vallia;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Teste da classe <code>Validator_CadastroDePessoaJurídica</code>.
 * 
 * 
 * @author Gabriel Guimarães
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 * @author <a href="mailto:misaelbarreto@gmail.com">Misael Barreto</a>
 * @author <a href="mailto:romulomail@gmail.com">Rômulo Augusto</a>
 * 		
 * @since 0.2
 * 		
 * @version 0.2
 */
public class TestCNPJValidator {
	
	private AbstractCPRFValidator validadorCNPJ;
	
	@Test
	public void test14DigitosValidos() {
		validadorCNPJ = AbstractCPRFValidator.create("11222333000181");
		assertTrue(validadorCNPJ.isValido());
	}
	
	@Test
	public void test14DigitosInvalidos() {
		validadorCNPJ = AbstractCPRFValidator.create("11222333000182");
		assertFalse(validadorCNPJ.isValido());
	}
	
	@Test
	public void test15DigitosValidos() {
		validadorCNPJ = AbstractCPRFValidator.create("123456789000130");
		assertTrue(validadorCNPJ.isValido());
	}
	
	@Test
	public void test15DigitosInvalidos() {
		validadorCNPJ = AbstractCPRFValidator.create("123456789000131");
		assertFalse(validadorCNPJ.isValido());
	}
	
}
