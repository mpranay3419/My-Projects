package com.coforge.ars.exception;

	
	public class BookingNotFoundException extends RuntimeException{

		
		public BookingNotFoundException(String errorMessage) {
			super(errorMessage);
		}
	
	}


