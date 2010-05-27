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
 * Created at: 30/03/2008 - 18:22:21
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
 * Criado em: 30/03/2008 - 18:22:21
 * 
 */

package br.com.nordestefomento.jrimum.vallia.digitoverificador;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import br.com.nordestefomento.jrimum.utilix.Filler;


public class GuiaLinhaDigitavelDV extends AbstractDigitoVerificador {

	private static final long serialVersionUID = -9177413216786384292L;

	/**
	 *<p>
	 * Módulo 10 utilizado no cálculo.
	 * </p>
	 */
	private static Modulo modulo;

	/**
	 * <p>
	 * Expressão regular para validação do campo da linha digitável, aceita os
	 * seguintes formatos:
	 * </p>
	 * <ul type="circle"> <li><tt>#########</tt></li> <li><tt>#####.####</tt></li>
	 * <li><tt>##########</tt></li> <li><tt>#####.#####</tt></li> </ul>
	 * 
	 */
	private static final String REGEX_CAMPO = "(\\d{9})|(\\d{10})|(\\d{5})\\.(\\d{4})|(\\d{5})\\.(\\d{5})";

	/**
	 *<p>
	 * Construtor vaizo, o mesmo que <tt>super()</tt>.
	 * </p>
	 * 
	 * @since 0.3
	 */
	public GuiaLinhaDigitavelDV(Modulo modulo) {
		super();
		this.modulo = modulo;
	}

	/**
	 * @see br.com.nordestefomento.jrimum.vallia.digitoverificador.AbstractDigitoVerificador#calcule(java.lang.String)
	 * @since 0.3
	 */
	@Override
	public int calcule(long numero) {

		return calcule(Filler.ZERO_LEFT.fill(String.valueOf(numero), 10));
	}

	/**
	 * @see br.com.nordestefomento.jrimum.vallia.digitoverificador.AbstractDigitoVerificador#calcule(java.lang.String)
	 * @since 0.3
	 */
	@Override
	public int calcule(String numero) throws IllegalArgumentException {

		int dv = 0;
		int resto = 0;

		if (StringUtils.isNotBlank(numero)
				&& Pattern.matches(REGEX_CAMPO, numero)) {

			numero = StringUtils.replaceChars(numero, ".", "");

			resto = modulo.calcule(numero);

			if (resto != 0)
				dv = modulo.valor() - resto;
		} else
			throw new IllegalArgumentException(
					"O campo [ "
							+ numero
							+ " ] da linha digitável deve conter apenas números com 9 ou 10 dígitos !");

		return dv;
	}

}
