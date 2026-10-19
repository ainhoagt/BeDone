package com.example.be_done_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="tasks")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="column_id")
    private BoardColumn boardColumn;
    @Column(name="task_name", nullable = false)
    private String taskName;
    @Column(name="colour")
    private String colour;
    @Column(name="description")
    private String description;
    @Column(name="position_index", nullable = false)
    private int positionIndex;
}
