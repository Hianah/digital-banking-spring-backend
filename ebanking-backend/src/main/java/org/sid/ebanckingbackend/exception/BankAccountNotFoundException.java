package org.sid.ebanckingbackend.exception;

public class BankAccountNotFoundException extends Exception{

	public BankAccountNotFoundException (String message){
		super(message);
	}
}
