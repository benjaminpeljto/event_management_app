package ba.ibu.edu.web_engineering_project.rest.controllers;

import ba.ibu.edu.web_engineering_project.core.service.EventService;
import ba.ibu.edu.web_engineering_project.core.service.JwtService;
import ba.ibu.edu.web_engineering_project.core.service.S3Service;
import ba.ibu.edu.web_engineering_project.rest.dto.EventDTO;
import ba.ibu.edu.web_engineering_project.rest.dto.EventRequestDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("api/events")
public class EventController {

    private final EventService eventService;
    private final S3Service s3Service;
    private final JwtService jwtService;

    public EventController(EventService eventService, S3Service s3Service, JwtService jwtService) {
        this.eventService = eventService;
        this.s3Service = s3Service;
        this.jwtService = jwtService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<List<EventDTO>> getEvents(){
        return ResponseEntity.ok(eventService.getEvents());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ongoing")
    public ResponseEntity<List<EventDTO>> getOngoingEvents(){
        return ResponseEntity.ok(eventService.getOngoingEvents());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ongoing/type/{eventType}")
    public ResponseEntity<List<EventDTO>> getOngoingEventsByType(@PathVariable String eventType){
        return ResponseEntity.ok(eventService.getOngoingEventsByType(eventType));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ongoing/keyword/{keyword}")
    public ResponseEntity<List<EventDTO>> getOngoingEventsByKeyword(@PathVariable String keyword){
        return ResponseEntity.ok(eventService.getOngoingEventsByKeyword(keyword));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/pending")
    public ResponseEntity<List<EventDTO>> getPendingEvents(){
        return ResponseEntity.ok(eventService.getPendingEvents());
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/approve/{id}")
    public ResponseEntity<EventDTO> approveEvent(@PathVariable String id){
        return ResponseEntity.ok(eventService.approveEvent(id));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/set-live/{id}")
    public ResponseEntity<EventDTO> onGoEvent(@PathVariable String id){
        return ResponseEntity.ok(eventService.onGoEvent(id));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/cancel/{id}")
    public ResponseEntity<EventDTO> cancelEvent(@PathVariable String id){
        return ResponseEntity.ok(eventService.cancelEvent(id));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable String id){
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/")
    public ResponseEntity<EventDTO> addEvent(
            @RequestBody EventRequestDTO event,
            @RequestHeader(name = HttpHeaders.AUTHORIZATION, required = false) String authorizationHeader
    ){
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String token = authorizationHeader.substring(7);
        String userId = jwtService.extractUserId(token);

        if (userId == null || userId.equals("Null")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        event.setOrganizerId(userId);
        return ResponseEntity.ok(eventService.addEvent(event));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable String id ,@RequestBody EventRequestDTO event){
        return ResponseEntity.ok(eventService.updateEvent(id, event));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable String id){
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.POST, path = "upload-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadImage(@RequestPart("image") MultipartFile file) {
        if (file.isEmpty()) {
            return "Please select a file to upload.";
        }

        // Checking the file type (only PNG and JPEG images are allowed)
        String contentType = file.getContentType();
        if (contentType == null || (!contentType.equals("image/png") && !contentType.equals("image/jpeg"))) {
            return "Invalid file type. Only PNG and JPEG are allowed.";
        }

        String imageUrl = s3Service.putObject(file);
        return imageUrl;

    }


}
