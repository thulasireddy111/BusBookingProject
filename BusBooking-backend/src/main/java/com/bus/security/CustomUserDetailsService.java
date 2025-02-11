package com.bus.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bus.Repository.OwnerRepo;
import com.bus.entities.Owner;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final OwnerRepo ownerRepository;

    public CustomUserDetailsService(OwnerRepo ownerRepository) {
        this.ownerRepository = ownerRepository;
    } 

    @Override
    public UserDetails loadUserByUsername(String mobileNo) throws UsernameNotFoundException {
        try {
            String mobileNumber =(mobileNo); // Convert string to Long
            Optional<Owner> owner = ownerRepository.findByMobileNo(mobileNumber);
            return owner.map(CustomUserDetails::new)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with mobile number: " + mobileNo));
        } catch (NumberFormatException e) {
            throw new UsernameNotFoundException("Invalid mobile number format: " + mobileNo);
        }
    }
}
