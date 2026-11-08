
package com.uchyy.portfolio.service;

import com.uchyy.portfolio.model.Enquiry;
import com.uchyy.portfolio.model.EnquiryStatus;
import com.uchyy.portfolio.repository.EnquiryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class EnquiryService {

    private final EnquiryRepository enquiryRepository;

    public EnquiryService(EnquiryRepository enquiryRepository) {
        this.enquiryRepository = enquiryRepository;
    }

    // Get all enquiries, with optional filtering and sorting
    public List<Enquiry> getAllEnquiries(String status, String sortBy, String direction) {

        try {
            var enquiries = enquiryRepository.findAll().stream();

            if (status != null && !status.isBlank()) {
                enquiries = enquiries.filter(enquiry ->  enquiry.getStatus().name().equalsIgnoreCase(status));
            }

            Comparator<Enquiry> comparator;

            if ("name".equalsIgnoreCase(sortBy)) {
                comparator = Comparator.comparing(
                        Enquiry::getName,
                        String.CASE_INSENSITIVE_ORDER
                );
            } else {
                comparator = Comparator.comparing(Enquiry::getCreatedAt);
            }

            if (!"asc".equalsIgnoreCase(direction)) {
                comparator = comparator.reversed();
            }

            return enquiries.sorted(comparator).toList();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw databaseError(e);
        } catch (ExecutionException e) {
            throw databaseError(e);
        }
    }

    // Get one enquiry
    public Enquiry getEnquiryById(String id) {
        try {
            return enquiryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Enquiry not found: " + id));

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw databaseError(e);
        } catch (ExecutionException e) {
            throw databaseError(e);
        }
    }

    // Create a new enquiry
    public Enquiry createEnquiry(Enquiry enquiry) {
        try {
            String now = Instant.now().toString();

            enquiry.setId(UUID.randomUUID().toString());
            enquiry.setStatus(EnquiryStatus.NEW);
            enquiry.setCreatedAt(now);
            enquiry.setUpdatedAt(now);

            return enquiryRepository.save(enquiry);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw databaseError(e);
        } catch (ExecutionException e) {
            throw databaseError(e);
        }
    }

    // Update an existing enquiry
    public Enquiry updateEnquiry(String id, Enquiry updatedEnquiry) {
        try {
            Enquiry existing = enquiryRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Enquiry not found: " + id));

            existing.setName(updatedEnquiry.getName());
            existing.setEmail(updatedEnquiry.getEmail());
            existing.setSubject(updatedEnquiry.getSubject());
            existing.setMessage(updatedEnquiry.getMessage());
            existing.setStatus(updatedEnquiry.getStatus());
            existing.setUpdatedAt(Instant.now().toString());

            return enquiryRepository.save(existing);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw databaseError(e);
        } catch (ExecutionException e) {
            throw databaseError(e);
        }
    }

    // Delete an enquiry
    public void deleteEnquiry(String id) {
        try {
            boolean deleted = enquiryRepository.deleteById(id);

            if (!deleted) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Enquiry not found: " + id);
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw databaseError(e);
        } catch (ExecutionException e) {
            throw databaseError(e);
        }
    }

    // Handle database failures
    private ResponseStatusException databaseError(Exception e) {
        return new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "A database error occurred",
                e
        );
    }
}
