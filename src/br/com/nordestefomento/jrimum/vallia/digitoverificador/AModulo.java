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
 * Created at: 30/03/2008 - 18:22:01
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
 * Criado em: 30/03/2008 - 18:22:01
 * 
 */


package br.com.nordestefomento.jrimum.vallia.digitoverificador;

import org.apache.commons.lang.StringUtils;

import br.com.nordestefomento.jrimum.ACurbitaObject;

/**
 * 
 * <p>
 * Representa o módulo no contexto de autenticação, ou seja, uma rotina que auxilia no cálculo do dígito verificador.
 * </p>
 * <p>
 * As rotinas tradicionais são Módulo 10 e Módulo 11.
 * </p>
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

/**
 * 
 * <p>
 * DEFINIÇÃO DA CLASSE
 * </p>
 * 
 * <p>
 * OBJETIVO/PROPÓSITO
 * </p>
 * 
 * <p>
 * EXEMPLO: 
 * </p>
 * 
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 * 
 * @since 
 * 
 * @version 
 */
	
public abstract class AModulo extends ACurbitaObject{
	
	/**
	 * Mensagem da exceção lançada no método calcular.
	 */
	protected static final String O_ARGUMENTO_DEVE_CONTER_APENAS_NUMEROS = "O argumento deve conter apenas números !";

	private EnumModulo enumModulo;
	
	private int limiteMaximo;
	
	private int limiteMinimo;
	
	public AModulo() {
		super();
	}
	
	/**
	 * Calcula o módulo (no contexto da autenticação) do número passado.
	 * @param numero - número para ser calculado o módulo.
	 * @return módulo do número.
	 * @throws IllegalArgumentException caso a String não esteja em um formatador aceitável.
	 * (A String deve conter apenas números).
	 * 
	 */
	public abstract int calcular(String numero)	throws IllegalArgumentException;

	/**
	 * Calcula o módulo (no contexto da autenticação) do número passado.
	 * @param numero - número para ser calculado o módulo.
	 * @return módulo do número.
	 */
	public abstract int calcular(long numero);
	
	

	
	/**
	 * 
	 * <p>
	 * Efetua somente o cálculo do produto e a soma dos produtos, não realiza a
	 * operação módulo.
	 * </p>
	 * <p>
	 * Exemplo para o número 654321: <br />
	 * <pre>
	 * +---+---+---+---+---+---+
	 * | 6 | 5 | 4 | 3 | 2 | 1 |
	 * +---+---+---+---+---+---+
	 *   |   |   |   |   |   |
	 *  x7  x6  x5  x4  x3  x2
	 *   |   |   |   |   |   |
	 *  =42 =30 =20 =12 =6  =2
	 *   +---+---+---+---+---+- == retorne a soma (42 + 30 + 20 + 12 + 6 + 2) = 112
	 * </pre>
	 * </p> 
	 * 
	 * @param modulo
	 * @param numero
	 * @param limiteMin
	 * @param limiteMax
	 * 
	 * @return soma
	 * 
	 * @since 0.2 
	 */
		
	public static int calculeSomaSequencial(EnumModulo modulo, String numero, int limiteMin, int limiteMax) {

		int resto = 0;
		int peso = 0;
		int soma = 0;
		
		if(StringUtils.isNotBlank(numero) && StringUtils.isNumeric(numero)){
			
			StringBuilder sb = new StringBuilder(numero);
			sb.reverse();
		
			peso = limiteMin;
			
			for(char c : sb.toString().toCharArray())
			{
				soma += peso * Character.getNumericValue(c);
				peso++;
				
				if(peso > limiteMax)
					peso = limiteMin;
			}
			
			resto = soma % modulo.getValor();
			
		}else
			throw new IllegalArgumentException(O_ARGUMENTO_DEVE_CONTER_APENAS_NUMEROS);
		
		return resto;
	}

	/**
	 * <p>
	 * Instancia o módulo que foi especificado pela enumeração de módulos.
	 * </p>
	 * <p>
	 * <code>A_Módulo módulo = A_Módulo.getInstance(E_Módulo.MÓDULO_11);</code>
	 * </p>
	 * @param enumModulo - enumeração de módulos.
	 * @return uma instância de módulo.
	 */
	public static AModulo getInstance(EnumModulo enumModulo)
	{
		AModulo aModulo = enumModulo.getModulo();		
		
		return aModulo;
	}

	/**
	 * <p>
	 * Recupera o valor do módulo.
	 * </p>
	 * <p>
	 * Ex.: Para o módulo 10, retorna <code>10</code>; para o módulo 11, <code>11</code>.
	 * </p>
	 * @return valor
	 */
	public int getValor(){
		return enumModulo.getValor();	
	}
		
	/**
	 * Recupera o limite máximo.
	 * @return limite máximo.
	 */
	public int getLimiteMaximo() {
		return limiteMaximo;
	}

	/**
	 * Modifica o limite máximo.
	 * @param limiteMaximo - limite máximo.
	 */
	public void setLimiteMaximo(int limiteMaximo) {
		this.limiteMaximo = limiteMaximo;
	}

	/**
	 * Recupera o limite mínimo.
	 * @return limite mínimo.
	 */
	public int getLimiteMinimo() {
		return limiteMinimo;
	}

	/**
	 * Modifica o limite mínimo.
	 * @param limiteMinimo - limite mínimo.
	 */
	public void setLimiteMinimo(int limiteMinimo) {
		this.limiteMinimo = limiteMinimo;
	}

	/**
	 * Recupera a enumeração de módulos.
	 * @return enumeração de módulos.
	 */
	public EnumModulo getE_Modulo() {
		return enumModulo;
	}

	/**
	 * Modifica a enumeração de módulos.
	 * @param enumModulo - enumeração de módulos.
	 */
	public void setE_Modulo(EnumModulo enumModulo) {
		this.enumModulo = enumModulo;
	}
			
}
