package arctic.tn.mohamedalirepo.service;

import arctic.tn.mohamedalirepo.client.ProjectWorkflow;
import arctic.tn.mohamedalirepo.client.Respons;
import arctic.tn.mohamedalirepo.entity.Entreprise;

import java.util.List;

public interface InterfaceEntreprise {

    public Entreprise addnewEntreprise(Entreprise entreprise);
    public Entreprise editEntreprise(Entreprise entreprise);
    public Entreprise getEntreprisebyId(Long id);
    public List<Entreprise> getAllEntreprise();
    public void deleteEntreprise(Long id);
    public List<Entreprise> getEntrepriseName(String name);
    Respons getProject(Long user);


}
