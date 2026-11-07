
package com.uchyy.portfolio.service;

import com.uchyy.portfolio.model.Enquiry;
import com.uchyy.portfolio.model.EnquiryStatus;
import com.uchyy.portfolio.repository.EnquiryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;

@Service
public class EnquiryService {

    private final EnquiryRepository enquiryRepository;

    public EnquiryService(EnquiryRepository enquiryRepository) {
        this.enquiryRepository = enquiryRepository;
    }

    // Get all enquiries, with optional filtering and sorting
    public List<Enquiry> getAllEnquiries(String status, String sortBy, String direction) {

        var enquiries = enquiryRepository.findAll().stream();

        if (status != null && !status.isBlank()) {
            enquiries = enquiries.filter(enquiry ->
                    enquiry.getStatus().name()
                            .equalsIgnoreCase(status));
        }

        Comparator<Enquiry> comparator;

        if ("name".equalsIgnoreCase(sortBy)) {
            comparator = Comparator.comparing(
                    Enquiry::getName,
                    String.CASE_INSENSITIVE_ORDER
            );
        } else {
            comparator = Comparator.comparing( Enquiry::getCreatedAt );
        }

        if ("asc".equalsIgnoreCase(direction)) {
            return  enquiries.sorted(comparator).toList();
        } else {
            comparator = comparator.reversed();
        }

        return enquiries.sorted(comparator).toList();
    }

    // Get one enquiry
    public Enquiry getEnquiryById(String id) {
        return enquiryRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Enquiry not found: " + id));
    }

    public Enquiry createEnquiry(Enquiry enquiry) {
        enquiry.setId(java.util.UUID.randomUUID().toString());
        enquiry.setStatus(EnquiryStatus.NEW);
        enquiry.setCreatedAt(java.time.Instant.now().toString());
        return enquiryRepository.save(enquiry);
    }

    public Enquiry updateEnquiry(String id, Enquiry updatedEnquiry) {

        Enquiry existing = enquiryRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Enquiry not found: " + id));

        existing.setName(updatedEnquiry.getName());
        existing.setEmail(updatedEnquiry.getEmail());
        existing.setSubject(updatedEnquiry.getSubject());
        existing.setMessage(updatedEnquiry.getMessage());
        existing.setStatus(updatedEnquiry.getStatus());

        return existing;
    }

    public void deleteEnquiry(String id) {

        boolean deleted = enquiryRepository.deleteById(id);

        if (!deleted) {
            throw new RuntimeException("Enquiry not found: " + id);
        }
    }
}
