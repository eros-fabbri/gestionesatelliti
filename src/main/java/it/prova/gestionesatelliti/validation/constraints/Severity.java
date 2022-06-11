package it.prova.gestionesatelliti.validation.constraints;

import javax.validation.Payload;

public class Severity {
    public interface Info extends Payload {
    }

    public interface Error extends Payload {
    }
}
