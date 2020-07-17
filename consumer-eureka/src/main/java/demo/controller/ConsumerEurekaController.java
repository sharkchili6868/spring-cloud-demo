package demo.controller;

import demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class ConsumerEurekaController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consume/{id}")
    public String getUser(@PathVariable int id) {
        ResponseEntity<String> result = restTemplate.getForEntity("http://spring-cloud-producer/api/produce/" + id, String.class);
        return result.getBody();
    }

    @PostMapping("/consume")
    public String postUser(@RequestBody User user) {
        ResponseEntity<String> result = restTemplate.postForEntity("http://spring-cloud-producer/api/produce", user, String.class);
        return result.getBody();
    }
}
