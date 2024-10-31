package ma.edeliver.edeliverbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import ma.edeliver.edeliverbackend.entity.Commande;
import ma.edeliver.edeliverbackend.entity.Notification;
import ma.edeliver.edeliverbackend.repository.CommandeRepository;
import ma.edeliver.edeliverbackend.repository.NotificationRepository;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public List<Notification> getNotificationsByClientId(Long clientId) {
        return notificationRepository.findByClientId(clientId);
    }

}
