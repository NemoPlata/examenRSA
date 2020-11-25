import java.math.BigInteger;
import java.util.*;

public class procesosRSA{
	
	//Variables del cifrado RSA Persona 1
	//Tamaño del primo
	private int tamanoPrimo;
	//Numeros Primos
	private BigInteger p_A, q_A, n_A;
	//Indicador de Euler Phi
	private BigInteger phi_A;
	//Clave publica e , clave privada inversa d
	private BigInteger e_A, d_A;

	//Variables del cifrado RSA Persona 2
	//Numeros Primos
	private BigInteger n_B;
	//Clave publica e
	private BigInteger e_B;

	public procesosRSA(int tamanoPrimo){
		this.tamanoPrimo = tamanoPrimo;
	}

	public void generarPrimos(){
		p_A = new BigInteger(tamanoPrimo, 10, new Random());
		do{
			q_A = new BigInteger(tamanoPrimo, 10, new Random());	
		}while(q_A.compareTo(p_A)==0);
	}

	public void generarClavePublicaPrivadaPhi(){
		//Obtenemos el numero primo n_A
		n_A = p_A.multiply(q_A);
		//Obtenemos el numero phi
		phi_A = p_A.subtract(BigInteger.valueOf(1));
		phi_A = phi_A.multiply(q_A.subtract(BigInteger.valueOf(1)));

		//Elegir el numero coPrimo menor que n.  1 < e < Phi(n)
		do{
			e_A = new BigInteger(2*tamanoPrimo, new Random());
		}while((e_A.compareTo(phi_A) != -1) || (e_A.gcd(phi_A).compareTo(BigInteger.valueOf(1)) != 0));
		//realizar la operacion modulo d = e^(1mod phi)
		d_A = e_A.modInverse(phi_A);		
	}

	public BigInteger[] cifrarRSA_ClavePublica(String mensaje){
		int i;
		byte[] temp = new byte[1];
		byte[] digitos = mensaje.getBytes();
		BigInteger[] bigdigitos = new BigInteger[digitos.length];
		BigInteger[] encriptado = new BigInteger[bigdigitos.length];

		for(i=0; i<bigdigitos.length; i++){
			temp[0] = digitos[i];
			bigdigitos[i] = new BigInteger(temp);
		}			
		for(i=0; i<bigdigitos.length; i++) {
			encriptado[i] = bigdigitos[i].modPow(e_A,n_A);
		}
		return encriptado;
	}

	public String descifrarRSA_ClavePrivada(BigInteger[] cifrado){
		BigInteger[] descifrar = new BigInteger[cifrado.length];
		char[] charArray = new char[descifrar.length];

		for(int i=0; i<descifrar.length; i++){
			descifrar[i] = cifrado[i].modPow(d_A,n_A);
		}		
		for(int i=0; i<charArray.length; i++){
			charArray[i] = (char)(descifrar[i].intValue());
		}

		return (new String(charArray));
	}

	/*public BigInteger[] cifrarRSA_ClavePrivada(String mensaje){
		 
	}

	public String descifrar_ClavePublica(BigInteger[] cifrado){

	}*/
}