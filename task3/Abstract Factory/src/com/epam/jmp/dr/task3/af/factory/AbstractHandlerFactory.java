package com.epam.jmp.dr.task3.af.factory;

import com.epam.jmp.dr.task3.af.handler.Handler;

/**
 * AbstractHandlerFactory class
 *
 */
public abstract class AbstractHandlerFactory {

	/**
	 * Builds and return Handler instance, with provided details
	 * 
	 * @param details
	 * @return
	 * @throws Exception
	 */
	public abstract Handler createHandler(String details) throws Exception;

}
