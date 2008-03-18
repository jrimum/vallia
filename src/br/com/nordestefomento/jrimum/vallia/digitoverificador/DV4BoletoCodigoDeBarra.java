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
 * Created at / Criado em : 17/03/2007 - 17:42:15
 * 
 */

package br.com.nordestefomento.jrimum.vallia.digitoverificador;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * Lógica de cálculo do dígito verificador do código de barras de um boleto.<br />
 * A lógica funciona da seguinte forma:
 * </p>
 * <p>
 * Utilizando-se o módulo 11, considerando-se os 43 dígitos que compõem o código de barras, já excluída a 5ª posição
 * (posição do dígito verificador), segue-se o procedimento abaixo:
 * </p>
 * <p>
 * Calcula-se o dígito verificador através da expressão <code>DV = 11 - R</code>, 
 * onde R é o resultado do cálculo do módulo.<br />
 * Observação: O dígito verificador será 1 para os restos (resultado do módulo): 0 , 10 ou 1 (zero, dez, um).
 * </p>
 * <p>
 * Obs.: A rotina de módulo utilizada é o módulo 11.
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
public class DV4BoletoCodigoDeBarra extends ADigitoVerificador {

	private static final int TAMANHO_SEM_DV = 43;
	
	private static final long serialVersionUID = 7977220668336110040L;
	
	public DV4BoletoCodigoDeBarra() {
		
		super();
	}

	/**
	 * @see br.com.nordestefomento.jrimum.vallia.digitoverificador.ADigitoVerificador#calcular(java.lang.String)
	 */
	@Override
	public int calcular(String numero) throws IllegalArgumentException {
		
		int dv = 0;
		
		AModulo aModulo = AModulo.getInstance(EnumModulo.MODULO_11);
		
		if(StringUtils.isNotBlank(numero) && StringUtils.isNumeric(numero) && (numero.length() == TAMANHO_SEM_DV)) {
			
			dv = aModulo.getValor() - aModulo.calcular(numero);
			
			if((dv == 0) || (dv == 1) || (dv > 9))
				dv = 1;
		}else
			throw new IllegalArgumentException("O código de barras [ "+numero+" ] deve conter apenas números e length = 43 !");
		
		return dv;
	}

}
