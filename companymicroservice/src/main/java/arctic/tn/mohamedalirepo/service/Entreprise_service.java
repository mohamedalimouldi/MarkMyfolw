package arctic.tn.mohamedalirepo.service;

import arctic.tn.mohamedalirepo.client.EntrepriseDoto;
import arctic.tn.mohamedalirepo.client.ProjectWorkflow;
import arctic.tn.mohamedalirepo.client.Respons;
import arctic.tn.mohamedalirepo.entity.Entreprise;
import arctic.tn.mohamedalirepo.entity.Subscription;
import arctic.tn.mohamedalirepo.repository.EntrepriseRepo;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor

public class Entreprise_service implements InterfaceEntreprise {

    EntrepriseRepo entrepriseRepo;
    private RestTemplate restTemplate;
    @Override
    public Entreprise addnewEntreprise(Entreprise entreprise) {
        return entrepriseRepo.save(entreprise);
    }

    @Override
    public Entreprise editEntreprise(Entreprise entreprise) {
        return entrepriseRepo.save(entreprise);
    }

    @Override
    public Entreprise getEntreprisebyId(Long id) {
        return entrepriseRepo.findById(id).orElse(null);
    }

    @Override
    public List<Entreprise> getAllEntreprise() {
        return entrepriseRepo.findAll();
    }

    @Override
    public void deleteEntreprise(Long id) {
     entrepriseRepo.deleteById(id);
    }

    @Override
    public List<Entreprise> getEntrepriseName(String nameuser) {
        return entrepriseRepo.findByName(nameuser);
    }

    @Override
    public Respons getProject(Long id) {
        Respons respons = new Respons();
        Entreprise entreprise = entrepriseRepo.findById(id).get();
        EntrepriseDoto entrepriseDoto = mapToEntreprise(entreprise);
        ResponseEntity<List<ProjectWorkflow>> responseEntity = restTemplate
                .exchange("http://localhost:8000/workflowservice/projectworkflow/getAll/" + entreprise.getId(),
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<ProjectWorkflow>>() {});
        List<ProjectWorkflow> projectWorkflowList = responseEntity.getBody();
        List<ProjectWorkflow> projectWorkflow = responseEntity.getBody();
        System.out.println(responseEntity.getStatusCode());
        respons.setProjectWorkflow(projectWorkflow);

        return respons;

    }
    private EntrepriseDoto mapToEntreprise(Entreprise entreprise){
        EntrepriseDoto entrepriseDoto = new EntrepriseDoto();
        entrepriseDoto.setId(entreprise.getId());
        entrepriseDoto.setCodefiscal(entreprise.getCodefiscal());
        entrepriseDoto.setAddress(entreprise.getAddress());
        entrepriseDoto.setName(entreprise.getName());
        entrepriseDoto.setEmail(entreprise.getEmail());
        return entrepriseDoto;
    }


}
