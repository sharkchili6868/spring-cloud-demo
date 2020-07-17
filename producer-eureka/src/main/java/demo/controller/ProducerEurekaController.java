package demo.controller;

import demo.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProducerEurekaController {

    @GetMapping("produce/{id}")
    public String getUser(@PathVariable int id)
    {
        System.out.println("producer called");
        return "chris" + id;
    }

    @PostMapping("/produce")
    public String postUser(@RequestBody User user) {
        return user.getId() + user.getName();
    }
}
