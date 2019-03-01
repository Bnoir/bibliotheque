package bibliotheque.model;

import bibliotheque.model.enumeration.StatutEmprunt;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Emprunt {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @NotNull
    private LocalDate daterendu;

    @OneToOne
    @JoinColumn(name = "id")
    private Exemplaire exemplaire;

    @OneToOne
    @JoinColumn(name = "id")
    private Usager usager;

    @Enumerated(EnumType.STRING)
    private StatutEmprunt statut; // 0 : en cours, 1 : termine

    public Emprunt() {
    }

    public Emprunt(Usager usager, Exemplaire exemplaire) {
        this.usager = usager;
        this.exemplaire = exemplaire;
        this.statut = StatutEmprunt.EN_COURS;

        int jour = (exemplaire.getOeuvre() instanceof Livre) ? Livre.DUREE_EMPRUNT : Magasine.DUREE_EMPRUNT;
        this.daterendu = LocalDate.now().plusDays(jour);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDaterendu() {
        return daterendu;
    }

    public void setDaterendu(LocalDate daterendu) {
        this.daterendu = daterendu;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    public Usager getUsager() {
        return usager;
    }

    public void setUsager(Usager usager) {
        this.usager = usager;
    }

    public StatutEmprunt getStatut() {
        return statut;
    }

    public void setStatut(StatutEmprunt statut) {
        this.statut = statut;
    }
}
