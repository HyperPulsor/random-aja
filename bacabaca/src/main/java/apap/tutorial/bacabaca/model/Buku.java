package apap.tutorial.bacabaca.model;
import java.util.UUID;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "buku")
@SQLDelete(sql = "UPDATE buku SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
public class Buku {
    @Id
    private UUID id = UUID.randomUUID();

    @NotNull
    @Size(max = 100)
    @Column(name = "judul", nullable = false)
    private String judul;

    @NotNull
    @Size(max = 4)
    @Column(name = "tahun_terbit", nullable = false)
    private String tahunTerbit;

    @NotNull
    @Column(name = "harga", nullable = false)
    private BigDecimal harga;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_penerbit", referencedColumnName = "idPenerbit")
    private Penerbit penerbit;

    @ManyToMany
    @JoinTable(name = "penulis_buku", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "id_penulis"))
    List<Penulis> listPenulis;

    @NotNull
    @Column(name = "is_deleted", nullable = false)
    private boolean isdDeleted = Boolean.FALSE;
}
