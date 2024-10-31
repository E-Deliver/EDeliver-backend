package ma.edeliver.edeliverbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import ma.edeliver.edeliverbackend.entity.Commande;
import ma.edeliver.edeliverbackend.entity.Notification;
import ma.edeliver.edeliverbackend.service.CommandeService;
import ma.edeliver.edeliverbackend.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private CommandeService commandeService;

    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    @GetMapping("/client")
    public List<Notification> getNotificationsByClientId(@RequestParam Long clientId) {
        return notificationService.getNotificationsByClientId(clientId);
    }

    @GetMapping("/livreur")
    public List<Notification> getNotificationsForLivreur(@RequestParam Long livreurId) {
        List<Long> clientIds = commandeService.getClientIdsByLivreurId(livreurId);
        return notificationService.getNotificationsByClientIds(clientIds);
    }
}
