package ma.edeliver.edeliverbackend.service;

import ma.edeliver.edeliverbackend.entity.Notification;
import ma.edeliver.edeliverbackend.repository.NotificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotificationServiceTest {

    @InjectMocks
    private NotificationService notificationService;

    @Mock
    private NotificationRepository notificationRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllNotifications() {
        Notification notification1 = new Notification();
        notification1.setMessage("Notification 1");
        Notification notification2 = new Notification();
        notification2.setMessage("Notification 2");

        when(notificationRepository.findAll()).thenReturn(List.of(notification1, notification2));

        List<Notification> result = notificationService.getAllNotifications();

        assertEquals(2, result.size());
        assertEquals("Notification 1", result.get(0).getMessage());
        assertEquals("Notification 2", result.get(1).getMessage());
        verify(notificationRepository, times(1)).findAll();
    }

    @Test
    void testGetNotificationsByClientId() {
        Long clientId = 1L;
        Notification notification1 = new Notification();
        notification1.setMessage("Notification for Client 1");
        Notification notification2 = new Notification();
        notification2.setMessage("Another Notification for Client 1");

        when(notificationRepository.findByClientId(clientId)).thenReturn(List.of(notification1, notification2));

        List<Notification> result = notificationService.getNotificationsByClientId(clientId);

        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(n -> n.getMessage().contains("Client 1")));
        verify(notificationRepository, times(1)).findByClientId(clientId);
    }

    @Test
    void testGetNotificationsByClientIds() {
        Long clientId1 = 1L;
        Long clientId2 = 2L;
        Notification notification1 = new Notification();
        notification1.setMessage("Notification for Client 1");
        Notification notification2 = new Notification();
        notification2.setMessage("Notification for Client 2");

        when(notificationRepository.findByClientId(clientId1)).thenReturn(List.of(notification1));
        when(notificationRepository.findByClientId(clientId2)).thenReturn(List.of(notification2));

        List<Notification> result = notificationService.getNotificationsByClientIds(Arrays.asList(clientId1, clientId2));

        assertEquals(2, result.size());
        assertEquals("Notification for Client 1", result.get(0).getMessage());
        assertEquals("Notification for Client 2", result.get(1).getMessage());
        verify(notificationRepository, times(1)).findByClientId(clientId1);
        verify(notificationRepository, times(1)).findByClientId(clientId2);
    }
}
