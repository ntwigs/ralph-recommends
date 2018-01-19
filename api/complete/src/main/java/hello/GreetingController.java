package hello;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicLong;

import controller.Start;
import controller.Users;
import model.UserDB;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/")
    public HashMap<String, Double> greeting(@RequestParam(value = "id", defaultValue = "") int id) throws IOException {
        Start start = new Start(id);
        return start.now();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/recs")
    public HashMap<Integer, Double> notgreeting(@RequestParam(value = "id", defaultValue = "") int id) throws IOException {
        Start start = new Start(id);
        return start.recUsers();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/users")
    public HashSet<Integer> users() throws IOException {
        Users users = new Users();
        return users.getUsers();
    }
}
