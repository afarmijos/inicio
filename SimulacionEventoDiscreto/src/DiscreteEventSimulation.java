
public class DiscreteEventSimulation {

	public static void main(String[] args) {
		
		
		Simulation s = new Simulation();
		
		
		//s.setup(); // setup simulation;
		
		//s.run(10000); // run of simulation
		
		
		s.ejecutarSimulacionTraceDriven("C:\\Users\\Alex\\Google Drive\\PHD\\Clases\\SistemasOperativosAvanzados\\DeberDiscreteEventSimulation\\Entrada12.txt");
		
		
		System.out.println("El tiempo promedio fuera de servicio es: "+s.calcularTiempoPromedioDowntime());
	}

}
