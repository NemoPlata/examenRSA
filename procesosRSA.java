import java.math.BigInteger;
import java.io.IOException;
import java.util.*;

public class ProcesosRSA{
	
	private GuardarClaves guardarC = new GuardarClaves();
	//Variables del cifrado RSA Persona 1
	//Tamaño del primo
	private int tamanoPrimo = 1024;
	//Numeros Primos
	private BigInteger p_A, q_A, n_A;
	//Indicador de Euler Phi
	private BigInteger phi_A;
	//Clave publica e , clave privada inversa d
	private BigInteger e_A, d_A;

	public ProcesosRSA(){

	}

	public void generarPrimos(){
		p_A = new BigInteger(tamanoPrimo, 100, new Random());
		do{
			q_A = new BigInteger(tamanoPrimo, 100, new Random());	
		}while(q_A.compareTo(p_A)==0);
	}

	public void generarClavePublicaPrivadaPhi(){
		//Obtenemos el numero n_A
		n_A = p_A.multiply(q_A);
		//Obtenemos el numero phi = (p-1)*(q-1)
		phi_A = p_A.subtract(BigInteger.valueOf(1));
		phi_A = phi_A.multiply(q_A.subtract(BigInteger.valueOf(1)));

		//Elegir el numero coPrimo menor que n.  1 < e < Phi(n)
		do{
			e_A = new BigInteger(2*tamanoPrimo, new Random());
		}while((e_A.compareTo(phi_A) != -1) || (e_A.gcd(phi_A).compareTo(BigInteger.valueOf(1)) != 0));
		//realizar la operacion modulo d = e^(1mod phi)
		d_A = e_A.modInverse(phi_A);
		guardarC.guardarClavePrivada(d_A);			
	}

	public BigInteger[] cifrarRSA_ClavePublica(String mensaje) throws IOException, ClassNotFoundException{
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
		System.out.println("Numero P:");
		System.out.println(p_A);
		System.out.println();
		System.out.println("Numero Q:");
		System.out.println(q_A);
		System.out.println();
		System.out.println("Numero Phi:");
		System.out.println(phi_A);
		System.out.println();
		System.out.println("Mensaje Encriptado:");
		System.out.println();
		for(i=0; i<encriptado.length; i++) {
			System.out.print(encriptado[i]);	
		}		
		System.out.println();
		guardarC.guardarClaves(p_A,q_A,phi_A,encriptado);		
		return encriptado;
	}

	public String descifrarRSA_ClavePrivada(BigInteger p_A, BigInteger q_A, BigInteger phi_A, BigInteger[] cifrado, BigInteger d_A){
		BigInteger[] descifrar = new BigInteger[cifrado.length];
		char[] charArray = new char[descifrar.length];
		n_A = p_A.multiply(q_A);					

		for(int i=0; i<descifrar.length; i++){
			descifrar[i] = cifrado[i].modPow(d_A,n_A);
		}		
		for(int i=0; i<charArray.length; i++){
			charArray[i] = (char)(descifrar[i].intValue());
		}

		return (new String(charArray));
	}
}