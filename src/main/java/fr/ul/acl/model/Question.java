
package fr.ul.acl.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {

    public enum TypeQuestion {
        QCM,
        VRAI_FAUX
    }

    public enum Categorie {
        MATHEMATIQUES,
        CULTURE_GENERALE,
        ACL
    }

    private String enonce;
    private TypeQuestion type;
    private Categorie categorie;
    private List<String> propositions;
    private String bonneReponse;

    public Question(String enonce,
                    TypeQuestion type,
                    Categorie categorie,
                    List<String> propositions,
                    String bonneReponse) {

        if (enonce == null || enonce.trim().isEmpty()) {
            throw new IllegalArgumentException("L'énoncé est obligatoire.");
        }

        if (type == null || categorie == null) {
            throw new IllegalArgumentException("Type et catégorie obligatoires.");
        }

        if (bonneReponse == null || bonneReponse.trim().isEmpty()) {
            throw new IllegalArgumentException("La bonne réponse est obligatoire.");
        }

        this.enonce = enonce;
        this.type = type;
        this.categorie = categorie;
        this.propositions = new ArrayList<>(propositions);
        this.bonneReponse = bonneReponse;

        verifierQuestion();
    }

    private void verifierQuestion() {

        if (type == TypeQuestion.QCM && propositions.size() < 2) {
            throw new IllegalArgumentException(
                    "Un QCM doit avoir au moins deux propositions."
            );
        }

        if (type == TypeQuestion.VRAI_FAUX) {
            if (!bonneReponse.equalsIgnoreCase("Vrai")
                    && !bonneReponse.equalsIgnoreCase("Faux")) {

                throw new IllegalArgumentException(
                        "La réponse doit être Vrai ou Faux."
                );
            }
        }
    }

    public boolean verifierReponse(String reponse) {

        if (reponse == null) {
            return false;
        }

        return bonneReponse.equalsIgnoreCase(reponse.trim());
    }

    public String getEnonce() {
        return enonce;
    }

    public TypeQuestion getType() {
        return type;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public List<String> getPropositions() {
        return Collections.unmodifiableList(propositions);
    }

    public String getBonneReponse() {
        return bonneReponse;
    }
}
