
public class Machine implements EventHandler {
	private double MTTF;
	private double MTTFvariance;
	public boolean working;
	
	public Machine() {
		
		working = true;
	}
	
	@Override
	public void respondToEvent(Event e, Simulation s) {
		if (e.getType()==s.working) {
			System.out.println(e.getTime()+" "+s.labelWorking);			
			working=true;
			double timeToNextFailure = s.obtenerSiguienteTiempoFallo();
			e.setTime(s.now+timeToNextFailure);
			e.setType(s.failure);
			s.scheduleEvent(e);
			return;
		}
		if (e.getType()==s.failure) {
			System.out.println(e.getTime()+" "+s.labelFailure);
			working=false;
			e.setTime(s.now);
			e.setHandler(s.r);
			e.setType(s.startRepair);
			s.scheduleEvent(e);
			return;
		}

	}

}
