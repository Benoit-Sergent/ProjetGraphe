package CreneauxSecteurs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AlgorithmeColorationSecteurs {
/**
    public void colorerSecteurs(List<Secteur> secteurs) {
        if (secteurs == null || secteurs.isEmpty()) {
            return;
        }

        for (Secteur s : secteurs) {
            s.setCouleur("Lundi");
        }

        for (Secteur secteur : secteurs) {
            Set<Integer> couleursUtilisees = new HashSet<>();
            for (Secteur voisin : secteur.getSecteursVoisins()) {
                int c = voisin.getJour();
                if (c >= 0) {
                    couleursUtilisees.add(c);
                }
            }
            int couleur = 0;
            while (couleursUtilisees.contains(couleur)) {
                couleur++;
            }
            secteur.setCouleur(couleur);
        }
    }
 **/
}
