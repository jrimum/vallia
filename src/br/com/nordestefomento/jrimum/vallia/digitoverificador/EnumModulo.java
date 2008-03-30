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
 * Created at: 30/03/2008 - 18:51:20
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
 * Criado em: 30/03/2008 - 18:51:20
 * 
 */


package br.com.nordestefomento.jrimum.vallia.digitoverificador;

import java.io.Serializable;

/**
 * 
 * Enumeração das implementações dos módulos.
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
public enum EnumModulo implements Serializable {

	MODULO_10, MODULO_11;

	public AModulo getModulo() {
		
		AModulo modulo = null;
		
		switch(this) {
			
			case MODULO_10:
				modulo = new Modulo10();
				break;
				
			case MODULO_11:
				modulo = new Modulo11();
				break;
		}		
		
		return modulo;
		
	}
	
	public int getValor() {
		
		int valor = 0;
		
		switch(this) {
			
			case MODULO_10:
				valor = 10;
				break;
				
			case MODULO_11:
				valor = 11;
				break;
		}		
		
		return valor;
		
	}	
}
