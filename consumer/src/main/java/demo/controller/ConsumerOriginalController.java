package demo.controller;

import demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class ConsumerOriginalController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consume/{id}")
    public String getUser(@PathVariable int id) {
        ResponseEntity<String> result = restTemplate.getForEntity("http://localhost:9904/api/produce/" + id, String.class);
        return result.getBody();
    }

    @PostMapping("/consume")
    public String postUser(@RequestBody User user) {
        ResponseEntity<String> result = restTemplate.postForEntity("http://localhost:9904/api/produce", user, String.class);
        return result.getBody();
    }
}
