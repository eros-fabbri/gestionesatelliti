package it.prova.gestionesatelliti.validation.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import it.prova.gestionesatelliti.model.Satellite;

public class DataCheckValidator implements ConstraintValidator<DateCheck, Satellite>{
	
	DateMode dataMode;

	 @Override
	    public void initialize(DateCheck constraintAnnotation) {
	    }

	 @Override
	    public boolean isValid(Satellite satellite, ConstraintValidatorContext context) {
		 
		 	boolean isValid = false;
		 	
	        if ( satellite == null ) {
	            return true;
	        }

	        if (satellite.getDataLancio() != null && satellite.getDataRientro() != null) {
	        	isValid = satellite.getDataLancio().after(satellite.getDataRientro());
	        }
	        
	        if (satellite.getDataLancio() == null || satellite.getDataRientro() == null) {
	        	isValid = true;
	        }
	        System.out.println("Il validate è: " +isValid);
	        return isValid;
	    }
}
