import java.math.BigInteger;
import java.io.IOException;
import java.util.Scanner;

public class MainRSA{

	public static void main(String[]args){
		//ClavesRSA claves_rsa = new ClavesRSA();
		GuardarClaves guardar_claves = new GuardarClaves();
		Scanner reader = new Scanner(System.in);		
		BigInteger[] msjCifrado;
		BigInteger claveP, claveQ, clavePhi;
		String msjDescifrado;

		ProcesosRSA prsa = new ProcesosRSA();
		prsa.generarPrimos();
		try{
			prsa.generarClavePublicaPrivadaPhi();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		}		
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

		try{
			guardar_claves.obtenerClaves();
			for(int i=0; i<guardar_claves.getListaClavesRSA().size(); i++) {
				claveP = guardar_claves.getListaClavesRSA().get(i).getClave_P();
				claveQ = guardar_claves.getListaClavesRSA().get(i).getClave_Q();
				clavePhi = guardar_claves.getListaClavesRSA().get(i).getClave_Phi();				
				System.out.println("Clave P 2:");
				System.out.println(claveP);
				System.out.println("");
				System.out.println("Clave Q 2:");
				System.out.println(claveQ);
				System.out.println("");
				System.out.println("Clave Phi 2:");			
				System.out.println(clavePhi);		
			}			
		}catch(IOException ioe){
			ioe.printStackTrace();
		}catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		}			
	}
}