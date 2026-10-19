package com.example.be_done_api.model;

import jakarta.persistence.*;
import jakarta.persistence.Column;
import lombok.*;

import java.util.List;

@Entity
@Table(name="board_columns")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BoardColumn {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="board_id")
    private Board board;
    @Column(name="column_name", nullable = false)
    private String columnName;
    @Column(name="colour")
    private String colour;
    @Column(name="position_index", nullable = false)
    private int positionIndex;
    @OneToMany(mappedBy = "boardColumn", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Task> tasks;
}
