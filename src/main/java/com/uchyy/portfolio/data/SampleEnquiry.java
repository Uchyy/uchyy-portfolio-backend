
package com.uchyy.portfolio.data;

import com.uchyy.portfolio.model.Enquiry;
import com.uchyy.portfolio.model.EnquiryStatus;

import java.util.List;

public class SampleEnquiry {

    public static List<Enquiry> getEnquiries() {

        return List.of(
                new Enquiry(
                        "enquiry-001",
                        "Alex Morgan",
                        "alex@example.com",
                        "Songwriting Collaboration",
                        "I'd love to discuss a possible songwriting collaboration.",
                        EnquiryStatus.NEW,
                        "2026-11-01"
                ),

                new Enquiry(
                        "enquiry-002",
                        "Jordan Lee",
                        "jordan@example.com",
                        "Music Licensing",
                        "I'm interested in discussing licensing one of your songs.",
                        EnquiryStatus.IN_PROGRESS,
                        "2026-11-02"
                )
        );
    }
}
