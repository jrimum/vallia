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
 * Created at / Criado em : 17/03/2007 - 17:42:57
 * 
 */

package br.com.nordestefomento.jrimum.vallia.digitoverificador;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import br.com.nordestefomento.jrimum.utilix.Filler;
import br.com.nordestefomento.jrimum.utilix.Util4String;


/**
 * 
 * O cálculo do dígito verificador do CNPJ é realizado em duas etapas e é
 * auxiliado pela rotina de módulo 11. <br />
 * Abaixo é demonstrado como esse cálculo é feito:
 * 
 * <h3>Exemplo para um número hipotético 11.222.333/0001-XX:</h3>
 * <p>
 * Primeiramente obtém-se um número R, calculado através da rotina de módulo 11,
 * a partir dos doze primeiros números do CNPJ, nesse caso 112223330001. <br />
 * Para obter o primeiro dígito verificador deve-se seguir a seguinte lógica:
 * <br />
 * <br />
 * Se o número R for menor que 2, o dígito terá valor 0 (zero); senão, será a
 * subtração do valor do módulo (11) menos o valor do número R, ou seja,
 * <code>DV = 11 - R</code>.
 * </p>
 * <p>
 * Para obter o segundo dígito verificador é da mesma forma do primeiro, porém
 * deve ser calculado a partir dos treze primeiros números do CNPJ, ou seja,
 * 112223330001 + primeiro dígito.
 * </p>
 * <p>
 * Obs.: O limite mínimo e máximo do módulo 11 são 2 e 9, respectivamente.
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
public class DV4CNPJ extends ADigitoVerificador {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4702398145481452503L;

	/**
	 * Expressão regular para validação dos doze primeiros números do CNPJ sem
	 * formatação: "############".
	 */
	private static final String REGEX_CNPJ_DV = "\\d{12}";

	/**
	 * Expressão regular para validação dos doze primeiros números do CNPJ
	 * formatado: "##.###.###/####".
	 */
	private static final String REGEX_CNPJ_DV_FORMATED = "\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}";

	/**
	 * @see br.com.nordestefomento.jrimum.vallia.digitoverificador.ADigitoVerificador#calcular(long)
	 */
	@Override
	public int calcular(long numero) {

		return calcular(Util4String.complete_x(numero, Filler.LONG_ZERO_LEFT, 12));
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

				isFormatoValido = (Pattern.matches(REGEX_CNPJ_DV, numero) || Pattern
						.matches(REGEX_CNPJ_DV_FORMATED, numero));

				if (isFormatoValido) {

					numero = StringUtils.replaceChars(numero, ".", "");
					numero = StringUtils.replaceChars(numero, "/", "");

					dv1 = calcularDigito(numero);
					dv2 = calcularDigito(numero + dv1);

					break validacao;
				}
			}

			throw new IllegalArgumentException(
				"O CNPJ [ "+numero+" ] deve conter apenas números, sendo eles no formatador ##.###.###/#### ou ############ !");
			
		}
		
		return Integer.parseInt(dv1 + "" + dv2);
		
	}

	/**
	 * Método auxiliar para o cálculo do dígito verificador. <br />
	 * Calcula os dígitos separadamente.
	 * 
	 * @param numero -
	 *            número a partir do qual será extraído o dígito verificador.
	 * @return um número que faz parte de um dígito verificador.
	 * @throws IllegalArgumentException
	 *             caso o número não esteja no formatador desejável.
	 */
	private int calcularDigito(String numero) throws IllegalArgumentException {

		int dv = 0;
		int resto = 0;
		AModulo modulo = AModulo.getInstance(EnumModulo.MODULO_11);

		resto = modulo.calcular(numero);

		if (resto >= 2) {

			dv = modulo.getValor() - resto;
		}

		return dv;
	}

}
