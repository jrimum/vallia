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
 * Created at: 30/03/2008 - 18:19:24
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
 * Criado em: 30/03/2008 - 18:19:24
 * 
 */

package org.jrimum.vallia;

import static org.jrimum.utilix.Objects.isNotNull;

import java.io.Serializable;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.jrimum.utilix.Exceptions;
import org.jrimum.utilix.Objects;
import org.jrimum.vallia.digitoverificador.AbstractDigitoVerificador;
import org.jrimum.vallia.digitoverificador.CNPJDV;
import org.jrimum.vallia.digitoverificador.CPFDV;

/**
 * Representa a família de validadores para o cadastro de pessoa na receita
 * federal (CPRF).
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
 * 
 */
public abstract class AbstractCPRFValidator {

	/**
	 * Cadastro de pessoa para validação.
	 */
	protected StringBuilder codigoDoCadastro;

	/**
	 * Validador de dígito verificador do cadastro de Pessoa.
	 */
	protected AbstractDigitoVerificador digitoVerificador;

	/**
	 * Expressão regular para validação de CPF: "###.###.###-##" ou
	 * "###########".
	 */
	private static final String REGEX_CPF = "(\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2})|(\\d{11})";

	/**
	 * Expressão regular para validação de CNPJ: "##.###.###/####-##" ou
	 * "##############".
	 */
	private static final String REGEX_CNPJ = "(\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2})|(\\d{14})";

	/**
	 * Expressão regular para validação de um cadastro: "###" ou
	 * "##############".
	 */
	private static final String REGEX_CADASTRO = "\\d{3,14}";

	/**
	 * Representa o tipo de cadastro e fornece o autenticador correto de a cordo
	 * com este tipo.
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
	public enum TipoDeCPRF implements Serializable {

		CPF, CNPJ;

		public AbstractCPRFValidator getAutenticador() {

			AbstractCPRFValidator validador = null;

			switch (this) {

				case CPF:
	
					validador = new CPFValidator();
					validador.digitoVerificador = new CPFDV();
	
					break;
	
				case CNPJ:
	
					validador = new CNPJValidator();
					validador.digitoVerificador = new CNPJDV();
	
					break;
			}

			return validador;
		}

		/**
		 * @see org.jrimum.utilix.Objects#toString()
		 * @see java.lang.Enum#toString()
		 */
		public String toString() {
			return Objects.toString(this);
		}
	}

	/**
	 * @see org.jrimum.vallia.AbstractCPRFValidator.TipoDeCPRF
	 */
	private TipoDeCPRF tipoDeCadastro;

	/**
	 * Valida o dígito verificador do cadastro de pessoa passado durante a
	 * instanciação do validador.
	 * 
	 * @return verdadeiro se o dígito verificador for válido.
	 */
	public abstract boolean isValido();

	/**
	 * Revome a formatação existente em <code>códigoDoCadastro</code>.
	 */
	protected abstract void removeFormatacao();

	/**
	 * Recupera uma instância de um validador para o cadastro de pessoa a partir
	 * de um identificador.
	 * <p>
	 * Primeiro é feita uma pré-validação que consiste em:
	 * <ul>
	 * <li>Verificar se o parâmetro não é nulo.</li>
	 * <li>Verificar se o parâmetro não é vazio.</li>
	 * <li>Verificar se o parâmetro está em algum formatador válido para
	 * cadastro de pessoa.</li>
	 * </ul>
	 * </p>
	 * 
	 * @param codigoDoCadastro
	 *            - identificador do cadastro de pessoa.
	 * @return uma instância de <code>AbstractCPRFValidator</code>.
	 * @exception IllegalArgumentException
	 *                - caso o parâmetro não esteja em um formatador válido de
	 *                cadastro de pessoa.
	 * @since 0.2
	 */
	public static final AbstractCPRFValidator create(String codigoDoCadastro)
			throws IllegalArgumentException {

		AbstractCPRFValidator validatorCPRF = null;

		validatorCPRF = create(selectTipoDeCadastro(codigoDoCadastro));

		validatorCPRF.codigoDoCadastro = new StringBuilder(codigoDoCadastro);
		validatorCPRF.removeFormatacao();

		return validatorCPRF;
	}

	/**
	 * Cria um validador a partir do tipo de CPRF.
	 * 
	 * @param tipoDeCadastro
	 * @return um validador
	 * 
	 * @since 0.2
	 */
	public static final AbstractCPRFValidator create(TipoDeCPRF tipoDeCadastro) {

		AbstractCPRFValidator validatorCPRF = null;

		if (isNotNull(tipoDeCadastro)) {

			validatorCPRF = tipoDeCadastro.getAutenticador();

			validatorCPRF.tipoDeCadastro = tipoDeCadastro;

		} else {
			Exceptions.throwIllegalArgumentException("Tipo de Cadastro [ \""
					+ tipoDeCadastro + "\" ] nulo !");
		}

		return validatorCPRF;
	}

	/**
	 * Faz a pré-validação e se correto identifica o tipo de cadastro.
	 * 
	 * @param codigoDoCadastro
	 * @return Tipo de CPRF
	 * @throws IllegalArgumentException
	 * 
	 * @since 0.2
	 */
	private static TipoDeCPRF selectTipoDeCadastro(String codigoDoCadastro)
			throws IllegalArgumentException {

		TipoDeCPRF tipo = null;

		switch_Tipo: {

			if (StringUtils.isNotBlank(codigoDoCadastro)) {

				/*
				 * FILTRO
				 */

				if (Pattern.matches(REGEX_CPF, codigoDoCadastro)) {

					tipo = TipoDeCPRF.CPF;

					break switch_Tipo;
				}

				if (Pattern.matches(REGEX_CNPJ, codigoDoCadastro)) {

					tipo = TipoDeCPRF.CNPJ;

					break switch_Tipo;
				}

			}

			Exceptions.throwIllegalArgumentException("O código de cadastro [ \""
					+ codigoDoCadastro
					+ "\" ] não está em um formato válido !");
		}

		return tipo;
	}

	/**
	 * Define se os parâmetros válidos em relação a nulidade e formato de CPRF.
	 * 
	 * @param codigoDoCadastro
	 * @param tipoDeCadastro
	 * @return indicação de aprovação
	 * @throws IllegalArgumentException
	 * 
	 * @since 0.2
	 */
	public static final boolean isParametrosValidos(String codigoDoCadastro,
			TipoDeCPRF tipoDeCadastro) throws IllegalArgumentException {

		boolean isValido = false;

		if (isNotNull(codigoDoCadastro) && isNotNull(tipoDeCadastro)) {

			if (Pattern.matches(REGEX_CADASTRO, codigoDoCadastro)) {

				isValido = true;

			} else {

				Exceptions.throwIllegalArgumentException(
						"O cadastro está em um tamanho incorreto ou não exsite: [ \""
								+ codigoDoCadastro + "\" ]");
			}
		} else {

			Exceptions.throwIllegalArgumentException(
					"O tipo de cadastro está incorreto: [ \"" + tipoDeCadastro
							+ "\" ] ou o cadastro não exsite: [ \""
							+ codigoDoCadastro + "\" ]");
		}

		return isValido;
	}

	/**
	 * Recupera o cadastro de pessoa a ser validado. <br />
	 * Obs.: A String retornada não possui formatação, ou seja, possui apenas os
	 * dígitos.
	 * 
	 * @return cadastro de pessoa a ser validado.
	 * 
	 * @since 0.2
	 */
	public final String getCodigoDoCadastro() {

		return codigoDoCadastro.toString();
	}

	/**
	 * Indica se o validador é de pessoa física.
	 * 
	 * @return verdadeiro se for de pessoa física.
	 * @since 0.2
	 */
	public final boolean isFisica() {

		return this instanceof CPFValidator;
	}

	/**
	 * Indica se o validador é de pessoa jurídica.
	 * 
	 * @return verdadeiro se for de pessoa jurídica.
	 * @since 0.2
	 */
	public final boolean isJuridica() {

		return this instanceof CNPJValidator;
	}
	
	/**
	 * @return the tipoDeCadastro
	 */
	public final TipoDeCPRF getTipoDeCadastro() {
		return tipoDeCadastro;
	}
}
