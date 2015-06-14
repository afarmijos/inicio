import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class Otros {

	public static void escribirArchivo(List<String> lista, String archivo){
		
		
		FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(archivo);
            pw = new PrintWriter(fichero);
            
            for (String valor : lista) {
            	pw.println(valor);
    		}
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
		
		
		
	}
	
	public static List<String> leerArchivo(String archivo){
		
		
		List<String> contenido=new ArrayList<String>();
		
		String cadena;
	      FileReader f;
		try {
			f = new FileReader(archivo);
			
			BufferedReader b = new BufferedReader(f);
		      while((cadena = b.readLine())!=null) {
		    	  contenido.add(cadena);
		      }
		      b.close();
			
		      
		      
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return contenido;
		
	}
	
	
	public static double sacarPromedio(List<Double> listaElementos){
		
		double suma=0;
		
		if (listaElementos.isEmpty())
			return 0;
		
		for (Double valor : listaElementos) {
			suma=suma+valor.doubleValue();
		}
		
	    // calculate sum
	    
	    return suma/listaElementos.size();
		
		
	}
	
	
}
