package com.spring.data.websocket.service;
 
import java.util.HashMap;
import java.util.Map;
 
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
 
 
import reactor.core.publisher.Mono;
 
@Service
public class ApiService {
 
    private final WebClient projectManagementClient;
    private final WebClient admetClient;
 
    @Value("${project.management.subscription.key}")
    private String subscriptionKey;
 
    @Value("${admet.subscription.key}")
    private String admetSubscriptionKey;
 
    public ApiService(WebClient.Builder webClientBuilder) {
        this.projectManagementClient = webClientBuilder
            .baseUrl("https://caitapimus.azure-api.net")
            .filter(logRequest())
            .filter(logResponse())
            .build();
        this.admetClient = webClientBuilder
        		.baseUrl("https://caitapimus.azure-api.net")
        		.filter(logRequest())
        		.filter(logResponse())
        		.build();
    }
 
    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            System.out.println("Request: " + clientRequest.method() + " " + clientRequest.url());
            clientRequest.headers().forEach((name, values) -> values.forEach(value -> System.out.println(name + ": " + value)));
            return Mono.just(clientRequest);
        });
    }
 
    private ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            System.out.println("Response: " + clientResponse.statusCode());
            clientResponse.headers().asHttpHeaders().forEach((name, values) -> values.forEach(value -> System.out.println(name + ": " + value)));
            return Mono.just(clientResponse);
        });
    }
 
    
    public String getResult(MultipartFile file1,MultipartFile file2) {
        Map<String, MultipartFile> requestBody = new HashMap<>();
        requestBody.put("file1", file1);
        requestBody.put("file1", file2);
 
        try {
        	System.out.println("Printing: " + admetClient.post()
                    .uri("/api/v1/pyrmd")
                    .headers(httpHeaders -> {
                        httpHeaders.set("Ocp-Apim-Subscription-Key", admetSubscriptionKey);
                    })
                    .body(BodyInserters.fromValue(requestBody))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block());
            String response = admetClient.post()
                .uri("/api/v1/pyrmd")
                .headers(httpHeaders -> {
                    httpHeaders.set("Ocp-Apim-Subscription-Key", admetSubscriptionKey);
                })
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .bodyToMono(String.class)
                .block();
 
            //System.out.println("GetRequestResponseDTO: " + response.getResult().getResult().getBbb_color() + " " + response.getResult().getSmile());
            return response;
 
        } catch (WebClientResponseException e) {
            System.err.println("Status code: " + e.getStatusCode());
            System.err.println("Response body: " + e.getResponseBodyAsString());
        } catch (WebClientRequestException e) {
            System.err.println("Request error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
 
        return null;
    }
    
    public String getResult1() {
 
        try {
        	System.out.println("Printing: " + admetClient.post()
                    .uri("/api/v1/pyrmd/tasks")
                    .headers(httpHeaders -> {
                        httpHeaders.set("Ocp-Apim-Subscription-Key", admetSubscriptionKey);
                    })
                    .retrieve()
                    .bodyToMono(String.class)
                    .block());
            String response = admetClient.post()
                .uri("/api/v1/pyrmd/tasks")
                .headers(httpHeaders -> {
                    httpHeaders.set("Ocp-Apim-Subscription-Key", admetSubscriptionKey);
                })
                .retrieve()
                .bodyToMono(String.class)
                .block();
 
            //System.out.println("GetRequestResponseDTO: " + response.getResult().getResult().getBbb_color() + " " + response.getResult().getSmile());
            return response;
 
        } catch (WebClientResponseException e) {
            System.err.println("Status code: " + e.getStatusCode());
            System.err.println("Response body: " + e.getResponseBodyAsString());
        } catch (WebClientRequestException e) {
            System.err.println("Request error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
 
        return null;
    }
    
    public String getResult2(String id) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("id", id);
 
        try {
        	System.out.println("Printing: " + admetClient.post()
                    .uri("/api/v1/pyrmd/results")
                    .headers(httpHeaders -> {
                        httpHeaders.set("Ocp-Apim-Subscription-Key", admetSubscriptionKey);
                    })
                    .body(BodyInserters.fromValue(requestBody))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block());
            String response = admetClient.post()
                .uri("/api/v1/pyrmd/results")
                .headers(httpHeaders -> {
                    httpHeaders.set("Ocp-Apim-Subscription-Key", admetSubscriptionKey);
                })
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .bodyToMono(String.class)
                .block();
 
            //System.out.println("GetRequestResponseDTO: " + response.getResult().getResult().getBbb_color() + " " + response.getResult().getSmile());
            return response;
 
        } catch (WebClientResponseException e) {
            System.err.println("Status code: " + e.getStatusCode());
            System.err.println("Response body: " + e.getResponseBodyAsString());
        } catch (WebClientRequestException e) {
            System.err.println("Request error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
 
        return null;
    }
}