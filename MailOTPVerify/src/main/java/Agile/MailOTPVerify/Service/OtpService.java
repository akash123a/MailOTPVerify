package Agile.MailOTPVerify.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpService {

    @Autowired
    private JavaMailSender mailSender;

    private final Map<String, Integer> otpStorage = new HashMap<>();
    private final Random random = new Random();

    // Generate and send OTP to email
    public String generateAndSendOtp(String email) {
        int otp = 100000 + random.nextInt(900000); // 6-digit OTP
        otpStorage.put(email, otp);

        // Send OTP via email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP is: " + otp + ". It is valid for 5 minutes.");
        mailSender.send(message);

        return "OTP sent successfully to " + email;
    }

    // Verify OTP
    public boolean verifyOtp(String email, int userOtp) {
        if (otpStorage.containsKey(email) && otpStorage.get(email) == userOtp) {
            otpStorage.remove(email); // Remove OTP after verification
            return true;
        }
        return false;
    }
}