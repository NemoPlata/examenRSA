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

	public GuardarClaves(){

	}

	public void guardarClaves(BigInteger p, BigInteger q, BigInteger phi) throws IOException, ClassNotFoundException{
		claves = new ClavesRSA(p , q, phi);
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
}