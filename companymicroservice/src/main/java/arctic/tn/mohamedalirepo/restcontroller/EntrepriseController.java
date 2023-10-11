package arctic.tn.mohamedalirepo.restcontroller;

import arctic.tn.mohamedalirepo.client.Respons;
import arctic.tn.mohamedalirepo.entity.Entreprise;
import arctic.tn.mohamedalirepo.service.InterfaceEntreprise;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/entreprise")
public class EntrepriseController  {
   InterfaceEntreprise interfaceEntreprise;
   @PostMapping("/add")
    public Entreprise addnewEntreprise(@RequestBody Entreprise entreprise) {
        return interfaceEntreprise.addnewEntreprise(entreprise);
    }

    @PutMapping("/update")
    public Entreprise editEntreprise(@RequestBody Entreprise entreprise) {
        return interfaceEntreprise.editEntreprise(entreprise);
    }
    @GetMapping("/findbyId/{id}")
    public Entreprise getEntreprisebyId(@PathVariable("id") Long id) {
        return interfaceEntreprise.getEntreprisebyId(id);
    }
    @GetMapping("/getAll")
    public List<Entreprise> getAllEntreprise() {
        return interfaceEntreprise.getAllEntreprise();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEntreprise(@PathVariable("id") Long id) {
        interfaceEntreprise.deleteEntreprise(id);
    }
    @GetMapping("/company/{id}")
    public ResponseEntity<Respons> getProject(@PathVariable("id") Long id){
       Respons respons = interfaceEntreprise.getProject(id);
       return ResponseEntity.ok(respons);
    }
    @GetMapping("/companyName")
    List<Entreprise> findNameuser(@RequestParam("name") String name){
       return interfaceEntreprise.getEntrepriseName(name);
    }


}
