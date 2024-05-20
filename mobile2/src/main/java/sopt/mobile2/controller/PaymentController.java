package sopt.mobile2.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sopt.mobile2.dto.MonthlyTransferRequestDto;
import sopt.mobile2.dto.PaymentResponse;
import sopt.mobile2.dto.PaymentResponseDto;
import sopt.mobile2.service.PaymentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/{accountId}")
    public ResponseEntity<PaymentResponseDto> getMonthlyPayment(@PathVariable(name = "accountId") Long accountId,
                                                                @RequestParam(value = "month") int month){
        return ResponseEntity.ok(paymentService.getMonthPayment(accountId, month));
    }
}
