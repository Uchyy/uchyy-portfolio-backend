package com.uchyy.portfolio.controller;

import com.google.cloud.firestore.Firestore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
public class FirestoreTestController {

    private final Firestore firestore;

    public FirestoreTestController(Firestore firestore) {
        this.firestore = firestore;
    }

    @GetMapping("/api/firestore-test")
    public Map<String, String> testFirestore()
            throws ExecutionException, InterruptedException {

        Map<String, Object> testData = Map.of(
                "message", "Hello from Spring Boot",
                "project", "Uchyy Portfolio",
                "status", "connected"
        );

        firestore.collection("connection_tests")
                .document("first-test")
                .set(testData)
                .get();

        return Map.of(
                "status", "success",
                "message", "Data successfully written to Firestore"
        );
    }
}