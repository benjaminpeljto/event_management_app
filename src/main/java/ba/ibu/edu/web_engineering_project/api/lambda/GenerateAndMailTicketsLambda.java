package ba.ibu.edu.web_engineering_project.api.lambda;

import ba.ibu.edu.web_engineering_project.core.model.Ticket;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.ConversionException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GenerateAndMailTicketsLambda {

    private final RestTemplate restTemplate;
    private final String getAwsGenerateSendTicketsLambda;


    public GenerateAndMailTicketsLambda(RestTemplate restTemplate, String getAwsGenerateSendTicketsLambda) {
        this.restTemplate = restTemplate;
        this.getAwsGenerateSendTicketsLambda = getAwsGenerateSendTicketsLambda;
    }

    @Async
    public void generateQRAndEmailTickets(String buyerID, String buyerName, String buyerEmail, List<Ticket> tickets){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = createRequestBody(buyerID, buyerName, buyerEmail, tickets);

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        restTemplate.postForEntity(getAwsGenerateSendTicketsLambda, request, String.class).getBody();
    }

    private String createRequestBody(String buyerID, String buyerName, String buyerEmail, List<Ticket> tickets) {
        Map<String, Object> requestBodyMap = new HashMap<>();
        requestBodyMap.put("buyerID", buyerID);
        requestBodyMap.put("buyerName", buyerName);
        requestBodyMap.put("buyerEmail", buyerEmail);

        // Convert Ticket objects to Map for the tickets array
        List<Map<String, Object>> ticketsList = tickets.stream()
                .map(this::ticketToMap)
                .toList();

        requestBodyMap.put("tickets", ticketsList);

        // Convert the map to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(requestBodyMap);
        } catch (JsonProcessingException e) {
            throw new ConversionException("Unable to convert ticket request to JSON for sending.") {
            };
        }
    }


    private Map<String, Object> ticketToMap(Ticket ticket) {
        Map<String, Object> ticketMap = new HashMap<>();
        ticketMap.put("ticketID", ticket.getId());
        ticketMap.put("buyerID", ticket.getBuyerId());
        ticketMap.put("buyerName", ""); // Set to an empty string and assign in createRequestBody function
        ticketMap.put("eventName", ticket.getEvent().getTitle());
        ticketMap.put("ticketType", ticket.getTicketType().name());

        return ticketMap;
    }
}
