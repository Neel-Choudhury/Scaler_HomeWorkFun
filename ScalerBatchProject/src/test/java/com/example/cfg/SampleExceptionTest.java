package com.example.cfg;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import com.example.demo.DemoApplicationTests;


public class SampleExceptionTest extends DemoApplicationTests{
	
	@Test
	public void testSampleException() {
		SampleException sampleException = new SampleException("sample exception message");
		assertTrue(sampleException instanceof SampleException);
	}
}
