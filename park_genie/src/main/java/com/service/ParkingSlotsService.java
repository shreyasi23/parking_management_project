package com.service;

import com.model.ParkingSlots;

import java.util.List;

public interface ParkingSlotsService {
    List<ParkingSlots> getParkingSlots();

    ParkingSlots updateSlotStatus(ParkingSlots parkingSlots);

    List<String> getFreeSlots(int vehicle_type);
}
