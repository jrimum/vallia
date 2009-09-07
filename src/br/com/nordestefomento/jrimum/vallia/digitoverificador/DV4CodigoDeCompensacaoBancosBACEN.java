/* 
 * Copyright 2008 JRimum Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * Created at: 15/06/2008 - 12:00:00
 *
 * ================================================================================
 *
 * Direitos autorais 2008 JRimum Project
 *
 * Licenciado sob a Licença Apache, Versão 2.0 ("LICENÇA"); você não pode 
 * usar esse arquivo exceto em conformidade com a esta LICENÇA. Você pode obter uma 
 * cópia desta LICENÇA em http://www.apache.org/licenses/LICENSE-2.0 A menos que 
 * haja exigência legal ou acordo por escrito, a distribuição de software sob esta 
 * LICENÇA se dará “COMO ESTÁ”, SEM GARANTIAS OU CONDIÇÕES DE QUALQUER TIPO, sejam 
 * expressas ou tácitas. Veja a LICENÇA para a redação específica a reger permissões 
 * e limitações sob esta LICENÇA.
 * 
 * Criado em: 15/06/2008 - 12:00:00
 * 
 */

package br.com.nordestefomento.jrimum.vallia.digitoverificador;

import static br.com.nordestefomento.jrimum.utilix.ObjectUtil.isNotNull;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 *  Cálcula para o dígito de compensação para o código de compensação dos bancos supervisionados pelo <a href="http://www.bcb.gov.br/?CHEQUESCOMPE">BACEN</a>
 * </p>
 * 
 * @author <a href="http://gilmatryx.googlepages.com">Gilmar P.S.L.</a>
 * @author Misael
 * 
 * @since 0.2
 * 
 * @version 0.2
 */
	
public class DV4CodigoDeCompensacaoBancosBACEN extends ADigitoVerificador {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5250684561237486022L;
	
	/**
	 * Mesagem padrão para erro. 
	 */
	private static final String MSG = "O código de compensação do banco deve ser um número entre 1 e 999.";

	/**
	 * <p>
	 * Calcula o dígito verificador para código de compensação passado.
	 * </p>
	 * 
	 * @see br.com.nordestefomento.jrimum.vallia.digitoverificador.ADigitoVerificador#calcule(java.lang.String)
	 * 
	 * @param numero 
	 * @return int digito
	 * @since 0.2
	 * 
	 */
	@Override
	public int calcule(String numero) throws IllegalArgumentException {

		if (StringUtils.isNotBlank(numero) && StringUtils.isNumeric(numero)) {
			
			return calcule(Integer.valueOf(numero.trim()));		
			
		} else {
			throw new IllegalArgumentException(MSG);
		}
	}

	
	/**
	 * <p>
	 * Calcula o dígito verificador para código de compensação passado.
	 * </p>
	 * 
	 * @param numero
	 * 
	 * @return int digito
	 * 
	 * @since 0.2
	 */
		
	public int calcule(int numero) {
		
		return calcule((long)numero);
	}
	
	/**
	 * <p>
	 * Calcula o dígito verificador para código de compensação passado.
	 * </p>
	 * 
	 * @param numero
	 * 
	 * @return int digito
	 
	 * @since 0.2
	 * 
	 * @see br.com.nordestefomento.jrimum.vallia.digitoverificador.ADigitoVerificador#calcule(long)
	 */
	@Override
	public int calcule(long numero) {
		
		int dv = -1;
		
		if ((numero > 0) && (numero <= 999)) {
			
			int soma = Modulo.calculeSomaSequencialMod11(String.valueOf(numero), 2, 9);
			
			soma *= 10;
			
			dv = soma % 11;
			
			if (dv == 10)
				dv = 0;
			
		}else
			throw new IllegalArgumentException(MSG);
		
		return dv;
	}
	
	
	/**
	 * <p>
	 * Retorna se um código de compensação passado é válido.
	 * </p>
	 * 
	 * @param codigo
	 * @return true se for númerio entre 0 e 999
	 * 
	 * @since 0.2
	 */
		
	public boolean isCodigoValido(String codigo)throws IllegalArgumentException{
		
		if (StringUtils.isNotBlank(codigo) && StringUtils.isNumeric(codigo)) {
			
			return isCodigoValido(Integer.valueOf(codigo.trim()));
			
		} else {
			throw new IllegalArgumentException(MSG);
		}
	}
	
	
	/**
	 * <p>
	 * Retorna se um código de compensação passado é válido.
	 * </p>
	 * 
	 * @param codigo
	 * @return true se entre 0 e 999
	 * 
	 * @since 0.2
	 */
		
	public boolean isCodigoValido(Integer codigo)throws IllegalArgumentException{
		
		if (isNotNull(codigo,"Código De Compensação") && (codigo > 0) && (codigo <= 999)) {
			return true;
		}else
			throw new IllegalArgumentException(MSG);
	}
}