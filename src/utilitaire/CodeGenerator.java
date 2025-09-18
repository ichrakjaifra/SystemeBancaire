package utilitaire;

import java.util.Random;
import java.util.Set;

public class CodeGenerator {
    
    private static final Random random = new Random();
    
    public static String genererCodeCompte(Set<String> codesExistants) {
        String code;
        do {
            int nombre = random.nextInt(90000) + 10000; // Génère un nombre entre 10000 et 99999
            code = "CPT-" + nombre;
        } while (codesExistants.contains(code));
        
        return code;
    }
}
