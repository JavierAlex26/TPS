import java.io.*;
import java.util.*;

public class archivoINST {
	

    public archivoINST() {
    }
    
    public void crearArchivo(){
    	try{
	    	BufferedWriter bw = new BufferedWriter(new FileWriter("P2ASM.INST",true));//agrega al final del archivo con el true
	    	bw.write("LINEA\t ETQ\t CODOP\t   OPER\t\t  MODOS");
	    	bw.newLine();
	    	bw.write(".................................................................................");
	    	bw.newLine();
	    	bw.close();
	    }catch(IOException ioe){}
    	
    }
    
    public void escribirPalabras(int lin, String etq, String cod, String ope){
    	String line;
    	line = Integer.toString(lin);
    	try{
    		BufferedWriter bw = new BufferedWriter(new FileWriter("P2ASM.INST",true));
    	    bw.write(line);
    	    bw.write("\t ");
    	    if(etq.isEmpty())
    	    	bw.write("Null");
    	    else
    	    	bw.write(etq);
    	    bw.write("\t ");
    	    if(cod.isEmpty())
    	    	bw.write("Null");
    	    else
    	    	bw.write(cod);
    	    bw.write("\t   ");
    	    if(ope.isEmpty())
    	    	bw.write("Null");
    	    else
    	    	bw.write(ope);
	    	bw.close();
    	}catch(IOException ioe){}
    }
    
    public void escribirModoDir(String modoDir, int i){
    	try{
    		BufferedWriter bw = new BufferedWriter(new FileWriter("P2ASM.INST",true));
    		if(i==0)
    			bw.write("\t\t  ");
    		bw.write(modoDir);
    		bw.close();
    	}catch(IOException ioe){}
    }
    
    public void escribirComa(){
    	try{
    		BufferedWriter bw = new BufferedWriter(new FileWriter("P2ASM.INST",true));
    	    bw.write(", ");
    		bw.close();
    	}catch(IOException ioe){}
    }
    
    public void nuevaLinea(){
    	try{
    		BufferedWriter bw = new BufferedWriter(new FileWriter("P2ASM.INST",true));
    		bw.newLine();
    		bw.close();
    	}catch(IOException ioe){}
    }
    
}