package sopt.mobile2.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sopt.mobile2.service.AccountService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account-list")
public class AccountListController {

    private final AccountService accountService;

    @GetMapping("{memberId}")
    public ResponseEntity getAccountList(@PathVariable(name = "memberId") Long memberId) {
        return ResponseEntity.ok(accountService.getMyAccountList(memberId));
    }
}
