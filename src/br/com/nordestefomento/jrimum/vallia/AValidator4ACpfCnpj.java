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


package br.com.nordestefomento.jrimum.vallia;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import br.com.nordestefomento.jrimum.ACurbitaObject;
import br.com.nordestefomento.jrimum.vallia.digitoverificador.ADigitoVerificador;
import br.com.nordestefomento.jrimum.vallia.digitoverificador.DV4CNPJ;
import br.com.nordestefomento.jrimum.vallia.digitoverificador.DV4CPF;


/**
 * 
 * Representa a família de validadores para o cadastro de pessoa.
 * 
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
public abstract class AValidator4ACpfCnpj extends ACurbitaObject {
	
	/**
	 * Cadastro de pessoa para validação.
	 */
	protected StringBuilder codigoDoCadastro;
	
	/**
	 * Validador de dígito verificador do cadastro de Pessoa.
	 */
	protected ADigitoVerificador digitoVerificador;

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
	 * Expressão regular para validação de um cadastro: "###" ou "##############".
	 */
	private static final String REGEX_CADASTRO = "\\d{3,14}";

	/**
	 * 
	 * Representa o tipo de cadastro e fornece o autenticador correto de a cordo
	 * com este tipo.
	 * 
	 * 
	 * @author Gabriel Guimarães
	 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L</a>
	 * @author Misael Barreto
	 * @author Rômulo Augusto
	 * @author <a href="http://www.nordeste-fomento.com.br">Nordeste Fomento
	 *         Mercantil</a>
	 * 
	 * @since JMatryx 1.0
	 * 
	 * @version 1.0
	 */
	public enum EnumCadastroDePessoa {

		CPF, CNPJ;

		public AValidator4ACpfCnpj getAutenticador() {

			AValidator4ACpfCnpj validador = null;

			switch (this) {
			
			case CPF:
				
				validador = new Validator4CPF();
				validador.digitoVerificador = new DV4CPF();
				
				break;

			case CNPJ:
				
				validador = new Validator4CNPJ();
				validador.digitoVerificador = new DV4CNPJ();
				
				break;
			}

			return validador;
		}
	}

	/**
	 * @see br.com.nordestefomento.jrimum.vallia.AValidator4ACpfCnpj.EnumCadastroDePessoa
	 */
	private EnumCadastroDePessoa tipoDeCadastro;

	/**
	 * Valida o dígito verificador do cadastro de pessoa passado durante a
	 * instanciação do validador.
	 * 
	 * @return verdadeiro se o dígito verificador for válido.
	 * 
	 * @see AValidator4ACpfCnpj.getInstance(java.lang.String)
	 */
	public abstract boolean isValido();
	
	/**
	 * Revome a formatação existente em
	 * <code>códigoDoCadastro</code>.
	 * 
	 */
	protected abstract void removeFormatacao();

	/**
	 * <p>
	 * Recupera uma instância de um validador para o cadastro de pessoa a partir
	 * de um identificador.
	 * </p>
	 * <p>
	 * Primeiro é feita uma pré-validação que consiste em:
	 * <ul>
	 * <li>Verificar se o parâmetro não é nulo.</li>
	 * <li>Verificar se o parâmetro não é vazio.</li>
	 * <li>Verificar se o parâmetro está em algum formatador válido para cadastro
	 * de pessoa.</li>
	 * </ul>
	 * </p>
	 * 
	 * @param codigoDoCadastro -
	 *            identificador do cadastro de pessoa.
	 * @return uma instância de <code>AValidator4ACpfCnpj</code>.
	 * @exception IllegalArgumentException -
	 *                caso o parâmetro não esteja em um formatador válido de
	 *                cadastro de pessoa.
	 */
	public static AValidator4ACpfCnpj getInstance(
			String codigoDoCadastro) throws IllegalArgumentException {

		AValidator4ACpfCnpj validator_A_CP = null;

		validator_A_CP = getInstance(selectTipoDeCadastro(codigoDoCadastro));	
		
		validator_A_CP.codigoDoCadastro = new StringBuilder (codigoDoCadastro);
		validator_A_CP.removeFormatacao();

		return validator_A_CP;
	}

	public static AValidator4ACpfCnpj getInstance(EnumCadastroDePessoa tipoDeCadastro){
		
		AValidator4ACpfCnpj validator_A_CP = null;

		if(tipoDeCadastro != null){
			
			validator_A_CP = tipoDeCadastro.getAutenticador();
			
			validator_A_CP.tipoDeCadastro = tipoDeCadastro;
			
		}else
			throw new IllegalArgumentException(
					"Tipo de Cadastro [ "+tipoDeCadastro+" ] nulo !");
		
		return validator_A_CP;
	}
	
	/**
	 * Faz a pré-validação e se correto identifica o tipo de cadastro.
	 * 
	 * @param codigoDoCadastro
	 * @return
	 * @throws IllegalArgumentException
	 */
	private static EnumCadastroDePessoa selectTipoDeCadastro(
			String codigoDoCadastro) throws IllegalArgumentException {

		EnumCadastroDePessoa tipo = null;

		switch_Tipo: {

			if (StringUtils.isNotBlank(codigoDoCadastro)) {

				/*
				 * FILTRO
				 */
				
				if (Pattern.matches(REGEX_CPF, codigoDoCadastro)) {

					tipo = EnumCadastroDePessoa.CPF;

					break switch_Tipo;
					
				}

				if (Pattern.matches(REGEX_CNPJ, codigoDoCadastro)) {

					tipo = EnumCadastroDePessoa.CNPJ;

					break switch_Tipo;
				}
				
			}
			
			throw new IllegalArgumentException(
					"O código de cadastro [ "+codigoDoCadastro+" ] não está em um formatador válido !");
		}

		return tipo;

	}

	/**
	 * Armazena o valor do cadastro de pessoa.
	 * 
	 * @param codigoDoCadastro -
	 *            cadastro de pessoa para validação.
	 */
	public void setCodigoDoCadastro(String codigoDoCadastro) {

		if (selectTipoDeCadastro(codigoDoCadastro) == this.tipoDeCadastro) {

			this.codigoDoCadastro.delete(0, codigoDoCadastro.length());
			
			this.codigoDoCadastro.append(codigoDoCadastro);
			
			removeFormatacao();
		} else
			throw new IllegalArgumentException("Este é um validador de: "
					+ this.tipoDeCadastro);

	}

	public static boolean isParametrosValidos(String codigoDoCadastro,
			EnumCadastroDePessoa tipoDeCadastro) throws IllegalArgumentException {

		boolean isValido = false;

		if (codigoDoCadastro != null && tipoDeCadastro != null) {

			if (Pattern.matches(REGEX_CADASTRO, codigoDoCadastro)) {

				isValido = true;

			} else {

				throw new IllegalArgumentException(
						"O cadastro num tamanho incorreto ou o cadastro não exsite: [ "
								+ codigoDoCadastro + " ]");
			}
		} else {

			throw new IllegalArgumentException(
					"O tipo de cadastro está incorreto: [ " + tipoDeCadastro
							+ " ] ou o cadastro não exsite: [ "
							+ codigoDoCadastro + " ]");
		}

		return isValido;
	}
	
	/**
	 * Recupera o cadastro de pessoa a ser validado. <br />
	 * Obs.: A String retornada não possui formatação, ou seja, possui apenas os
	 * dígitos.
	 * 
	 * @return cadastro de pessoa a ser validado.
	 */
	public String getCodigoDoCadastro() {

		return codigoDoCadastro.toString();
	}

	/**
	 * Indica se o validador é de pessoa física.
	 * 
	 * @return verdadeiro se for de pessoa física.
	 */
	public boolean isFisica() {

		return this instanceof Validator4CPF;
	}

	/**
	 * Indica se o validador é de pessoa jurídica.
	 * 
	 * @return verdadeiro se for de pessoa jurídica.
	 */
	public boolean isJuridica() {

		return this instanceof Validator4CNPJ;
	}

}
