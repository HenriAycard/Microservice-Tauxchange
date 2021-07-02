package fr.dauphine.miageif.tauxchange.TauxChange.Model;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


// Classe persistente representant  un "taux de change"
@Entity
public class TauxChange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="devise_src")
    private String source;

    @Column(name="devise_dest")
    private String dest;

    @Column(name="taux")
    private BigDecimal taux;

    @Column(name="date_cours")
    private String date;

    public TauxChange() {
    }

    public TauxChange(Long id, String source, String dest, BigDecimal taux, String date) {
        super();
        this.id = id;
        this.source = source;
        this.dest = dest;
        this.taux = taux;
        this.date = date;
    }

    public TauxChange(String source, String dest, BigDecimal taux, String date) {
        super();
        this.source = source;
        this.dest = dest;
        this.taux = taux;
        this.date = date;
    }

    // GETTER

    public Long getId() { return id; }

    public String getSource() { return source; }

    public String getDest() { return dest; }

    public BigDecimal getTaux() {
        return taux;
    }

    public String getDate() { return date; }

    // SETTER

    public void setId(Long id) { this.id = id; }

    public void setSource(String source) { this.source = source; }

    public void setDestination(String dest) { this.dest = dest; }

    public void setTaux(BigDecimal taux) { this.taux = taux; }

    public void setDate(String date) { this.date = date; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TauxChange that = (TauxChange) o;
        return Objects.equals(id, that.id) && Objects.equals(source, that.source) && Objects.equals(dest, that.dest) && Objects.equals(taux, that.taux) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, source, dest, taux, date);
    }
}
