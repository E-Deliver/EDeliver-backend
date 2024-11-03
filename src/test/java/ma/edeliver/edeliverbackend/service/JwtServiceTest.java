package ma.edeliver.edeliverbackend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JwtServiceTest {

    @InjectMocks
    private JwtService jwtService;

    private final String secretKey = "MySuperSecretKey12345678901234567890123456789012"; // Clé secrète de test (doit être en base64)
    private final long jwtExpiration = 1000 * 60 * 60; // 1 heure pour les tests

    private String token;
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        // Initialisation manuelle des valeurs `secretKey` et `jwtExpiration`
        jwtService = new JwtService();
        jwtService.secretKey = secretKey; // Injectez la clé secrète ici pour le test
        jwtService.jwtExpiration = jwtExpiration;

        // Création d'un token de test
        Map<String, Object> claims = new HashMap<>();
        claims.put("testClaim", "testValue");
        userDetails = User.withUsername("testuser").password("password").roles("USER").build();
        token = jwtService.generateToken(claims, userDetails);
    }

    @Test
    void testExtractUsername() {
        String username = jwtService.extractUsername(token);
        assertEquals("testuser", username);
    }

    @Test
    void testGenerateToken() {
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void testIsTokenValid() {
        assertTrue(jwtService.isTokenValid(token, userDetails));
    }

}
