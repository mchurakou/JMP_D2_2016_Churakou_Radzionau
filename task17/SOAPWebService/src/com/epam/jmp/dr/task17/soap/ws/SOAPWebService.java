package com.epam.jmp.dr.task17.soap.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface SOAPWebService {
	
	@WebMethod String getString();

}
