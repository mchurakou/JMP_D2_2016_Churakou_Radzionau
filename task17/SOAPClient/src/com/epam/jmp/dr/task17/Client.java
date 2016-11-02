package com.epam.jmp.dr.task17;

import com.epam.jmp.dr.task17.soap.ws.SOAPWebService;
import com.epam.jmp.dr.task17.soap.ws.SOAPWebServiceImplService;

public class Client {

	
	public static void main(String[] args) {
		
		SOAPWebService service = new SOAPWebServiceImplService().getSOAPWebServiceImplPort();
		
		System.out.println(service.getString());
	}

}
