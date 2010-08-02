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
 * Created at: 30/03/2008 - 18:51:09
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
 * Criado em: 30/03/2008 - 18:51:09
 * 
 */

package org.jrimum.vallia.digitoverificador;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.jrimum.utilix.text.Filler;

/**
 * <p>
 * O cálculo do dígito verificador do CPF é realizado em duas etapas e é
 * auxiliado pela rotina de módulo 11.
 * </p>
 * <p>
 * Abaixo é demonstrado como esse cálculo é feito:
 * </p>
 * <h3>Exemplo para um número hipotético 222.333.666-XX:</h3>
 * <p>
 * Primeiramente obtém-se um número R, calculado através da rotina de módulo 11,
 * a partir dos nove primeiros números do CPF, nesse caso 222333666. <br />
 * Para obter o primeiro dígito verificador deve-se seguir a seguinte lógica: <br />
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
 * @see Modulo
 * 
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L</a>
 * @author <a href="mailto:misaelbarreto@gmail.com">Misael Barreto</a>
 * @author <a href="mailto:romulomail@gmail.com">Rômulo Augusto</a>
 * @author <a href="http://www.nordestefomento.com.br">Nordeste Fomento
 *         Mercantil</a>
 * 
 * @since 0.2
 * 
 * @version 0.2
 */
public class CPFDV extends AbstractDigitoVerificador {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2059692008894172695L;

	/**
	 * <p>
	 * Liminte mínimo do para cálculo no módulo 11.
	 *</p>
	 */
	private static final int LIMITE_MINIMO = 2;

	/**
	 * <p>
	 * Expressão regular para validação dos nove primeiros números do CPF sem
	 * formatação: <tt>"#########"</tt>.
	 * </p>
	 */
	private static final String REGEX_CPF_DV = "\\d{9}";

	/**
	 * <p>
	 * Expressão regular para validação dos nove primeiros números do CPF
	 * formatado: <tt>"###.###.###"</tt>.
	 * </p>
	 */
	private static final String REGEX_CPF_DV_FORMATTED = "\\d{3}\\.\\d{3}\\.\\d{3}";

	/**
	 * @see org.jrimum.vallia.digitoverificador.AbstractDigitoVerificador#calcule(long)
	 * @since 0.2
	 */
	@Override
	public int calcule(long numero) {

		return calcule(Filler.ZERO_LEFT.fill(String.valueOf(numero), 9));
	}

	/**
	 * @see org.jrimum.vallia.digitoverificador.AbstractDigitoVerificador#calcule(java.lang.String)
	 * @since 0.2
	 */
	@Override
	public int calcule(String numero) throws IllegalArgumentException {

		int dv1 = 0;
		int dv2 = 0;
		
		numero = removaFormatacao(numero);

		if (isFormatoValido(numero)) {

			dv1 = calcule(numero, 10);
			dv2 = calcule(numero + dv1, 11);
			
		} else {

			throw new IllegalArgumentException("O CPF [ " + numero
						+ " ] deve conter apenas números, sendo eles no formato ###.###.### ou ######### !");
		}

		return Integer.parseInt(dv1 + "" + dv2);

	}
	
	/**
	 * Método null-safe que remove a formatação da String, com a intenção de deixar
	 * apenas números.
	 * 
	 * @param numero - CNPJ que pode estar formatado.
	 * @return Número CNPJ sem formatação.
	 */
	private String removaFormatacao(String numero) {
		
		numero = StringUtils.replaceChars(numero, ".", "");
		
		return numero;
	}
	
	/**
	 * <p>
	 * Verifica se o número passado está em um formato aceitável para a realização do cálculo,
	 * ou seja:
	 * </p>
	 * <ul>
	 * 	<li>Não é null</li>
	 * 	<li>Não é vazio</li>
	 *  <li>Apenas números</li>
	 *  <li>Não é somente zeros</li>
	 *  <li>Está no formato ##.###.###/#### ou ############</li>
	 * </ul>
	 * 
	 * @param numero - CNPJ para ser validado
	 * @return <code>true</code> caso o número esteja em um formato válido; <code>false</code>, 
	 * caso contrário.
	 */
	private boolean isFormatoValido(String numero) {
		
		boolean isValido = false;
		
		if (StringUtils.isNotBlank(numero)) {
			
			boolean formatoValido = (Pattern.matches(REGEX_CPF_DV, numero) || Pattern.matches(REGEX_CPF_DV_FORMATTED, numero));

			if (formatoValido) {
				
				isValido = Long.parseLong(numero) > 0;
			}
		}
		
		return isValido;
	}

	/**
	 * <p>
	 * Método auxiliar para o cálculo do dígito verificador.
	 * </p>
	 * <p>
	 * Calcula os dígitos separadamente.
	 * </p>
	 * 
	 * @param numero
	 *            - número a partir do qual será extraído o dígito verificador.
	 * @param limiteMaximoDoModulo
	 *            - limite máximo do módulo utilizado, no caso, módulo 11.
	 * @return um número que faz parte de um dígito verificador.
	 * @throws IllegalArgumentException
	 *             caso o número não esteja no formatador desejável.
	 * @since 0.2
	 */
	private int calcule(String numero, int limiteMaximoDoModulo)
			throws IllegalArgumentException {

		int dv = 0;
		int resto = 0;

		resto = Modulo
				.calculeMod11(numero, LIMITE_MINIMO, limiteMaximoDoModulo);

		if (resto >= 2) {

			dv = TipoDeModulo.MODULO11.valor() - resto;
		}

		return dv;
	}
}
