
package fr.ul.acl.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Random;

public class BanqueQuestions {

    private EnumMap<Question.Categorie, List<Question>> questions;
    private EnumMap<Question.Categorie, List<Question>> questionsRestantes;

    private Random random;
    private Question derniereQuestion;

    public BanqueQuestions() {

        questions = new EnumMap<>(Question.Categorie.class);
        questionsRestantes = new EnumMap<>(Question.Categorie.class);

        random = new Random();
        derniereQuestion = null;

        // Initialisation des catégories
        for (Question.Categorie categorie : Question.Categorie.values()) {
            questions.put(categorie, new ArrayList<Question>());
            questionsRestantes.put(categorie, new ArrayList<Question>());
        }

        // Remplissage des banques
        ajouterQuestionsMathematiques();
        ajouterQuestionsCultureGenerale();

        // ACL sera complété à partir du cours
    }

    // =====================================================
    // AJOUT D'UNE QUESTION
    // =====================================================

    public void ajouterQuestion(Question question) {

        if (question == null) {
            throw new IllegalArgumentException("La question ne peut pas être nulle.");
        }

        Question.Categorie categorie = question.getCategorie();

        questions.get(categorie).add(question);
        questionsRestantes.get(categorie).add(question);
    }

    // =====================================================
    // SELECTION ALEATOIRE
    // =====================================================

    public Question obtenirQuestionAleatoire(
            Question.Categorie categorie) {

        List<Question> banque = questions.get(categorie);
        List<Question> restantes = questionsRestantes.get(categorie);

        if (banque == null || banque.isEmpty()) {
            throw new IllegalStateException(
                    "Aucune question disponible pour la catégorie : "
                    + categorie
            );
        }

        // Toutes les questions ont été posées :
        // on recharge la liste pour recommencer
        if (restantes.isEmpty()) {
            restantes.addAll(banque);
        }

        Collections.shuffle(restantes, random);

        // On récupère une question et on la retire
        Question question = restantes.remove(0);

        // Eviter de poser immédiatement la même question
        if (question == derniereQuestion && restantes.size() > 0) {

            Question autreQuestion = restantes.remove(0);

            restantes.add(question);

            question = autreQuestion;
        }

        derniereQuestion = question;

        return question;
    }

    // =====================================================
    // QUESTIONS DE MATHEMATIQUES - CHAMBRE 1
    // 10 QCM + 10 VRAI/FAUX
    // =====================================================

    private void ajouterQuestionsMathematiques() {

        Question.Categorie categorie =
                Question.Categorie.MATHEMATIQUES;

        // ---------- QCM ----------

        ajouterQuestion(new Question(
                "Combien font 5 + 3 ?",
                Question.TypeQuestion.QCM,
                categorie,
                liste("6", "7", "8", "9"),
                "8"
        ));

        ajouterQuestion(new Question(
                "Combien font 12 - 4 ?",
                Question.TypeQuestion.QCM,
                categorie,
                liste("6", "7", "8", "9"),
                "8"
        ));

        ajouterQuestion(new Question(
                "Combien font 6 x 7 ?",
                Question.TypeQuestion.QCM,
                categorie,
                liste("36", "42", "48", "56"),
                "42"
        ));

        ajouterQuestion(new Question(
                "Combien font 36 / 6 ?",
                Question.TypeQuestion.QCM,
                categorie,
                liste("5", "6", "7", "8"),
                "6"
        ));

        ajouterQuestion(new Question(
                "Combien font 15 + 9 ?",
                Question.TypeQuestion.QCM,
                categorie,
                liste("22", "23", "24", "25"),
                "24"
        ));

        ajouterQuestion(new Question(
                "Combien font 20 - 7 ?",
                Question.TypeQuestion.QCM,
                categorie,
                liste("11", "12", "13", "14"),
                "13"
        ));

        ajouterQuestion(new Question(
                "Combien font 8 x 4 ?",
                Question.TypeQuestion.QCM,
                categorie,
                liste("24", "28", "32", "36"),
                "32"
        ));

        ajouterQuestion(new Question(
                "Combien font 45 / 5 ?",
                Question.TypeQuestion.QCM,
                categorie,
                liste("7", "8", "9", "10"),
                "9"
        ));

        ajouterQuestion(new Question(
                "Combien font 25 + 17 ?",
                Question.TypeQuestion.QCM,
                categorie,
                liste("40", "41", "42", "43"),
                "42"
        ));

        ajouterQuestion(new Question(
                "Combien font 9 x 9 ?",
                Question.TypeQuestion.QCM,
                categorie,
                liste("72", "81", "90", "99"),
                "81"
        ));

        // ---------- VRAI / FAUX ----------

        ajouterQuestion(vraiFaux(
                "2 + 3 = 5",
                categorie,
                "Vrai"
        ));

        ajouterQuestion(vraiFaux(
                "10 - 4 = 7",
                categorie,
                "Faux"
        ));

        ajouterQuestion(vraiFaux(
                "5 x 6 = 30",
                categorie,
                "Vrai"
        ));

        ajouterQuestion(vraiFaux(
                "24 / 4 = 8",
                categorie,
                "Faux"
        ));

        ajouterQuestion(vraiFaux(
                "15 + 15 = 30",
                categorie,
                "Vrai"
        ));

        ajouterQuestion(vraiFaux(
                "9 x 3 = 27",
                categorie,
                "Vrai"
        ));

        ajouterQuestion(vraiFaux(
                "50 - 20 = 25",
                categorie,
                "Faux"
        ));

        ajouterQuestion(vraiFaux(
                "40 / 8 = 5",
                categorie,
                "Vrai"
        ));

        ajouterQuestion(vraiFaux(
                "7 x 8 = 54",
                categorie,
                "Faux"
        ));

        ajouterQuestion(vraiFaux(
                "100 - 35 = 65",
                categorie,
                "Vrai"
        ));
    }

    // =====================================================
    // QUESTIONS DE CULTURE GENERALE - CHAMBRE 2
    // 10 QCM + 10 VRAI/FAUX
    // =====================================================

    private void ajouterQuestionsCultureGenerale() {

        Question.Categorie categorie =
                Question.Categorie.CULTURE_GENERALE;

        // ---------- QCM ----------

        ajouterQuestion(new Question(
                "Quelle est la capitale de l'Italie ?",
                Question.TypeQuestion.QCM,
                categorie,
                liste("Madrid", "Rome", "Paris", "Lisbonne"),
                "Rome"
        ));

        ajouterQuestion(new Question(
                "Combien de continents compte-t-on généralement ?",
                Question.TypeQuestion.QCM,
                categorie,
                liste("5", "6", "7", "8"),
                "7"
        ));

        ajouterQuestion(new Question(
                "Quelle planète est surnommée la planète rouge ?",
                Question.TypeQuestion.QCM,
                categorie,
                liste("Vénus", "Mars", "Jupiter", "Mercure"),
                "Mars"
        ));

        ajouterQuestion(new Question(
                "Quel est le plus grand océan du monde ?",
                Question.TypeQuestion.QCM,
                categorie,
                liste("Atlantique", "Indien", "Pacifique", "Arctique"),
                "Pacifique"
        ));

        ajouterQuestion(new Question(
                "Combien de jours y a-t-il dans une année bissextile ?",
                Question.TypeQuestion.QCM,
                categorie,
                liste("365", "366", "364", "360"),
                "366"
        ));

        ajouterQuestion(new Question(
                "Quel animal est un mammifère ?",
                Question.TypeQuestion.QCM,
                categorie,
                liste("Crocodile", "Dauphin", "Truite", "Grenouille"),
                "Dauphin"
        ));

        ajouterQuestion(new Question(
                "Quel est le symbole chimique de l'eau ?",
                Question.TypeQuestion.QCM,
                categorie,
                liste("CO2", "O2", "H2O", "NaCl"),
                "H2O"
        ));

        ajouterQuestion(new Question(
                "Dans quel pays se trouve la ville de Tokyo ?",
                Question.TypeQuestion.QCM,
                categorie,
                liste("Chine", "Japon", "Corée du Sud", "Thaïlande"),
                "Japon"
        ));

        ajouterQuestion(new Question(
                "Combien de côtés possède un hexagone ?",
                Question.TypeQuestion.QCM,
                categorie,
                liste("5", "6", "7", "8"),
                "6"
        ));

        ajouterQuestion(new Question(
                "Quel organe permet principalement de respirer ?",
                Question.TypeQuestion.QCM,
                categorie,
                liste("Le cœur", "Les poumons", "Le foie", "L'estomac"),
                "Les poumons"
        ));

        // ---------- VRAI / FAUX ----------

        ajouterQuestion(vraiFaux(
                "La Terre tourne autour du Soleil.",
                categorie,
                "Vrai"
        ));

        ajouterQuestion(vraiFaux(
                "Le Sahara est un désert.",
                categorie,
                "Vrai"
        ));

        ajouterQuestion(vraiFaux(
                "Les araignées possèdent six pattes.",
                categorie,
                "Faux"
        ));

        ajouterQuestion(vraiFaux(
                "L'eau gèle à environ 0 °C à pression normale.",
                categorie,
                "Vrai"
        ));

        ajouterQuestion(vraiFaux(
                "La capitale de l'Espagne est Barcelone.",
                categorie,
                "Faux"
        ));

        ajouterQuestion(vraiFaux(
                "Un triangle possède trois côtés.",
                categorie,
                "Vrai"
        ));

        ajouterQuestion(vraiFaux(
                "Le Soleil est une planète.",
                categorie,
                "Faux"
        ));

        ajouterQuestion(vraiFaux(
                "Les poissons vivent généralement dans l'eau.",
                categorie,
                "Vrai"
        ));

        ajouterQuestion(vraiFaux(
                "La Lune est une étoile.",
                categorie,
                "Faux"
        ));

        ajouterQuestion(vraiFaux(
                "La France se trouve en Europe.",
                categorie,
                "Vrai"
        ));
    }

    // =====================================================
    // OUTILS DE CREATION
    // =====================================================

    private List<String> liste(String... propositions) {
        List<String> resultat = new ArrayList<>();

        Collections.addAll(resultat, propositions);

        return resultat;
    }

    private Question vraiFaux(
            String enonce,
            Question.Categorie categorie,
            String bonneReponse) {

        return new Question(
                enonce,
                Question.TypeQuestion.VRAI_FAUX,
                categorie,
                liste("Vrai", "Faux"),
                bonneReponse
        );
    }

    // =====================================================
    // INFORMATIONS SUR LA BANQUE
    // =====================================================

    public int getNombreQuestions(Question.Categorie categorie) {
        return questions.get(categorie).size();
    }

    public int getNombreQuestionsRestantes(
            Question.Categorie categorie) {

        return questionsRestantes.get(categorie).size();
    }

    public List<Question> getQuestions(Question.Categorie categorie) {

        return Collections.unmodifiableList(questions.get(categorie));
    }

}
