package com.epam.jmp.dr.task3.af.factory;

import com.epam.jmp.dr.task3.af.handler.HandlerType;

/**
 * FactoryProducer class
 *
 */
public class FactoryProducer {

	/**
	 * Returns concrete implementation of AbstractHandlerFactory, depending on
	 * HandlerType
	 * 
	 * @param type
	 * @return implementation of AbstractHandlerFactory, depending on
	 *         HandlerType
	 */
	public static AbstractHandlerFactory getFactory(HandlerType type) {
		if (type == null) {
			return null;
		}

		switch (type) {

		case DB:
			return new DBHandlerFactory();

		case FILE:
			return new FileHadlerFactory();

		default:
			return null;
		}
	}

}
