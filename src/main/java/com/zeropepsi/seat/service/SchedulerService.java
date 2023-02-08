package com.zeropepsi.seat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchedulerService {

	private final KopisService kopisService;
	private final PerformanceEsService performanceEsService;

	@Scheduled(cron = "0 0 0 * * *")
	public void PerformanceCompleteMySql() {
		kopisService.deletePerformanceComplete();
	}

	@Scheduled(cron = "0 0 0 * * *", initialDelay = 5000)
	public void PerformanceCompleteEs() {
		performanceEsService.deletePerformanceComplete();
	}

	@Scheduled(cron = "0 0 0 1 * ?")
	private void updatePerformance() {
		kopisService.updatePerformance();
	}

}
