
package com.uchyy.portfolio.controller;

import com.uchyy.portfolio.model.Enquiry;
import com.uchyy.portfolio.service.EnquiryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enquiries")
public class EnquiryController {

    private final EnquiryService enquiryService;

    public EnquiryController(EnquiryService enquiryService) {
        this.enquiryService = enquiryService;
    }

    // Get all enquiries, with optional filtering and sorting
    @GetMapping
    public List<Enquiry> getEnquiries(
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "date") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {

        return enquiryService.getAllEnquiries(
                status, sortBy, direction
        );
    }

    // Get one enquiry by ID
    @GetMapping("/{id}")
    public Enquiry getEnquiryById(@PathVariable String id) {
        return enquiryService.getEnquiryById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Enquiry createEnquiry(@RequestBody Enquiry enquiry) {
        return enquiryService.createEnquiry(enquiry);
    }

    @PutMapping("/{id}")
    public Enquiry updateEnquiry(@PathVariable String id, @RequestBody Enquiry enquiry) {

        return enquiryService.updateEnquiry(id, enquiry);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEnquiry(@PathVariable String id) {
        enquiryService.deleteEnquiry(id);
    }

}
