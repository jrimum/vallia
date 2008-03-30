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
 * Created at: 30/03/2008 - 18:51:44
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
 * Criado em: 30/03/2008 - 18:51:44
 * 
 */


package br.com.nordestefomento.jrimum.vallia.digitoverificador;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * Rotina do módulo 11.<br />
 * O módulo 11 funciona da seguinte maneira: 
 * </p>
 * <p>
 * Cada dígito do número, começando da direita para a esquerda (menos significativo para o mais significativo), 
 * é multiplicado pelo números 2, 3, 4 e assim sucessivamente até o limite máxmio definido, então inicia-se 
 * novamente a contagem.
 * <br />
 * O limite máximo padrão assumido pela classe é 9.
 * <br />
 * Exemplo para o número 654321:
 * <code>
 * +---+---+---+---+---+---+
 * | 6 | 5 | 4 | 3 | 2 | 1 |
 * +---+---+---+---+---+---+
 *   |   |   |   |   |   |
 *  x7  x6  x5  x4  x3  x2
 *   |   |   |   |   |   |
 *  =42 =30 =20 =12 =6  =2
 *   +---+---+---+---+---+-> = (112 / 11) = 10, resto 2; Então o módulo é igual a 2.
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
final class Modulo11 extends AModulo {

	
	private static final long serialVersionUID = -3175497932492181040L;

	/**
	 * Inicializa os atributos do módulo com um valor padrão.<br />
	 * No caso, o limite mínimo é 2 e o limite máximo é 9.
	 *
	 */
	public Modulo11() {
		
		this.setE_Modulo(EnumModulo.MODULO_11);
		this.setLimiteMinimo(2);
		this.setLimiteMaximo(9);
	}

	/**
	 * @see br.com.nordestefomento.jrimum.vallia.digitoverificador.AModulo#calcular(java.lang.String)
	 */
	@Override
	public int calcular(String numero) throws IllegalArgumentException {
		
		int resto = 0;
		int peso = 0;
		int soma = 0;
		
		if(StringUtils.isNotBlank(numero) && StringUtils.isNumeric(numero)){
			
			StringBuilder sb = new StringBuilder(numero);
			sb.reverse();
		
			peso = getLimiteMinimo();
			
			for(char c : sb.toString().toCharArray())
			{
				soma += peso * Character.getNumericValue(c);
				peso++;
				
				if(peso > getLimiteMaximo())
					peso = getLimiteMinimo();
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
