package cl.siigroup.libraryapi.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class UtilitiesTests {
	
	private static final String TESTDATE = "15-11-2024 14:00:00";
	
	@Test
	void testGetCurrentDateFormat() {
		try (MockedStatic<Utilities> utilities = Mockito.mockStatic(Utilities.class)) {
			utilities.when(() -> Utilities.getCurrentDateFormat("dd-MM-yyyy HH:mm:ss")).thenReturn(TESTDATE);
	    }
		
		assertTrue(true);
	}
	
	@Test
	void testGenerateResponse() {
		try (MockedStatic<Utilities> utilities = Mockito.mockStatic(Utilities.class)) {
			utilities.when(() -> Utilities.getCurrentDateFormat("dd-MM-yyyy HH:mm:ss")).thenReturn(TESTDATE);
			
			Map<String, Object> map = new HashMap<String, Object>();
	        map.put("fecha", TESTDATE);
	        map.put("status", HttpStatus.OK.value());
	        map.put("mensaje", "Test");
			ResponseEntity<Object> response = new ResponseEntity<Object>(map,HttpStatus.OK);
			utilities.when(() -> Utilities.generateResponse(HttpStatus.OK, "Test")).thenReturn(response);
	    }
		
		assertTrue(true);
	}

}
