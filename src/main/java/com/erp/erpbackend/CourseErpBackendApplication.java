package com.erp.erpbackend;

import com.erp.erpbackend.models.enums.user.UserStatus;
import com.erp.erpbackend.models.mybatis.user.User;
import com.erp.erpbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

@SpringBootApplication
public class CourseErpBackendApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(CourseErpBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
        keyGenerator.initialize(2048);

        KeyPair kp = keyGenerator.genKeyPair();
        PublicKey publicKey = (PublicKey) kp.getPublic();
        PrivateKey privateKey = (PrivateKey) kp.getPrivate();

        String encodedPublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        String encodedPrivateKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());

        System.out.println(convertToPrivateKey(encodedPrivateKey));
        System.out.println();
        System.out.println(convertToPublicKey(encodedPublicKey));
//        User user = User.builder().
//                name("Sarvar")
//                .surname("Musazade")
//                .status(UserStatus.ACTIVE)
//                .roleId(1L)
//                .email("sarvar55mszde@gmail.com")
//                .phoneNumber("5526021631")
//                .password("password")
//                .build();
//        userRepository.insert(user);
    }

    private String convertToPublicKey(String key) {
        StringBuilder result = new StringBuilder();
        result.append("-----BEGIN PUBLIC KEY-----\n");
        result.append(key);
        result.append("\n-----END PUBLIC KEY-----");
        return result.toString();
    }

    private String convertToPrivateKey(String key) {
        StringBuilder result = new StringBuilder();
        result.append("-----BEGIN PRIVATE KEY-----\n");
        result.append(key);
        result.append("\n-----END PRIVATE KEY-----");
        return result.toString();
    }

}
