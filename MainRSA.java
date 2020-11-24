import java.math.BigInteger;
import java.util.Scanner;

public class MainRSA{

	public static void main(String[]args){
		Scanner reader = new Scanner(System.in);
		int tamano = 1024;
		BigInteger[] msjCifrado;
		String msjDescifrado;

		procesosRSA prsa = new procesosRSA(tamano);
		prsa.generarPrimos();
		prsa.generarClavePublicaPrivadaPhi();
		System.out.println("Ingrese el mensaje a cifrar por favor:");
		String mensaje = reader.next();
		msjCifrado = prsa.cifrarRSA_ClavePublica(mensaje);

		System.out.println("Mensaje Cifrado: ");
		for(int i=0; i<msjCifrado.length; i++){
			System.out.print(msjCifrado[i]);
		}
		System.out.println("");

		msjDescifrado = prsa.descifrarRSA_ClavePrivada(msjCifrado);
		System.out.println("Mensaje Descifrado: ");
		System.out.println(msjDescifrado);

	}
}