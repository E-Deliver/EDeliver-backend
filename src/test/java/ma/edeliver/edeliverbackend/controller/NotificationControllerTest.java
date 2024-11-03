package ma.edeliver.edeliverbackend.controller;

import ma.edeliver.edeliverbackend.entity.Notification;
import ma.edeliver.edeliverbackend.service.CommandeService;
import ma.edeliver.edeliverbackend.service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class NotificationControllerTest {

    @InjectMocks
    private NotificationController notificationController;

    @Mock
    private NotificationService notificationService;

    @Mock
    private CommandeService commandeService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(notificationController).build();
    }

    @Test
    public void testGetAllNotifications() throws Exception {
        Notification notification1 = new Notification();
        notification1.setMessage("Notification 1");

        Notification notification2 = new Notification();
        notification2.setMessage("Notification 2");

        List<Notification> notifications = Arrays.asList(notification1, notification2);

        when(notificationService.getAllNotifications()).thenReturn(notifications);

        mockMvc.perform(get("/api/notifications")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].message").value("Notification 1"))
                .andExpect(jsonPath("$[1].message").value("Notification 2"));
    }

    @Test
    public void testGetNotificationsByClientId() throws Exception {
        Long clientId = 1L;
        Notification notification = new Notification();
        notification.setMessage("Notification for client");

        List<Notification> notifications = Arrays.asList(notification);

        when(notificationService.getNotificationsByClientId(clientId)).thenReturn(notifications);

        mockMvc.perform(get("/api/notifications/client")
                .param("clientId", clientId.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].message").value("Notification for client"));
    }

    @Test
    public void testGetNotificationsForLivreur() throws Exception {
        Long livreurId = 1L;
        Notification notification1 = new Notification();
        notification1.setMessage("Notification for livreur 1");

        List<Notification> notifications = Arrays.asList(notification1);
        when(commandeService.getClientIdsByLivreurId(livreurId)).thenReturn(Arrays.asList(1L));
        when(notificationService.getNotificationsByClientIds(Arrays.asList(1L))).thenReturn(notifications);

        mockMvc.perform(get("/api/notifications/livreur")
                .param("livreurId", livreurId.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].message").value("Notification for livreur 1"));
    }
}
