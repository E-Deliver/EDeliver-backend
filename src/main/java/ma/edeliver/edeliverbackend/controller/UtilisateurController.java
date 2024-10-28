package ma.edeliver.edeliverbackend.controller;

import ma.edeliver.edeliverbackend.entity.Utilisateur;
import ma.edeliver.edeliverbackend.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

  @Autowired
  private UtilisateurService utilisateurService;

  // Get the profile of the currently authenticated user
  @GetMapping("/profile")
  public ResponseEntity<Utilisateur> getUserProfile(Authentication authentication) {
    String email = authentication.getName();
    Utilisateur utilisateur = utilisateurService.getUtilisateurByEmail(email);
    return ResponseEntity.ok(utilisateur);
  }
  @PostMapping("/uploadPhoto")
  public ResponseEntity<String> uploadPhoto(@RequestParam("file") MultipartFile file, Authentication authentication) throws IOException {
    String email = authentication.getName();
    Utilisateur utilisateur = utilisateurService.getUtilisateurByEmail(email);

    if (utilisateur == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

    String folder = "uploads/profile_photos/";
    Path uploadPath = Paths.get(folder);

    if (!Files.exists(uploadPath)) {
      Files.createDirectories(uploadPath);
    }

    String filename = "profile_" + utilisateur.getId() + "_" + file.getOriginalFilename();
    Path filePath = uploadPath.resolve(filename);
    Files.copy(file.getInputStream(), filePath);

    // Update the user's photo path
    utilisateur.setPhoto(folder + filename);
    utilisateurService.saveUtilisateur(utilisateur);  // Save to persist the path

    System.out.println("Uploaded and saved photo path: " + utilisateur.getPhoto());

    return ResponseEntity.ok("Photo uploaded successfully");
  }

  // Get all users by their role
  @GetMapping("/role/{role}")
  public ResponseEntity<List<Utilisateur>> getUsersByRole(@PathVariable("role") String roleStr) {
    Utilisateur.Role role;
    try {
      role = Utilisateur.Role.valueOf(roleStr.toUpperCase());
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(null);
    }
    List<Utilisateur> utilisateurs = utilisateurService.getUtilisateursByRole(role);
    return ResponseEntity.ok(utilisateurs);
  }
  @PutMapping("/profile")
  public ResponseEntity<Utilisateur> updateUserProfile(@RequestBody Utilisateur updatedUtilisateur, Authentication authentication) {
    String email = authentication.getName();
    Utilisateur utilisateur = utilisateurService.getUtilisateurByEmail(email);

    if (utilisateur == null) {
      return ResponseEntity.notFound().build();
    }

    // Update the user's details
    utilisateur.setNom(updatedUtilisateur.getNom());
    utilisateur.setEmail(updatedUtilisateur.getEmail());
    utilisateur.setMotDePasse(updatedUtilisateur.getMotDePasse());
    utilisateur.setAddress(updatedUtilisateur.getAddress()); // Update address

    // Update the photo URL if provided
    if (updatedUtilisateur.getPhoto() != null && !updatedUtilisateur.getPhoto().isEmpty()) {
      // Log for debugging
      System.out.println("Updating photo from " + utilisateur.getPhoto() + " to " + updatedUtilisateur.getPhoto());
      utilisateur.setPhoto(utilisateur.getPhoto());
    } else {
      // If no new photo is provided, you can log it or handle it as needed
      System.out.println("No new photo provided, keeping existing photo: " + utilisateur.getPhoto());
    }

    // Save the updated user
    Utilisateur updatedUser = utilisateurService.saveUtilisateur(utilisateur);

    // Log the updated user details
    System.out.println("Updated User: " + updatedUser);

    return ResponseEntity.ok(updatedUser);
  }




}
