package sopt.mobile2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sopt.mobile2.dto.MonthlyTransferRequestDto;
import sopt.mobile2.service.MonthlyTransferService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transfer-list")
public class MonthlyTransferController {

    private final MonthlyTransferService monthlyTransferService;

    @GetMapping("")
    public ResponseEntity getTransferListByMonth(@RequestParam(name = "month") int month,
                                                 @RequestBody MonthlyTransferRequestDto requestDto) {
        return ResponseEntity.ok(monthlyTransferService.getTransferByMonth(month, requestDto));
    }
}
