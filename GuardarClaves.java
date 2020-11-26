import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.math.BigInteger;

public class GuardarClaves implements Serializable{
	
	private ArrayList<ClavesRSA> listaClavesRSA = new ArrayList<ClavesRSA>();
	private ClavesRSA claves = new ClavesRSA();
	private ArrayList<ClavePrivada> listaClavePrivada = new ArrayList<ClavePrivada>();
	private ClavePrivada clave_privada = new ClavePrivada();

	public GuardarClaves(){

	}

	public void guardarClaves(BigInteger p, BigInteger q, BigInteger phi, BigInteger[] msj) throws IOException, ClassNotFoundException{
		claves = new ClavesRSA(p , q, phi, msj);
		listaClavesRSA.add(claves);
		FileOutputStream archivoDat = new FileOutputStream("Claves.dat");
		ObjectOutputStream writer = new ObjectOutputStream(archivoDat);
		writer.writeObject(listaClavesRSA);
		writer.close();
	}

	public void obtenerClaves() throws FileNotFoundException, IOException, ClassNotFoundException{
		FileInputStream archivoDat = new FileInputStream("Claves.dat");
		ObjectInputStream reader = new ObjectInputStream(archivoDat);
		listaClavesRSA = (ArrayList) reader.readObject();
		reader.close();
	}

	public void guardarClavePrivada(BigInteger clavePrivada){
		clave_privada = new ClavesRSA(clavePrivada);
		listaClavePrivada.add(clave_privada);
		FileOutputStream archivoDat = new FileOutputStream("ClavePrivada.dat");
		ObjectOutputStream writer = new ObjectOutputStream(archivoDat);
		writer.writeObject(listaClavePrivada);
		writer.close();
	}

	public void obtenerClavePrivada() throws FileNotFoundException, IOException, ClassNotFoundException{
		FileInputStream archivoDat = new FileInputStream("ClavePrivada.dat");
		ObjectInputStream reader = new ObjectInputStream(archivoDat);
		listaClavePrivada = (ArrayList) reader.readObject();
		reader.close();
	}

	public ArrayList<ClavesRSA> getListaClavesRSA(){
		return listaClavesRSA;
	}

	public void setListaClavesRSA(ArrayList<ClavesRSA> listaClavesRSA){
		this.listaClavesRSA = listaClavesRSA;
	}

	public ClavesRSA getClaves(){
		return claves;
	}

	public void setClaves(ClavesRSA claves){
		this.claves = claves;
	}

	public ArrayList<ClavePrivada> getListaClavePrivada(){
		return listaClavePrivada;
	}

	public void setListaClavePrivada(ArrayList<ClavePrivada> listaClavePrivada){
		this.listaClavePrivada = listaClavePrivada;
	}

	public ClavePrivada getClavePrivada(){
		return clave_privada;
	}

	public void setClavePrivada(ClavePrivada clave_privada){
		this.clave_privada = clave_privada;
	}
}