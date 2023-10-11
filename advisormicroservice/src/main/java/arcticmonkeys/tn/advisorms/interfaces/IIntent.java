package arcticmonkeys.tn.advisorms.interfaces;

import arcticmonkeys.tn.advisorms.models.Document;
import arcticmonkeys.tn.advisorms.models.Intent;

import java.util.List;

public interface IIntent {
    Intent createIntent(Intent intent);
    Intent updateIntent(Intent intent);
    Intent getIntentById(Integer id);
    void deleteIntent(Integer id);
    List<Intent> getAll();
    String updateIntent();

}
