package com.bartoszptaszynski.football_club_carrier;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class SuccessJsonResponse  {
    public static ResponseEntity<?> getSuccessJson(Object o)
    {
        return new ResponseEntity<>(
                        Map.entry("success",o),
                        HttpStatus.OK
        );
    }
}
