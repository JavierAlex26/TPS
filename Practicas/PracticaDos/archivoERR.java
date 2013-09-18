import java.io.*;
import java.util.*;

public class archivoERR {

    public archivoERR() {
    }
    
    public void crearArchivoERR(){
    	try{
    		BufferedWriter bwErr = new BufferedWriter(new FileWriter("P2ASM.ERR",true));
    	}catch(IOException ioe){}
    }
    
    public void noEnd(){
    	try{
    		BufferedWriter bwErr = new BufferedWriter(new FileWriter("P2ASM.ERR",true));
    		bwErr.write("Error: NO SE ENCONTRO LA DIRECTIVA \"END\"");
    		bwErr.newLine();
			bwErr.close();
    	}catch(IOException ioe){}
    }
    
   	public void formatoLineaInvalido(int i){
    	try{
    		BufferedWriter bwErr = new BufferedWriter(new FileWriter("P2ASM.ERR",true));
    		bwErr.write(Integer.toString(i));
			bwErr.write(": FORMATO DE LINEA INVALIDO");
			bwErr.newLine();
			bwErr.close();
    	}catch(IOException ioe){}
    }
    
    public void entradaInvalida(int i){
    	try{
    		BufferedWriter bwErr = new BufferedWriter(new FileWriter("P2ASM.ERR",true));
    		bwErr.write(Integer.toString(i));
			bwErr.write(": FORMATO DE LINEA INVALIDO(Caracter No Valido)");
			bwErr.newLine();
			bwErr.close();
    	}catch(IOException ioe){}
    }
    public void estadoInvalida(int i){
    	try{
    		BufferedWriter bwErr = new BufferedWriter(new FileWriter("P2ASM.ERR",true));
    		bwErr.write(Integer.toString(i));
			bwErr.write(": FORMATO DE LINEA INVALIDO(Caracter Ubicado en Posicion Incorrecta)");
			bwErr.newLine();
			bwErr.close();
    	}catch(IOException ioe){}
    }
    
    public void etiquetaInvalida(int i){
    	try{
    		BufferedWriter bwErr = new BufferedWriter(new FileWriter("P2ASM.ERR",true));
			bwErr.write(Integer.toString(i));
			bwErr.write(": ETIQUETA INVALIDA");
			bwErr.newLine();
			bwErr.close();
    	}catch(IOException ioe){}
    }
    
    public void codopInvalido(int i){
    	try{
    		BufferedWriter bwErr = new BufferedWriter(new FileWriter("P2ASM.ERR",true));
			bwErr.write(Integer.toString(i));
			bwErr.write(": CODOP INVALIDO");
			bwErr.newLine();
			bwErr.close();
    	}catch(IOException ioe){}
    }
    
    public void codopNoEncontradoTabop(String cod,int i){
    	if(!cod.equals("END")){
	    	try{
	    		BufferedWriter bwErr = new BufferedWriter(new FileWriter("P2ASM.ERR",true));
				bwErr.write(Integer.toString(i));
				bwErr.write(": CODIGO "+"\""+cod+"\""+" NO ENCONTRADO");
				bwErr.newLine();
				bwErr.close();
	    	}catch(IOException ioe){}
    	}
    }
    
    public void operNoValidoParaCodop(String cod, String ope, int i){
    	if(!cod.equals("END")){
	    	try{
	    		BufferedWriter bwErr = new BufferedWriter(new FileWriter("P2ASM.ERR",true));
				bwErr.write(Integer.toString(i));
				bwErr.write(": OPERANDO "+"\""+ope+"\""+" NO VALIDO PARA codigo "+"\""+cod+"\"");
				bwErr.newLine();
				bwErr.close();
	    	}catch(IOException ioe){}
    	}
    }
}