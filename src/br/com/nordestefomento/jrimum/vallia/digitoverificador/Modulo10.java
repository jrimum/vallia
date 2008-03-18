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
 * Created at / Criado em : 17/03/2007 - 17:43:16
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
 *  =1  =4  =3  =8  =5  =12
 *   +---+---+---+---+---+-> = (33 / 10) = 3, resto 3; Então o módulo é igual a 3.
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
