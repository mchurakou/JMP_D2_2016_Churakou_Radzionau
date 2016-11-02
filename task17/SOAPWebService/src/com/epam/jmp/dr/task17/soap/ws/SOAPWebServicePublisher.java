package com.epam.jmp.dr.task17.soap.ws;

import javax.xml.ws.Endpoint;

public class SOAPWebServicePublisher {

	public static void main(String[] args) {

		Endpoint.publish("http://localhost:9999/soapws", new SOAPWebServiceImpl());

	}

}
