/**
 * @(#)Linea.java
 *
 *
 * @author 
 * @version 1.00 2013/8/30
 */

import java.io.*;
import java.util.*;

public class Linea {
	String linea;
	int i=0;
	
	Scanner Leer = new Scanner (System.in);
	
	int TdT[][]={{2,1,3,-1},{1,-1,1,6},{2,1,4,-1},{4,1,3,6},{4,1,5,6},{7,1,5,6},{-1,-1,-1,-1},
	             {7,1,8,6},{-1,1,8,6}}; 
		
    public Linea() {
    }
    public int analizaEntrada(char c){
    	if(c!=';' && !Character.isWhitespace(c) && c!=13)
    		return 0;
    	else
    		if(c==';')
    			return 1;
    		else
    			if(c==32 || c==9)
    				return 2;
    			else
    				return 3;
    }
    
    public void validaLinea(){
    	int estado=-1,entrada=-1,i=0, tam;
    	boolean b, etqErronea=false, codErroneo=false,end=true;
    	String etq, cod, ope, ruta, auxRuta;
    	File archINST, archERR;
    	
    	System.out.println("Introduce la ruta del Archivo: ");
    	ruta = Leer.nextLine();
    	
    	tam = ruta.length();
    	auxRuta = ruta.substring(tam-3,tam);
    	auxRuta = auxRuta.toUpperCase();
    	
    	if(!auxRuta.equals("ASM"))
    		System.out.println("\n\"Solo se permiten archivos con extension .ASM\"");
    	
    	try{
    		archINST = new File("PracticaUno.INST");
    		if(archINST.exists())
    			archINST.delete();
    			
    		archERR = new File("PracticaUno.ERR");
    		if(archERR.exists())
    			archERR.delete();
    		
	    	BufferedWriter bw = new BufferedWriter(new FileWriter("PracticaUno.INST",true));//agrega al final del archivo con el true    	
	    	bw.write("LINEA\t ETQ\t CODOP\t   OPER");
	    	bw.newLine();
	    	bw.write(".........................................");
	    	bw.newLine(); 	
	    		
	    	BufferedWriter bwErr = new BufferedWriter(new FileWriter("PracticaUno.ERR",true));
	    		
	    	try{
	    		BufferedReader br = new BufferedReader(new FileReader(ruta));
	    		while(br.ready())
	    		{
	    			StringTokenizer token = new StringTokenizer(br.readLine(),"\n");
	    			i++;
	    			while(token.hasMoreTokens() && end){
	    				linea = token.nextToken();

	    				System.out.println("Linea "+(i)+": "+linea);
	    				etq=cod=ope="";
	    				etqErronea=codErroneo=false;
	    				estado=0;
	    				b=true;
	    				tam = linea.length();
	    				
						for(int k=0; k<tam && b; k++){
							entrada = analizaEntrada(linea.charAt(k));
							if(entrada==-1){
								b=false;
								bwErr.write("Linea ");
								bwErr.write(Integer.toString(i));
								bwErr.write(": Error: FORMATO DE LINEA INVALIDO(Caracter No Valido)");
								bwErr.newLine();
							}
							else{
								estado = TdT[estado][entrada];
								if(estado==2){
									etq+=linea.charAt(k);
								}
								if(estado==4 && linea.charAt(k)!=32 ){
									cod+=linea.charAt(k);
								}
								if(estado==7 && linea.charAt(k)!=32){
									ope+=linea.charAt(k);
								}
								if(estado==-1){
									b=false;
								    bwErr.write("Linea ");
									bwErr.write(Integer.toString(i));
									bwErr.write(": Error: FORMATO DE LINEA INVALIDO(Caracter Ubicado en Posicion Incorrecta)");
									bwErr.newLine();
								}
							}
						}
						if(cod.equals("END")|| cod.equals("End")||cod.equals("ENd")||
						   cod.equals("ENd")||cod.equals("enD")||cod.equals("eND"))
						   	end=false;
						if(estado!=1){
							if(!etq.isEmpty()){
								if(etiquetaValida(etq))
									System.out.println("Etiqueta Valida");
								else{
									bwErr.write("Linea ");
									bwErr.write(Integer.toString(i));
									bwErr.write(": Error: ETIQUETA INVALIDA");
									bwErr.newLine();
									etqErronea=true;
								}
							}
							if(!cod.isEmpty()){
								System.out.println("CODOP: "+ cod);
								if(codopValido(cod))
									System.out.println("CODOP Valido");
								else{
									bwErr.write("Linea ");
									bwErr.write(Integer.toString(i));
									bwErr.write(": Error: CODOP INVALIDO");
									bwErr.newLine();
									codErroneo=true;
								}
							}
							if(!etqErronea && !codErroneo && end && b){
								escribeArchivoINST(i,etq,cod,ope,bw);
							}
						}
	    				i++;
	    			}
	    		}
	    		br.close();
	    		if(end==true){
					 bwErr.write("Error: NO SE ENCONTRO LA DIRECTIVA \"END\"");
	    		}
	    	}
	    	catch(FileNotFoundException f){System.out.println("No Existe Archivo....");}
	    	catch(IOException ioe){System.out.println("Error de archivo....");}
	     	bw.close();
	     	bwErr.close();
    	}catch(IOException ioe){}
    }
    
    public boolean etiquetaValida(String etq){
    	if(etq.matches("^[a-zA-Z]([a-zA-Z]|([0-9])|(_)){1,7}"))
    		return true;
    	else
    		return false;
    }
    
    public boolean codopValido(String cod){
    	int punto=0, tam=cod.length();
    	boolean flag=false;
    	if(tam > 5){
    		return false;
    	}
    	else
    		if(!(Character.isLowerCase(cod.charAt(0)) || Character.isUpperCase(cod.charAt(0)))){
    			return false;
    		}
    		else{
    			for(int i=1;i<tam && !flag;i++){
    				if(!(Character.isLetter(cod.charAt(i)) || cod.charAt(i)=='.'))
    					flag=true;
    				if(cod.charAt(i)=='.')
    					punto+=1;
    			}
    			if(flag || punto > 1)
    				return false;
    			else
    				return true;
    		}
    }
    
    public boolean operValido(String ope){
    	if(ope.matches(".+"))
    		return true;
    	else
    		return false;
    }
    
    public void escribeArchivoINST(int lin, String etq, String cod, String ope, BufferedWriter bw){
    	String line;
    	line = Integer.toString(lin);
    	try{
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
    	    bw.write("\t  ");
    	    if(ope.isEmpty())
    	    	bw.write("Null");
    	    else
    	    	bw.write(ope);
	    	bw.newLine();
    	}catch(IOException ioe){}
    }
    
    public static void main(String []args){
    	Linea l = new Linea();
    	l.validaLinea();
    }
}