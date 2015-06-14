
public class Repairman implements EventHandler {

	
	
	public Repairman() {
		
	}
	
	
	@Override
	public void respondToEvent(Event e, Simulation s) {
		if (e.getType()==s.startRepair) {
			System.out.println(e.getTime()+" "+s.labelStartRepair);
			double timeToRepair = s.obtenerTiempoReparacion();
			e.setType(s.finishRepair);
			e.setTime(s.now+timeToRepair);
			s.scheduleEvent(e);
			return;
		}
		if (e.getType()==s.finishRepair) {
			System.out.println(e.getTime()+" "+s.labelFinishRepair);
			e.setHandler(s.m);
			e.setType(s.working);
			e.setTime(s.now);
			s.scheduleEvent(e);
			
			if (s.isTraceDriven())
				s.setEventoDrivenAnterior(s.colaEventosDriven.poll());
			
			return;
		}
	}

}
