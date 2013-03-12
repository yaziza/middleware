package de.tud.in.middleware.order;

import java.io.Serializable;

public enum OrderState implements Serializable {
	INIT, OPEN, DISPATCHED, DELIVERED, EXCEPTION;

	public String exceptionDescription;

	@Override
	public String toString() {
		if (this == OrderState.EXCEPTION) {
			return String.format("%s - %s", super.toString(), exceptionDescription);
		}
		return super.toString();
	}
	
	public static OrderState getStateFromDescription(String description) {
		OrderState value = null;
		
		for (OrderState state : OrderState.values()){
			if (description.equals(state.toString()))
				return state;
		}
		
		return value;
	}
}
