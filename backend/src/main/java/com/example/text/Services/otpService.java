package com.example.text.Services;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

@Service
public class otpService {
	 private static final int OTP_LENGTH = 4;
	    private static final SecureRandom secureRandom = new SecureRandom();

	    public static String generateOtp() {
	        int otp = secureRandom.nextInt((int) Math.pow(10, OTP_LENGTH));
	        return String.format("%04d", otp); // Pads with leading zeros if necessary
	    }
 


}