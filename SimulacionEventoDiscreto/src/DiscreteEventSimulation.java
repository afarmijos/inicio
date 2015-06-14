
public class DiscreteEventSimulation {

	public static void main(String[] args) {
		
		Simulation s = new Simulation();
		
		if ("random".equals(args[0])) {
			
			s.setup(); // setup simulation;
			s.run(10000); // run of simulation
			System.out.println("El tiempo promedio fuera de servicio es: "+s.calcularTiempoPromedioDowntime());
			
		}
			
		if ("trace".equals(args[0])) {				
			s.ejecutarSimulacionTraceDriven(args[1]);
			System.out.println("El tiempo promedio fuera de servicio es: "+s.calcularTiempoPromedioDowntime());				
		}
		
		if ("procesarResultado".equals(args[0])) {				
			s.procesarResultado(args[1], args[2]);				
		}		
		
		if ("generarDriven".equals(args[0])) {				
			s.generarEntradaDriven(args[1], args[2]);				
		}
		
				
	}

}
