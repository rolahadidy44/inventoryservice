package com.example.inventoryservice.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="venue")
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name="total_capacity")
    private Long totalCapacity;

    @Column(name = "address")
    private Long address;

    @OneToMany(mappedBy = "venue")
    private List<Event> events;




}
