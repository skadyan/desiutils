package desi.coreservices.meta;

public @interface TicketValidator {
	Class<? extends desi.coreservices.TicketValidator> value() default desi.coreservices.TicketValidator.class;
}
