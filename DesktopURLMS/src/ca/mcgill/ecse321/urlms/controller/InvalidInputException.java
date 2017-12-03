package ca.mcgill.ecse321.urlms.controller;

import java.io.*;

public class InvalidInputException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public InvalidInputException(String string) {
		super(string);
	}
}
