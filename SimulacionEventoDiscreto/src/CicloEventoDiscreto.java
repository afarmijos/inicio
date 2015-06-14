
public class CicloEventoDiscreto {

	double tiempoMomentoWorking;
	double tiempoMomentoFailure;
	double tiempoMomentoStartingRepair;
	double tiempoMomentoFinishingRepair;	
	double tiempoDuracionReparacion;
	
	public static String  separador= Character.toString((char)9);
	
	
	
	boolean cicloCerrado;
	
	
	
	public CicloEventoDiscreto() {
		
		cicloCerrado=false;
	}
	
	@Override
	public String toString() {
		return "CicloEventoDiscreto [tiempoMomentoWorking="
				+ tiempoMomentoWorking + ", tiempoMomentoFailure="
				+ tiempoMomentoFailure + ", tiempoMomentoStartingRepair="
				+ tiempoMomentoStartingRepair
				+ ", tiempoMomentoFinishingRepair="
				+ tiempoMomentoFinishingRepair + ", tiempoDuracionReparacion="
				+ tiempoDuracionReparacion + ", cicloCerrado="				
				+ cicloCerrado + ", isCicloCerrado()=" + isCicloCerrado()
				+ ", getTiempoMomentoWorking()=" + getTiempoMomentoWorking()
				+ ", getTiempoMomentoFailure()=" + getTiempoMomentoFailure()
				+ ", getTiempoMomentoStartingRepair()="
				+ getTiempoMomentoStartingRepair()
				+ ", getTiempoMomentoFinishingRepair()="
				+ getTiempoMomentoFinishingRepair()
				+ ", getTiempoDuracionReparacion()="
				+ getTiempoDuracionReparacion()
				+ ", getClass()="				
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	public String toStringFormated(){		
		
		
		return  tiempoMomentoWorking + separador
				+ tiempoMomentoFailure + separador
				+ tiempoMomentoStartingRepair + separador
				+ tiempoMomentoFinishingRepair ;
	  
	}
	
public String toStringFormatedDriven(){
		
		
		return String.valueOf(tiempoMomentoFailure)
					+ separador
					+ String.valueOf(tiempoDuracionReparacion);
		
	}
	
	public boolean isCicloCerrado() {
		return cicloCerrado;
	}
	public void setCicloCerrado(boolean cicloCerrado) {
		this.cicloCerrado = cicloCerrado;
	}
	public double getTiempoMomentoWorking() {
		return tiempoMomentoWorking;
	}
	public void setTiempoMomentoWorking(double tiempoMomentoWorking) {
		this.tiempoMomentoWorking = tiempoMomentoWorking;
	}
	public double getTiempoMomentoFailure() {
		return tiempoMomentoFailure;
	}
	public void setTiempoMomentoFailure(double tiempoMomentoFailure) {
		this.tiempoMomentoFailure = tiempoMomentoFailure;
	}
	public double getTiempoMomentoStartingRepair() {
		return tiempoMomentoStartingRepair;
	}
	public void setTiempoMomentoStartingRepair(double tiempoMomentoStartingRepair) {
		this.tiempoMomentoStartingRepair = tiempoMomentoStartingRepair;
	}
	public double getTiempoMomentoFinishingRepair() {
		return tiempoMomentoFinishingRepair;
	}
	public void setTiempoMomentoFinishingRepair(double tiempoMomentoFinishingRepair) {
		this.tiempoMomentoFinishingRepair = tiempoMomentoFinishingRepair;
	}
	public double getTiempoDuracionReparacion() {
		return tiempoDuracionReparacion;
	}
	public void setTiempoDuracionReparacion(double tiempoDuracionReparacion) {
		this.tiempoDuracionReparacion = tiempoDuracionReparacion;
	}	
	
}
