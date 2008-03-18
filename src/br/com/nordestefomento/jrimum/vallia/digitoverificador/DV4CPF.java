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
 * Created at / Criado em : 17/03/2007 - 17:42:50
 * 
 */

package br.com.nordestefomento.jrimum.vallia.digitoverificador;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import br.com.nordestefomento.jrimum.utilix.Filler;
import br.com.nordestefomento.jrimum.utilix.Operator4String;


/**
 * 
 * O cálculo do dígito verificador do CPF é realizado em duas etapas e é
 * auxiliado pela rotina de módulo 11. <br />
 * Abaixo é demonstrado como esse cálculo é feito:
 * 
 * <h3>Exemplo para um número hipotético 222.333.666-XX:</h3>
 * <p>
 * Primeiramente obtém-se um número R, calculado através da rotina de módulo 11,
 * a partir dos nove primeiros números do CPF, nesse caso 222333666. <br />
 * Para obter o primeiro dígito verificador deve-se seguir a seguinte lógica:
 * <br />
 * <br />
 * Se o número R for menor que 2, o dígito terá valor 0 (zero); senão, será a
 * subtração do valor do módulo (11) menos o valor do número R, ou seja,
 * <code>DV = 11 - R</code>.
 * </p>
 * <p>
 * Para obter o segundo dígito verificador é da mesma forma do primeiro, porém
 * deve ser calculado a partir dos dez primeiros números do CPF, ou seja,
 * 222333666 + primeiro dígito.
 * </p>
 * <p>
 * Obs.: Os limites mínimos e máximos do módulo 11 para o cálculo do primeiro e
 * do segundo dígito verificador são 2 - 10 e 2 e 11, respectivamente.
 * </p>
 * 
 * @see Modulo11
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
public class DV4CPF extends ADigitoVerificador {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2059692008894172695L;

	/**
	 * Expressão regular para validação dos nove primeiros números do CPF sem
	 * formatação: "#########".
	 */
	private static final String REGEX_CPF_DV = "\\d{9}";

	/**
	 * Expressão regular para validação dos nove primeiros números do CPF
	 * formatado: "###.###.###".
	 */
	private static final String REGEX_CPF_DV_FORMATED = "\\d{3}\\.\\d{3}\\.\\d{3}";

	/**
	 * @see br.com.nordestefomento.jrimum.vallia.digitoverificador.ADigitoVerificador#calcular(long)
	 */
	@Override
	public int calcular(long numero) {

		return calcular(Operator4String.complete_x(numero, Filler.LONG_ZERO_LEFT, 9));
	}

	/**
	 * @see br.com.nordestefomento.jrimum.vallia.digitoverificador.ADigitoVerificador#calcular(java.lang.String)
	 */
	@Override
	public int calcular(String numero) throws IllegalArgumentException {

		int dv1 = 0;
		int dv2 = 0;
		boolean isFormatoValido = false;

		validacao: {

			if (StringUtils.isNotBlank(numero)) {

				isFormatoValido = (Pattern.matches(REGEX_CPF_DV, numero) || Pattern
						.matches(REGEX_CPF_DV_FORMATED, numero));

				if (isFormatoValido) {

					numero = StringUtils.replaceChars(numero, ".", "");

					dv1 = calcular(numero, 10);
					dv2 = calcular(numero + dv1, 11);

					break validacao;
				}
			}

			throw new IllegalArgumentException(
					"O CPF [ "+numero+" ] deve conter apenas números, sendo eles no formatador ###.###.### ou ######### !");

		}

		return Integer.parseInt(dv1 + "" + dv2);

	}

	/**
	 * Método auxiliar para o cálculo do dígito verificador. <br />
	 * Calcula os dígitos separadamente.
	 * 
	 * @param numero -
	 *            número a partir do qual será extraído o dígito verificador.
	 * @param limiteMaximoDoModulo -
	 *            limite máximo do módulo utilizado, no caso, módulo 11.
	 * @return um número que faz parte de um dígito verificador.
	 * @throws IllegalArgumentException
	 *             caso o número não esteja no formatador desejável.
	 */
	private int calcular(String numero, int limiteMaximoDoModulo)
			throws IllegalArgumentException {

		int dv = 0;
		int resto = 0;
		AModulo modulo = AModulo.getInstance(EnumModulo.MODULO_11);

		modulo.setLimiteMaximo(limiteMaximoDoModulo);
		resto = modulo.calcular(numero);

		if (resto >= 2) {

			dv = modulo.getValor() - resto;
		}

		return dv;
	}
}
