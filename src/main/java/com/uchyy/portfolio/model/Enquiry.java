
package com.uchyy.portfolio.model;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Enquiry {

    private String id;

    @NotBlank(message = "Name is required.")
    @Size(max = 100, message = "Name cannot exceed 100 characters.")
    private String name;

    @NotBlank(message = "Email is required.")
    @Email(message = "Please provide a valid email address.")
    private String email;

    @NotBlank(message = "Subject is required.")
    @Size(max = 150, message = "Subject cannot exceed 150 characters.")
    private String subject;

    @NotBlank(message = "Message is required.")
    @Size(max = 2000, message = "Message cannot exceed 2000 characters.")
    private String message;

    private EnquiryStatus status;
    private String createdAt;
    private String updatedAt;

    public Enquiry() {
    }

    public Enquiry(
            String id,
            String name,
            String email,
            String subject,
            String message,
            EnquiryStatus status,
            String createdAt,
            String updatedAt

    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.message = message;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EnquiryStatus getStatus() {
        return status;
    }

    public void setStatus(EnquiryStatus status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {return updatedAt; }

    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
}
