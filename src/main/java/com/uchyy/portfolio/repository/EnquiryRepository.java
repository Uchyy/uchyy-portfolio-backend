
package com.uchyy.portfolio.repository;

import com.uchyy.portfolio.data.SampleEnquiry;
import com.uchyy.portfolio.model.Enquiry;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EnquiryRepository {

    private final List<Enquiry> enquiries = new ArrayList<>(SampleEnquiry.getEnquiries());

    public List<Enquiry> findAll() {
        return enquiries;
    }

    public Optional<Enquiry> findById(String id) {
        return enquiries.stream().filter(enquiry -> enquiry.getId().equals(id)).findFirst();
    }

    public Enquiry save(Enquiry enquiry) {
        for (int i = 0; i < enquiries.size(); i++) {

            if (enquiries.get(i).getId().equals(enquiry.getId())) {
                enquiries.set(i, enquiry);
                return enquiry;
            }
        }

        enquiries.add(enquiry);
        return enquiry;
    }

    public boolean deleteById(String id) {
        return enquiries.removeIf( enquiry -> enquiry.getId().equals(id));
    }
}
