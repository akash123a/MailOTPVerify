package Agile.MailOTPVerify.Controller;

import Agile.MailOTPVerify.Service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mail/user")
public class OtpController {

    @Autowired
    private OtpService otpService;

    // Send OTP to Email
    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestParam String email) {
        try {
            String response = otpService.generateAndSendOtp(email);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to send OTP", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Verify OTP
    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestParam String email, @RequestParam int otp) {
        boolean isValid = otpService.verifyOtp(email, otp);
        if (isValid) {
            return new ResponseEntity<>("OTP Verified Successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid OTP!", HttpStatus.BAD_REQUEST);
        }
    }
}