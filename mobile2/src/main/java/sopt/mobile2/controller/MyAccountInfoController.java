package sopt.mobile2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sopt.mobile2.service.AccountService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account-info")
public class MyAccountInfoController {

    private final AccountService accountService;

    @GetMapping("/{accountId}")
    public ResponseEntity getAccountInfo(@PathVariable(name = "accountId") Long accountId) {
        return ResponseEntity.ok(accountService.getMyAccountInfo(accountId));
    }

}