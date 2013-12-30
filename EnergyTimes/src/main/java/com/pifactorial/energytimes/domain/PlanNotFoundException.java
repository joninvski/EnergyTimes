package com.pifactorial.energytimes.domain;

public class PlanNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public PlanNotFoundException(String msg) {
        super(msg);
	}
}
