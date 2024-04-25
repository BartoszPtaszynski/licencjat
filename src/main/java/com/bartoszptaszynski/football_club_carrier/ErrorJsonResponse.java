package com.bartoszptaszynski.football_club_carrier;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ErrorJsonResponse {
    public static ResponseEntity<?> getErrorJson(Object o)
    {
        return new ResponseEntity<>(
                        Map.entry("error",o),
                        HttpStatus.BAD_REQUEST
        );
    }
}
