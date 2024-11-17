package cl.siigroup.libraryapi.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
* Utilities that get usable functionality such as formatted current date and HTTP request response
* 
* @author Nelson Ramirez
* 
*/

public class Utilities {
	
	private Utilities() {
		throw new IllegalStateException("Utility class");
	}
	
	// Format can be "dd-MM-yyyy HH:mm:ss"
	public static String getCurrentDateFormat(String format) {
		LocalDateTime currentDate = LocalDateTime.now();
		
		return currentDate.format(DateTimeFormatter.ofPattern(format));
	}
	
	public static ResponseEntity<Object> generateResponse(HttpStatus status, String message) {
		String currentDateFormat = Utilities.getCurrentDateFormat("dd-MM-yyyy HH:mm:ss");
		
        Map<String, Object> map = new HashMap<>();
        map.put("fecha", currentDateFormat);
        map.put("status", status.value()); 
        map.put("mensaje", message); 

        return new ResponseEntity<>(map,status);
    }

}
