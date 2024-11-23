package com.example.text.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.text.model.logout;
import com.example.text.repository.logoutRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class TokenBlacklistService {
	@Autowired
	private  logoutRepository logoutrepository; 

    private final Set<String> blacklistedTokens = new HashSet<>();

//    public void blacklistToken(String token) {
//        blacklistedTokens.add(token);
//    }

    public boolean isTokenBlacklisted(String token) {
    	
    	
    	Optional<logout> obj= logoutrepository.getToken(token);
    	if (obj.isPresent()) {
    		return true;
    	}
    	else
    	{
    		return false;
    	}
//    	return true;
    }
}
