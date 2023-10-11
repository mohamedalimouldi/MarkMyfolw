package arcticmonkeys.tn.advisorms.externalapi;

import arcticmonkeys.tn.advisorms.models.Intent;
import arcticmonkeys.tn.advisorms.models.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Set;

@FeignClient(name = "neon-client", url = "http://127.0.0.1:5000")
public interface NeonClient {
    @GetMapping("/message/{id}")
    String search(@PathVariable("id") String id);

    @PostMapping("/update-intents")
    String update(@RequestBody List<Intent> sk);
}
