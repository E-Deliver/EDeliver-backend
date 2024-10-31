package ma.edeliver.edeliverbackend.controller;

import ma.edeliver.edeliverbackend.service.StatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

  private final StatisticsService statisticsService;

  public StatisticsController(StatisticsService statisticsService) {
    this.statisticsService = statisticsService;
  }

  @GetMapping("/totals")
  public ResponseEntity<Map<String, Long>> getTotals() {
    Map<String, Long> stats = new HashMap<>();
    stats.put("totalCommands", statisticsService.getTotalCommands());
    stats.put("totalClients", statisticsService.getTotalClients());
    stats.put("totalLivreurs", statisticsService.getTotalLivreurs());

    return ResponseEntity.ok(stats);
  }
}

