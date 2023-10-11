package arcticmonkeys.tn.advisorms.services;

import arcticmonkeys.tn.advisorms.externalapi.NeonClient;
import arcticmonkeys.tn.advisorms.interfaces.IIntent;
import arcticmonkeys.tn.advisorms.models.Intent;
import arcticmonkeys.tn.advisorms.models.Message;
import arcticmonkeys.tn.advisorms.repository.IntentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntentService implements IIntent {
    private IntentRepo intentRepo;
    private NeonClient neonClient;
    public IntentService(IntentRepo intentRepo,NeonClient neonClient){

        this.intentRepo=intentRepo;
        this.neonClient=neonClient;
    }
    @Override
    public Intent createIntent(Intent intent) {
        return intentRepo.save(intent);
    }

    @Override
    public Intent updateIntent(Intent intent) {
        return intentRepo.save(intent);
    }

    @Override
    public Intent getIntentById(Integer id) {
        return intentRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteIntent(Integer id) {
        Intent m=intentRepo.findById(id).orElse(null);
        if (m!=null){
            intentRepo.delete(m);
        }
    }

    @Override
    public List<Intent> getAll() {
        return intentRepo.findAll();

    }

    @Override
    public String updateIntent() {
        List<Intent> l=intentRepo.findAll();
        System.out.println(l.get(0).getPatterns());
        String res=neonClient.update(l);
        return res;
    }
}
