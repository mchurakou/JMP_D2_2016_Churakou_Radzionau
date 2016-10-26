package com.epam.jmp.dr.task16.legacy;

import java.io.IOException;

public class LegacyCodeConsumer {

	private LegacyCodeClass legacy;

	public LegacyCodeConsumer(LegacyCodeClass l) {
		legacy = l;
	}

	public void useStaticLegacyMethod() {
		System.out.println(LegacyCodeClass.getStaticMessage());
	}

	public void useGetStringFromFile() throws IOException {
		System.out.println(legacy.getStringFromFile());
	}

}
