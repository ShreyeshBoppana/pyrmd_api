package com.spring.data.websocket.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.JsonPath;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import com.spring.data.websocket.service.ApiService;


@RestController
public class ClientController {
	
    
    @Autowired
    private ApiService apiService;
    
    
    @PostMapping("/pyrmd")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file1") MultipartFile file1,
            @RequestParam("file2") MultipartFile file2) {

        
            String originalFileName1 = file1.getOriginalFilename();
            if (originalFileName1 == null || !originalFileName1.endsWith(".csv") ) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Invalid file format. Please upload a .csv file");
            }

            //String newFileName1 = originalFileName1.substring(0, originalFileName1.lastIndexOf('.')) + ".csv";
            
            String originalFileName2 = file2.getOriginalFilename();
            if (originalFileName2 == null || !originalFileName2.endsWith(".smi") ) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Invalid file format. Please upload a .smi file.");
            }

            //String newFileName2 = originalFileName2.substring(0, originalFileName2.lastIndexOf('.')) + ".smi";

            // Upload file to Azure Blob Storage
            
	    	String result = apiService.getResult(file1,file2);
	    	
	    	if(result == null)
	    	{
	    		return ResponseEntity.status(HttpStatus.NOT_FOUND)
	    				.body("Error!!, result not found");
	    	}
	    	
	        return ResponseEntity.status(HttpStatus.OK)
	        		.body(result);
            
        
    }
    
    
    @GetMapping("/pyrmd/tasks")
    public ResponseEntity<String> getTasks() {

        
            // Upload file to Azure Blob Storage
            
          //Optional<AdmetData> admetData = admetDataService.findBySmile(getResultRequestDTO.getSmile());
	    	String result = apiService.getResult1();
	        //System.out.println(admetData.getSmile());
	    	
	    	if(result == null)
	    	{
	    		return ResponseEntity.status(HttpStatus.NOT_FOUND)
	    				.body("Error!!, result not found");
	    	}
	    	
	        return ResponseEntity.status(HttpStatus.OK)
	        		.body(result);
            
            
    }
    
    @GetMapping("/pyrmd/{task_id}")
    public ResponseEntity<String> GetResult(
            @PathVariable String task_id) {

        
	    	String result = apiService.getResult2(task_id);
	        //System.out.println(admetData.getSmile());
	    	
	    	if(result == null)
	    	{
	    		return ResponseEntity.status(HttpStatus.NOT_FOUND)
	    				.body("Error!!, SMILE not found");
	    	}
	    	
	        return ResponseEntity.status(HttpStatus.OK)
	        		.body(result);
            
            
        
    }
    
}
