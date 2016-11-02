package com.epam.jmp.dr.task17.soap.ws;

import javax.jws.WebService;

@WebService(endpointInterface = "com.epam.jmp.dr.task17.soap.ws.SOAPWebService")
public class SOAPWebServiceImpl implements SOAPWebService {

	@Override
	public String getString() {
		return "String test from ws service";
	}

}
