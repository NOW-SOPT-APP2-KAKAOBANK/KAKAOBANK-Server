package sopt.mobile2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sopt.mobile2.dto.MonthlyTransferRequestDto;
import sopt.mobile2.service.MonthlyTransferService;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transfer-list")
public class MonthlyTransferController {

    private final MonthlyTransferService monthlyTransferService;
    private static int month = LocalDateTime.now().getMonthValue();

    @GetMapping("")
    public ResponseEntity getTransferListByMonth(@RequestBody MonthlyTransferRequestDto requestDto) {
        month = LocalDateTime.now().getMonthValue();
        return ResponseEntity.ok(monthlyTransferService.getTransferByMonth(month, requestDto));
    }

    @GetMapping("/next-month")
    public ResponseEntity getTransferListByNextMonth(@RequestBody MonthlyTransferRequestDto requestDto) {
        month ++;
        return ResponseEntity.ok(monthlyTransferService.getTransferByMonth(month, requestDto));
    }

    @GetMapping("/before-month")
    public ResponseEntity getTransferListByBeforeMonth(@RequestBody MonthlyTransferRequestDto requestDto) {
        month --;
        return ResponseEntity.ok(monthlyTransferService.getTransferByMonth(month, requestDto));
    }
}
