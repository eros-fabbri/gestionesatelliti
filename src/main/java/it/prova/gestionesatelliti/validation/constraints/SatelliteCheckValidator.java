package it.prova.gestionesatelliti.validation.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import it.prova.gestionesatelliti.model.Satellite;
import it.prova.gestionesatelliti.model.StatoSatellite;

public class SatelliteCheckValidator implements ConstraintValidator<SatelliteCheck, Satellite> {

	@Override
	public void initialize(SatelliteCheck constraintAnnotation) {
	}

	@Override
	public boolean isValid(Satellite satellite, ConstraintValidatorContext context) {

		if (satellite == null) {
			return true;
		}

		boolean isValid = true;

		if (satellite.getDataLancio() != null && satellite.getDataRientro() != null) {
			isValid = satellite.getDataLancio().before(satellite.getDataRientro());
			if (!isValid) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate("{dataRientro.primaDiDataLancio}")
						.addPropertyNode("dataRientro").addConstraintViolation();
			}
		}

		if (satellite.getStatoSatellite() == StatoSatellite.FISSO && satellite.getDataRientro() != null) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("{statoSatellite.statoNonValido}")
					.addPropertyNode("statoSatellite").addConstraintViolation();
		}

		if (satellite.getStatoSatellite() == StatoSatellite.DISATTIVATO && satellite.getDataRientro() == null) {
			isValid = true;
		}

		return isValid;
	}
}
