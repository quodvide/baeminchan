package codesquad.domain;

import codesquad.enums.DeliveryType;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
public class SideDish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_brand"))
    private Brand brand;

    @Column
    private String name;
    @Column
    private int price;
    @Column
    private int salePrice;
    @Column
    private String description;
    @Column
    private int weight;
    @Column
    private String enableDay;
    @Column
    private DeliveryType deliveryType;

    @Column
    @ColumnDefault("false")
    private boolean isEnableRegularDelivery;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SideDishImage> images;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_category"))
    private Category category;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_side_writer"))
    private User writer;
}

