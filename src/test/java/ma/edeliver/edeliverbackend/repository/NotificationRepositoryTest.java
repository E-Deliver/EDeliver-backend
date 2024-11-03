package ma.edeliver.edeliverbackend.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import ma.edeliver.edeliverbackend.entity.Notification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class NotificationRepositoryTest {

    @Mock
    private NotificationRepository notificationRepository;

    private Notification notification;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        notification = new Notification();
        notification.setMessage("New Notification");
    }

    @Test
    public void testSaveNotification() {
        when(notificationRepository.save(notification)).thenReturn(notification);

        Notification savedNotification = notificationRepository.save(notification);

        assertNotNull(savedNotification);
        assertEquals("New Notification", savedNotification.getMessage());
    }
}
