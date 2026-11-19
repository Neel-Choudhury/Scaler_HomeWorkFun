package com.example.cfg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.DemoApplicationTests;

public class ExceptionHandleModuleTest extends DemoApplicationTests {

	@InjectMocks
	private ExceptionHandleModule globalExceptionHandler; // Assuming this is your handler class

	@Mock
	private WebRequest webRequest; // Mocking WebRequest

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this); // Initialize mocks
	}

	@Test
	public void testglobalExceptionHandler() {
		SampleException exception = new SampleException("Sample exception occurred"); 
		ResponseEntity<ErrorMessage> responseEntity = globalExceptionHandler.globalExceptionHandler(exception,webRequest);

		// Assert
		assertNotNull(responseEntity);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
		assertEquals(500, responseEntity.getBody().getValue());
		assertNotNull(responseEntity.getBody().getDate());
		assertEquals("Sample exception occurred", responseEntity.getBody().getMessage());
	}
}
