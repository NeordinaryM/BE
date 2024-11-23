package neordinaryHackathon.neordinaryHackathon.domain;

import jakarta.persistence.*;
import lombok.*;
import neordinaryHackathon.neordinaryHackathon.domain.common.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Guest extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guest_id")
    private Long guestId;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
    private List<Letter> letterList = new ArrayList<>();
}
