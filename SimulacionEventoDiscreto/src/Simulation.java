import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;


public class Simulation {
	public Random generator = new Random(); // random number generator
	public EventHeap h;
	double now;
	
	private boolean traceDriven;
	
	
	public Machine m = new Machine();
	public Repairman r = new Repairman();
	public User u = new User();
	
	double MTTR;
	double MTTRvariance;
	
	private double MTTF;
	private double MTTFvariance;
	
	Queue<CicloEventoDiscreto> colaEventosDriven= null;
	private CicloEventoDiscreto eventoDrivenAnterior=null;
	
	List<Double> listaTiempoFueraServicio= new ArrayList<Double>();
	
	public Simulation() {
		generator = new Random();
		h = new EventHeap(10000);
		now = 0;
		
		MTTR = 10.0;
		MTTRvariance = 2.0;
		
		MTTF = 30.0;
		MTTFvariance = 5.0;
		traceDriven=false;
	}

	public void scheduleEvent(Event e) {
		h.insert(e);
	}
	
	public void setupSimulationTraceDriven(String rutaArchivoEntradaDriven ) {
		
		traceDriven=true;
		colaEventosDriven=procesarEntradaDriven(rutaArchivoEntradaDriven);
		setup();
		
		return;
	}
	
	public void setup( ) {
		
		Event machineEvent = new Event();
		machineEvent.setHandler(m);
		machineEvent.setType(working);
		machineEvent.setTime(0);
		scheduleEvent(machineEvent);
		
		Event userEvent = new Event();
		userEvent.setHandler(u);
		userEvent.setType(userCheck);
		userEvent.setTime(60);
		scheduleEvent(userEvent);
		return;
	}
	
	public void run(double maxTime) {
		while (!h.isEmpty() && h.peek().getTime()<=maxTime) {
			Event nextEvent = h.remove();
			now = nextEvent.getTime();
			
			nextEvent.getHandler().respondToEvent(nextEvent, this);
			
		}
	}
	
	public void ejecutarSimulacionTraceDriven (String rutaArchivoEntradaDriven) {
		
		setupSimulationTraceDriven(rutaArchivoEntradaDriven);
		
		while (!h.isEmpty()&&(! colaEventosDriven.isEmpty() )){
			
			Event nextEvent = h.remove();
			now = nextEvent.getTime();
			
			nextEvent.getHandler().respondToEvent(nextEvent, this);			
			
		}			
		
	}
	
	public double obtenerTiempoReparacion() {	
		
		double tiempo=0;
		
		if (isTraceDriven())
			
			if (!colaEventosDriven.isEmpty())
				tiempo=colaEventosDriven.peek().tiempoDuracionReparacion;
		else
			tiempo= Math.abs(generator.nextGaussian()*MTTRvariance+MTTR);
		
		return tiempo;
	}
	
	public double obtenerSiguienteTiempoFallo() {
		
		double tiempo=0;
		
		if (isTraceDriven()){
			
			tiempo=colaEventosDriven.peek().tiempoMomentoFailure;
			
			if (eventoDrivenAnterior!=null){
				
				tiempo=tiempo-(eventoDrivenAnterior.tiempoMomentoFailure
						+eventoDrivenAnterior.tiempoDuracionReparacion);
				
			}
		}	
			
		else
			tiempo= Math.abs(generator.nextGaussian()*MTTFvariance+MTTF);
		
		listaTiempoFueraServicio.add(new Double(tiempo));
		
		return tiempo;
		
		
	}
	
	private double extraeValor(String cadena){
		
		return Double.valueOf(cadena.substring(0, cadena.indexOf(" "))).doubleValue();
	}
	
	private List<CicloEventoDiscreto> capturarEventosDesdeArchivo(String rutaArchivoSalida){
		
		
		CicloEventoDiscreto ciclo= new CicloEventoDiscreto();	
		 
		
		List<CicloEventoDiscreto> listaCiclos = new ArrayList<CicloEventoDiscreto>();
		
		List<String> contenidoArchivo = Otros.leerArchivo(rutaArchivoSalida);
		
		for (String cadena : contenidoArchivo) {
	          
	    	  System.out.println(cadena);
	         if (!cadena.contains( "user check")){
	        	 
	        	 if (ciclo.isCicloCerrado()){
	        		
	        		 ciclo=new CicloEventoDiscreto();
	        	 }
	        	 
	        	 if (cadena.contains( labelWorking )){
	        		 ciclo.setTiempoMomentoWorking(extraeValor(cadena));		        		 
	        	 }
	        	 
	        	 if (cadena.contains( labelFailure )){
	        		 ciclo.setTiempoMomentoFailure(extraeValor(cadena));
	        	 }
	        	 
	        	 if (cadena.contains( labelStartRepair )){
	        		 ciclo.setTiempoMomentoStartingRepair(extraeValor(cadena));		        		 
	        	 }
	        	 
	        	 if (cadena.contains( labelFinishRepair )){
	        		 ciclo.setTiempoMomentoFinishingRepair(extraeValor(cadena));
	        		 
	        		 ciclo.setTiempoDuracionReparacion(ciclo.tiempoMomentoFinishingRepair-ciclo.tiempoMomentoStartingRepair);
	        		 
	        		 ciclo.setCicloCerrado(true);
	        		 listaCiclos.add(ciclo);
	        		 
	        	 }		        	 
	        	 
	        	 
	        	 
	  		} 
	          
		}		
		
		
		return listaCiclos;
		
	}
	
	public void procesarResultado(String rutaResultado, 
						String rutaEntradaDrivenGenerada)
						{
		
		List<CicloEventoDiscreto> listaCiclos= capturarEventosDesdeArchivo(rutaResultado);
		
		List<String> contenidoArchivoDriven=new ArrayList<String>();
		
		for (CicloEventoDiscreto cicloEventoDiscreto : listaCiclos) {
			contenidoArchivoDriven.add(cicloEventoDiscreto.toStringFormated());
		}
		
		Otros.escribirArchivo(contenidoArchivoDriven, rutaEntradaDrivenGenerada);
		
    }
	
	public void generarEntradaDriven(String rutaResultado, 
			String rutaEntradaDrivenGenerada)
			{

		List<CicloEventoDiscreto> listaCiclos= capturarEventosDesdeArchivo(rutaResultado);
		
		List<String> contenidoArchivoDriven=new ArrayList<String>();
		
		for (CicloEventoDiscreto cicloEventoDiscreto : listaCiclos) {
		contenidoArchivoDriven.add(cicloEventoDiscreto.toStringFormatedDriven() );
		}
		
		Otros.escribirArchivo(contenidoArchivoDriven, rutaEntradaDrivenGenerada);
		
		}
	
	public Queue<CicloEventoDiscreto> procesarEntradaDriven(String rutaArchivoSalida){
		
		Queue<CicloEventoDiscreto> colaCiclos = new LinkedList<CicloEventoDiscreto>();
		
		String cadena;
	      FileReader f;
		try {
			f = new FileReader(rutaArchivoSalida);
			
			BufferedReader b = new BufferedReader(f);
		      while((cadena = b.readLine())!=null) {
		    	  
		    	  CicloEventoDiscreto ciclo= new CicloEventoDiscreto();
		    	  		    	  
		    	  String[] valores=cadena.split(CicloEventoDiscreto.separador);		    	            
		    	 
		    	  ciclo.setTiempoMomentoFailure(Double.valueOf(valores[0]).doubleValue());
		    	  ciclo.setTiempoDuracionReparacion(Double.valueOf(valores[1]).doubleValue());
		    	  //ciclo.setTiempoDuracionSiguienteDanio(Double.valueOf(valores[2]).doubleValue());
		    	  
		    	  colaCiclos.add(ciclo);
		      }
		      b.close();  
		      
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return colaCiclos;
	}
	
	public double calcularTiempoPromedioDowntime()
	{
		
		return Otros.sacarPromedio(listaTiempoFueraServicio);
		
	}
	
	// events
	public final int working = 1;
	public final int failure = 2;
	public final int startRepair = 3;
	public final int finishRepair = 4;
	public final int userCheck = 5;
	
	public final String labelWorking="machine working";
	public final String labelFailure="machine failure";
	public final String labelStartRepair="starting repair";
	public final String labelFinishRepair="finishing repair";
	public final String labelUserCheck="user check";

	public boolean isTraceDriven() {
		return traceDriven;
	}

	public void setTraceDriven(boolean traceDriven) {
		this.traceDriven = traceDriven;
	}

	public CicloEventoDiscreto getEventoDrivenAnterior() {
		return eventoDrivenAnterior;
	}

	public void setEventoDrivenAnterior(CicloEventoDiscreto eventoDrivenAnterior) {
		this.eventoDrivenAnterior = eventoDrivenAnterior;
	}

}
