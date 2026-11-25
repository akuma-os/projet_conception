
package fr.ul.acl.model;

import java.util.List;

public class GestionnaireProgression {

    private BanqueQuestions banqueQuestions;
    private Questionnaire questionnaireActuel;

    // ID de la chambre actuelle : 0, 1, 2, 3 
    private int idChambreActuelle;

    // Etat de la progression
    private boolean missionTerminee;
    private boolean questionnaireReussi;
    private boolean cleObtenue;

    // Etat de la porte et de la zone du questionnaire
    private boolean porteAccessible;
    private boolean zoneQuestionnaireAccessible;

    public GestionnaireProgression(BanqueQuestions banqueQuestions) {

        if (banqueQuestions == null) {
            throw new IllegalArgumentException(
                    "La banque de questions ne peut pas être nulle."
            );
        }

        this.banqueQuestions = banqueQuestions;

        idChambreActuelle = 0;

        missionTerminee = false;
        questionnaireReussi = false;
        cleObtenue = false;

        porteAccessible = false;
        zoneQuestionnaireAccessible = false;

        questionnaireActuel = null;
    }

    // =====================================================
    // CATEGORIE DE LA CHAMBRE
    // =====================================================

    public Question.Categorie getCategorieChambre() {

        switch (idChambreActuelle) {

            case 0:
                return Question.Categorie.MATHEMATIQUES;

            case 1:
                return Question.Categorie.CULTURE_GENERALE;

            case 2:
                return Question.Categorie.ACL;

            case 3:
                return null;

            default:
                throw new IllegalStateException(
                        "Numéro de chambre invalide."
                );
        }
    }

    // =====================================================
    // 1. VERIFIER LA MISSION
    // =====================================================

    public void verifierMission() {

        if (missionTerminee) {
            rendrePorteAccessible();
        }
    }

    // =====================================================
    // 2. RENDRE LA PORTE ET LA ZONE ACCESSIBLES
    // =====================================================

    public void rendrePorteAccessible() {

        if (!missionTerminee) {
            return;
        }

        porteAccessible = true;
        zoneQuestionnaireAccessible = true;
    }

    // =====================================================
    // 3. LANCER LE QUESTIONNAIRE
    // =====================================================

    public void lancerQuestionnaire() {

        if (!zoneQuestionnaireAccessible) {
            throw new IllegalStateException(
                    "La zone du questionnaire n'est pas accessible."
            );
        }

        if (idChambreActuelle == 3) {
            throw new IllegalStateException(
                    "La chambre finale ne possède pas de questionnaire."
            );
        }

        if (questionnaireActuel != null) {
            return;
        }

        Question.Categorie categorie = getCategorieChambre();

        List<Question> questions =
                banqueQuestions.getQuestions(categorie);

        questionnaireActuel = new Questionnaire(
                "Questionnaire chambre " + idChambreActuelle,
                categorie,
                questions
        );

        questionnaireReussi = false;
    }

    // =====================================================
    // REPONDRE AU QUESTIONNAIRE
    // =====================================================

    public boolean repondreQuestionnaire(String reponse) {

        if (questionnaireActuel == null) {
            throw new IllegalStateException(
                    "Le questionnaire n'a pas été lancé."
            );
        }

        boolean correcte = questionnaireActuel.repondre(reponse);

        if (correcte) {
            questionnaireReussi = true;
            attribuerCle();
        }

        return correcte;
    }

    // =====================================================
    // 4. ATTRIBUER LA CLE
    // =====================================================

    public void attribuerCle() {

        if (questionnaireReussi) {
            cleObtenue = true;
        }
    }

    // =====================================================
    // MISSION TERMINEE
    // =====================================================

    public void validerMission() {

        missionTerminee = true;

        verifierMission();
    }

    // =====================================================
    // 5. AUTORISER LA TRANSITION
    // =====================================================

    public boolean autoriserTransition() {

        return porteAccessible && cleObtenue;
    }

    public boolean passerChambreSuivante() {

        if (!autoriserTransition()) {
            return false;
        }

        if (idChambreActuelle >= 3) {
            return false;
        }

        idChambreActuelle++;

        reinitialiserProgressionChambre();

        return true;
    }

    // =====================================================
    // REINITIALISER LA PROGRESSION
    // =====================================================

    private void reinitialiserProgressionChambre() {

        missionTerminee = false;
        questionnaireReussi = false;
        cleObtenue = false;

        porteAccessible = false;
        zoneQuestionnaireAccessible = false;

        questionnaireActuel = null;
    }

    // =====================================================
    // GETTERS
    // =====================================================

    public int getIdChambreActuelle() {
        return idChambreActuelle;
    }

    public boolean estMissionTerminee() {
        return missionTerminee;
    }

    public boolean estQuestionnaireReussi() {
        return questionnaireReussi;
    }

    public boolean aCle() {
        return cleObtenue;
    }

    public boolean estPorteAccessible() {
        return porteAccessible;
    }

    public boolean estZoneQuestionnaireAccessible() {
        return zoneQuestionnaireAccessible;
    }

    public Questionnaire getQuestionnaireActuel() {
        return questionnaireActuel;
    }
    public Question getQuestionActuelle() {
        if (questionnaireActuel == null) {
            return null;
        }

        return questionnaireActuel.getQuestionActuelle();
    }
}
