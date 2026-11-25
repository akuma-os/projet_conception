
package fr.ul.acl.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Questionnaire {

    private String nom;
    private Question.Categorie categorie;
    private List<Question> questions;

    private Question questionActuelle;
    private boolean reussi;

    private Random random;

    public Questionnaire(String nom,
                        Question.Categorie categorie,
                        List<Question> questions) {

        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom est obligatoire.");
        }

        if (categorie == null) {
            throw new IllegalArgumentException("La catégorie est obligatoire.");
        }

        if (questions == null || questions.isEmpty()) {
            throw new IllegalArgumentException(
                    "Le questionnaire doit contenir des questions."
            );
        }

        this.nom = nom;
        this.categorie = categorie;
        this.questions = new ArrayList<>(questions);

        this.random = new Random();
        this.reussi = false;

        verifierCategories();
        choisirQuestion();
    }

    private void verifierCategories() {

        for (Question question : questions) {

            if (question.getCategorie() != categorie) {
                throw new IllegalArgumentException(
                        "Toutes les questions doivent appartenir "
                        + "à la catégorie du questionnaire."
                );
            }
        }
    }

    private void choisirQuestion() {

        if (questions.size() == 1) {
            questionActuelle = questions.get(0);
            return;
        }

        Question nouvelleQuestion;

        do {
            int indice = random.nextInt(questions.size());
            nouvelleQuestion = questions.get(indice);

        } while (nouvelleQuestion == questionActuelle);

        questionActuelle = nouvelleQuestion;
    }

    public Question getQuestionActuelle() {
        return questionActuelle;
    }

    public boolean repondre(String reponse) {

        if (reussi) {
            return true;
        }

        boolean correcte = questionActuelle.verifierReponse(reponse);

        if (correcte) {
            reussi = true;
        } else {
            choisirQuestion();
        }

        return correcte;
    }

    public boolean estReussi() {
        return reussi;
    }

    public String getNom() {
        return nom;
    }

    public Question.Categorie getCategorie() {
        return categorie;
    }
}
