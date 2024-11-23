package neordinaryHackathon.neordinaryHackathon.domain;

import jakarta.persistence.*;
import lombok.*;
import neordinaryHackathon.neordinaryHackathon.domain.common.BaseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class House extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_id")
    private Long houseId;

    private String name;

    private LocalDate date;

    private String location;

    private Integer houseImagesNumber;

    @Column(length = 3000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    private List<Room> roomList = new ArrayList<>();

    public House updateHouse(String name, String location, LocalDate date, String content) {
        this.name = name;
        this.location =location;
        this.date = date;
        this.content = content;
        return this;
    }
}
