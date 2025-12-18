package com.example.inventoryservice.service;

import com.example.inventoryservice.entity.Event;
import com.example.inventoryservice.entity.Venue;
import com.example.inventoryservice.repository.EventRepository;
import com.example.inventoryservice.repository.VenueRepository;
import com.example.inventoryservice.response.EventInventoryResponse;
import com.example.inventoryservice.response.VenueInventoryResponse;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    private  final EventRepository eventRepository;
    private final VenueRepository venueRepositrory;

    @Autowired
    public InventoryService(final EventRepository eventRepository, final VenueRepository venueRepositrory ){
        this.eventRepository = eventRepository;
        this.venueRepositrory = venueRepositrory;

    }

    public List<EventInventoryResponse> getAllEvents() {

        final List<Event> events= eventRepository.findAll();

        return events.stream().map(event -> EventInventoryResponse.builder()
                .event(event.getName())
                .capacity(event.getLeftCapacity())
                .venue(event.getVenue())
                .build()).collect(Collectors.toList());



    }

    public VenueInventoryResponse getVenueInformation(final Long venueId) {

        final Venue venue= venueRepositrory.findById(venueId).orElse(null);

        return VenueInventoryResponse.builder()
                .venueId(venue.getId())
                .venueName(venue.getName())
                .totalCapacity(venue.getTotalCapacity())
                .build();

    }
}
