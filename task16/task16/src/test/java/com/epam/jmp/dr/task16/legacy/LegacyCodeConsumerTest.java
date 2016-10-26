package com.epam.jmp.dr.task16.legacy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ LegacyCodeClass.class })
public class LegacyCodeConsumerTest {

	@InjectMocks
	private LegacyCodeConsumer cons;

	@Mock
	private LegacyCodeClass legacy;

	@Test
	public void testUseStaticLegacyMethod() throws Exception {
		PowerMockito.mockStatic(LegacyCodeClass.class);
		PowerMockito.when(LegacyCodeClass.getStaticMessage()).thenReturn("Mocked static method call result");
		cons.useStaticLegacyMethod();
	}

	@Test
	public void testUsePrivateLegacyMethod() throws Exception {
		LegacyCodeClass legacy = PowerMockito.spy(new LegacyCodeClass());
		PowerMockito.when(legacy, PowerMockito.method(LegacyCodeClass.class, "getMessage")).withNoArguments()
				.thenReturn("Mocked private method call result");
		legacy.printPrivateMessage();

	}

	@Test
	public void testUseFinalLegacyMethod() throws Exception {
		LegacyCodeClass legacy = PowerMockito.mock(LegacyCodeClass.class);
		PowerMockito.when(legacy.getFinalMessage()).thenReturn("Mocked final method call result");
		System.out.println(legacy.getFinalMessage());

	}

	@Test
	public void testGetLineFromFileLegacyMethod() throws Exception {
		PowerMockito.when(legacy.getStringFromFile()).thenReturn("Mocked getStringFromFile method call result");
		cons.useGetStringFromFile();
	}

}
