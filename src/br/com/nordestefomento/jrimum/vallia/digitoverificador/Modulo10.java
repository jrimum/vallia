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
 * Created at: 30/03/2008 - 18:51:32
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
 * Criado em: 30/03/2008 - 18:51:32
 * 
 */


package br.com.nordestefomento.jrimum.vallia.digitoverificador;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * Rotina do módulo 10.<br />
 * O módulo 10 funciona da seguinte maneira: 
 * </p>
 * <p>
 * Cada dígito do número, começando da direita para a esquerda (menos significativo para o mais significativo), 
 * é multiplicado pelo números 2 e 1, alternadamente, iniciando pelo 2.
 * <br />
 * Exemplo para o número 123456:
 * <code>
 * +---+---+---+---+---+---+
 * | 1 | 2 | 3 | 4 | 5 | 6 |
 * +---+---+---+---+---+---+
 *   |   |   |   |   |   |
 *  x1  x2  x1  x2  x1  x2
 *   |   |   |   |   |   |
 *  =1  =4  =3  =8  =5  =[ 3 <= ( 1 + 2 <==12 ) ] = 24
 *   +---+---+---+---+---+-> = (24 / 10) = 3, resto 3; Então o módulo é igual a 3.
 * </code>
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
final class Modulo10 extends AModulo {


	private static final long serialVersionUID = 3277497627284351444L;

	/**
	 * Inicializa os atributos do módulo com um valor padrão.<br />
	 * No caso, o limite mínimo é 1 e o limite máximo é 2.
	 *
	 */
	public Modulo10() {
		
		this.setE_Modulo(EnumModulo.MODULO_10);
		this.setLimiteMinimo(1);
		this.setLimiteMaximo(2);
	}

	/**
	 * 
	 * @see br.com.nordestefomento.jrimum.vallia.digitoverificador.AModulo#calcular(java.lang.String)
	 */
	@Override
	public int calcular(String numero) throws IllegalArgumentException {

		int resto = 0;
		int produto = 0;
		int peso = 0;
		int soma = 0;
		
		if(StringUtils.isNotBlank(numero) && StringUtils.isNumeric(numero)){
			
			StringBuilder sb = new StringBuilder(numero);
			sb.reverse();
			
			peso = getLimiteMaximo();
			
			for(char c : sb.toString().toCharArray()){
				
				produto = peso * Character.getNumericValue(c);
				
				if(produto > 9){
					
					soma += produto / 10;
					soma += produto % 10;
				}
				else
					soma += produto;
				
				peso = ( peso == getLimiteMaximo() ) ? getLimiteMinimo() : getLimiteMaximo();
			}
			
			resto = soma % getValor();
			
		}else
			throw new IllegalArgumentException(O_ARGUMENTO_DEVE_CONTER_APENAS_NUMEROS);
		
		return resto;
	}
	
	/**
	 * @see br.com.nordestefomento.jrimum.vallia.digitoverificador.AModulo#calcular(long)
	 */
	@Override
	public int calcular(long numero) {
		
		return calcular(String.valueOf(numero));
	}

}
