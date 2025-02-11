package com.bus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bus.Repository.PassengerRepo;
import com.bus.Repository.SeatsRepo;
import com.bus.entities.Bus;
import com.bus.entities.Passengers;
import com.bus.entities.Seats;

@Service
@Transactional
public class SeatsServiceImpl implements SeatsService {
	
    @Autowired
    private SeatsRepo seatRepository;

    @Autowired
    private PassengerRepo passengerRepository;

 // Assuming you have a BusService to fetch the Bus by busNo
    @Autowired
    private BusService busService;

    public Seats addSeat(Seats seat) {
        // Fetch the bus by its busNo
        Bus bus = busService.getBus(seat.getBusId().getBusNo());
        
        // Ensure the bus exists
        if (bus == null) {
            throw new IllegalArgumentException("Bus not found");
        }
        
        seat.setBusId(bus); // Associate the bus with the seat
        return seatRepository.save(seat); // Save the seat
    }


    @Override
    public String bookSeat(Long passengerId,int seatNo) {
       
        Passengers passenger = passengerRepository.findById(passengerId)
            .orElseThrow(() -> new RuntimeException("Passenger not found"));
        Seats seat = seatRepository.findBySeatNo(seatNo);

        
        if (!seat.isAvailable()) {
            return "Seat is already booked.";
        }

       
        seat.setAvailable(false);

       
        passenger.setSeat(seat); 

       
        seatRepository.save(seat);
        passengerRepository.save(passenger);

        return "Seat booked successfully!";
    }
    
    
    
    @Override
    public List<Seats> getAvailableSeatsForBus(Bus bus) {
        // Fetch and return available seats for the given bus
        return seatRepository.findByBusIdAndAvailableTrue(bus);
    }


	@Override
	public Seats addSeats(Seats seat) {
		// TODO Auto-generated method stub
		
		
		 Bus bus = busService.getBus(seat.getBusId().getBusNo());
	        
	        // Ensure the bus exists
	        if (bus == null) {
	            throw new IllegalArgumentException("Bus not found");
	        }
	        
	        seat.setBusId(bus); // Associate the bus with the seat
	        return seatRepository.save(seat); 
		
	}

}
