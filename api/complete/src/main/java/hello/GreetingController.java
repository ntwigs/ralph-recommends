package hello;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import controller.Start;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @RequestMapping("/")
    public HashMap<String, Double> greeting(@RequestParam(value = "id", defaultValue = "") int id) throws IOException {
        Start start = new Start(id);
        return start.now();
    }
}
