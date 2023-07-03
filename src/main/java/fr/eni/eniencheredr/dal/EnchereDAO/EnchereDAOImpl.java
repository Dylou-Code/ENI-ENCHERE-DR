package fr.eni.eniencheredr.dal.EnchereDAO;

import fr.eni.eniencheredr.bo.Encheres;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EnchereDAOImpl implements EnchereDAO{

    private static final String selectListEnchereByID = "select ech.date_enchere, ech.montant_enchere, u1.no_utilisateur as echNo_utilisateur,\n"
            + "u1.pseudo as echPseudo, u1.nom as echNom, u1.prenom as echPrenom,\n"
            + "u1.email as echEmail, u1.telephone as echTelephone, u1.rue as echRue, u1.code_postal as echCodePostal,\n"
            + "u1.ville as echVille, u1.mot_de_passe as echPassword, u1.credit as echCredit, u1.administrateur as echAdministrateur,\n"
            + "a.*, c.*, u2.* from ENCHERES ech\n"
            + "inner join ARTICLES_VENDUS a on a.no_article = ech.no_article\n"
            + "inner join CATEGORIES c on c.no_categorie = a.no_categorie\n"
            + "inner join UTILISATEURS u2 on u2.no_utilisateur = a.no_utilisateur\n"
            + "inner join UTILISATEURS u1 on u1.no_utilisateur = ech.no_utilisateur\n"
            + "where ech.no_utilisateur = ?\n";

    private static final String selectEnchereByID = "select ech.date_enchere, ech.montant_enchere, u1.no_utilisateur as echNo_utilisateur,\n"
            + "u1.pseudo as echPseudo, u1.nom as echNom, u1.prenom as echPrenom,\n"
            + "u1.email as echEmail, u1.telephone as echTelephone, u1.rue as echRue, u1.code_postal as echCodePostal,\n"
            + "u1.ville as echVille, u1.mot_de_passe as echPassword, u1.credit as echCredit, u1.administrateur as echAdministrateur,\n"
            + "a.*, c.*, u2.* from ENCHERES ech\n"
            + "inner join ARTICLES_VENDUS a on a.no_article = ech.no_article\n"
            + "inner join CATEGORIES c on c.no_categorie = a.no_categorie\n"
            + "inner join UTILISATEURS u2 on u2.no_utilisateur = a.no_utilisateur\n"
            + "inner join UTILISATEURS u1 on u1.no_utilisateur = ech.no_utilisateur\n"
            + "where ech.no_article = ?\n";

    private static final String INSERT = "INSERT INTO";
    private static final String updateEnchere = "update ENCHERES set no_utilisateur = ?, no_Article = ?, date_enchere = ?, montant_enchere = ? where no_article = ?";
    private EnchereDAO enchereDAO;

    /*public class EnchereRowMapper  implements RowMapper<Encheres> {
        public Encheres mapRow(ResultSet rs, int rowNum) throws SQLException {
            Encheres encheres = new Encheres();
            encheres.setPseudo(rs.getString("pseudo"));
            encheres.setNom(rs.getString("nom"));
            encheres.setPrenom(rs.getString("prenom"));
            return encheres;
        }
    }*/
    @Override
    public List<Encheres> findAllEncheres() {
        return null;
    }

    @Override
    public Encheres findEncheresById(Integer id) {
        return null;
    }
}
