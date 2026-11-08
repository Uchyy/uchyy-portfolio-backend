
package com.uchyy.portfolio.service;

import com.uchyy.portfolio.repository.EnquiryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EnquiryServiceTest {

    @Mock
    private EnquiryRepository enquiryRepository;

    @InjectMocks
    private EnquiryService enquiryService;

    @BeforeEach
    void setUp() {
        // Mockito creates the mock repository
        // and injects it into EnquiryService.
    }

    @Test
    void shouldGetAllEnquiries() {
        // We'll write our first test here.
    }
}
