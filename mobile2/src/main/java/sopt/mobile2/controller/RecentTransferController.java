package sopt.mobile2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/{myAccountId}/bookmark/{markedAccountId}")
    public ResponseEntity<Void> addBookMark(@PathVariable(name = "myAccountId") Long myAccountId,
                                            @PathVariable(name = "markedAccountId") Long markedAccountId) {
        recentTransferService.addBookMark(myAccountId, markedAccountId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{myAccountId}/bookmark/{markedAccountId}")
    public ResponseEntity<Void> removeBookMark(@PathVariable(name = "myAccountId") Long myAccountId,
                                               @PathVariable(name = "markedAccountId") Long markedAccountId) {
        recentTransferService.removeBookMark(myAccountId, markedAccountId);
        return ResponseEntity.ok().build();
    }
}

