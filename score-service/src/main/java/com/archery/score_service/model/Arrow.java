package com.archery.score_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Arrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long matchId;

    private Long roundId;
    private String archerCid;
    private Long teamId;
    private Integer arrowNumber;
    private Integer score;
    @Enumerated(EnumType.STRING)
    private ArrowType type;
}