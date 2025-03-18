package Agile.MailOTPVerify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MailOtpVerifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailOtpVerifyApplication.class, args);

		System.out.println("Hello Mail OTP Verify");
	}

}
