package sopt.mobile2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sopt.mobile2.dto.RecentTransferResponse;
import sopt.mobile2.service.RecentTransferService;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recent-transfers")
public class RecentTransferController {

    private final RecentTransferService recentTransferService;

    @GetMapping("/{accountId}")
    public ResponseEntity<List<RecentTransferResponse>> getRecentTransfersByAccount(@PathVariable(name="accountId") Long accountId) {
        List<RecentTransferResponse> response = recentTransferService.recentTransferList(accountId);
        return ResponseEntity.ok(response);
    }
}

