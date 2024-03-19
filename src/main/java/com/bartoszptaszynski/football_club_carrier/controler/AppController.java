package com.bartoszptaszynski.football_club_carrier.controler;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.View;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AppController {
    @GetMapping("/xD")
    public ResponseEntity<String> login(){
        Map<String,String> map=new HashMap<>();
        map.put("text","XDDD");
        return new ResponseEntity<String>("XDDD", HttpStatus.OK);
    }
}
