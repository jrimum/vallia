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
 * Created at / Criado em : 17/03/2007 - 17:42:28
 * 
 */

package br.com.nordestefomento.jrimum.vallia.digitoverificador;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import br.com.nordestefomento.jrimum.utilix.Filler;
import br.com.nordestefomento.jrimum.utilix.Operator4String;


/**
 * <p>
 * Segundo o padrão FEBRABAN a linha digitável possui cinco campos, dos quais apenas os três primeiros necessitam de 
 * validação com dígito verificador.
 * </p>
 * <p>
 * Para fins de validação é preciso saber o seguinte:
 * <ul>
 * <li>O primerio campo tem tamanho 9 (nove) mais o dígito verificador.</li>
 * <li>O segundo campo tem tamanho 10 (dez) mais o dígito verificador.</li>
 * <li>O terceiro campo tem tamanho 10 (dez) mais o dígito verificador.</li>
 * </ul>
 * </p>
 * <p>
 * Obs1.: Todos os campos listados podem vir com um ponto (.) de separação exatamente após o dígito da 5ª posição.
 * <br />
 * Exemplo de linha digitável:<br />
 * <code>99997.77213 30530.150082 18975.000003 1 10010000035000</code>
 * </p>
 * 
 * <p>
 * O cálculo do dígito verificador é descrito através da expressão <code>DV = 11 - R</code>, 
 * onde R é o resultado do cálculo do módulo.<br />
 * Obs1.: O dígito verificador será 0 (zero) se o resto (resultado do módulo) for 0 (zero).
 * <br />
 * Obs2.: A rotina de módulo utilizada é a módulo 10.
 * </p>
 *  
 * @author Gabriel Guimarães
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L</a>
 * @author Misael Barreto 
 * @author Rômulo Augusto
 * @author <a href="http://www.nordeste-fomento.com.br">Nordeste Fomento Mercantil</a>
 * 
 * @since JMatryx 1.0
 * 
 * @version 1.0
 */
public class DV4BoletoLinhaDigitavel extends ADigitoVerificador {

	
	private static final long serialVersionUID = -9177413216786384292L;
	
	/**
	 * Expressão regular para validação do campo da linha digitável.<br />
	 * 
	 * Pode estar nos seguintes formatos: #########, #####.####, ##########, #####.#####.
	 */
	private static final String REGEX_CAMPO = "(\\d{9})|(\\d{10})|(\\d{5})\\.(\\d{4})|(\\d{5})\\.(\\d{5})";

	public DV4BoletoLinhaDigitavel() {
		super();
	}
	
	/**
	 * @see br.com.nordestefomento.jrimum.vallia.digitoverificador.ADigitoVerificador#calcular(java.lang.String)
	 */
	@Override
	public int calcular(long numero) {
		
		return calcular(Operator4String.complete_x(numero, Filler.LONG_ZERO_LEFT, 10));
	}

	/**
	 * @see br.com.nordestefomento.jrimum.vallia.digitoverificador.ADigitoVerificador#calcular(java.lang.String)
	 */
	@Override
	public int calcular(String numero) throws IllegalArgumentException {
		
		int dv = 0;
		int resto = 0;
		
		AModulo aModulo = AModulo.getInstance(EnumModulo.MODULO_10);
		
		if(StringUtils.isNotBlank(numero) && Pattern.matches(REGEX_CAMPO, numero)) {
		
			numero = StringUtils.replaceChars(numero, ".", "");
			
			resto = aModulo.calcular(numero);

			if(resto != 0)			
				dv = aModulo.getValor() - resto;
		}
		else
			throw new IllegalArgumentException("O campo [ "+numero+" ] da linha digitável deve conter apenas números com 9 ou 10 dígitos !");
		
		return dv;
	}

}
