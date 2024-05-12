package scaffold.framework.demo.entity;

import orm.DynamicORM;
import orm.annotations.Id;
import orm.annotations.Ignore;

/**
 * Utilisateur
 */
public class Utilisateur extends DynamicORM<Utilisateur> {

    @Id
    Integer id;
    String utilisateur;
    Integer sex;
    String description;
    Integer department;

    @Ignore
    private String checked;




    public void setChecked() {
        checked = "selected";
    }

    public String getChecked() {
        if (checked == null) {
            return "";
        }
        return checked;
    }

    public void setSex(String x) {
        setSex(Integer.parseInt(x));
    }

    public void setDepartment(String department) {
        setDepartment(Integer.parseInt(department));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(String utilisateur) throws Exception {
        if (utilisateur.isEmpty()) {
            throw new Exception("champ obligatoire");
        }
        this.utilisateur = utilisateur;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }
}