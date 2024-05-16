package sopt.mobile2.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import sopt.mobile2.dto.RecentTransferResponse;
import sopt.mobile2.service.RecentTransferService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RecentTransferControllerTest {

    @Mock
    private RecentTransferService recentTransferService;

    @InjectMocks
    private RecentTransferController recentTransferController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
    @Test
    public void testGetRecentTransfersByAccount() {
        Long accountId = 1L;
        List<RecentTransferResponse> mockResponse = List.of(
                new RecentTransferResponse("John Doe", 123456, true, null),
                new RecentTransferResponse("Jane Smith", 654321, false, null)
        );

        when(recentTransferService.recentTransferList(accountId)).thenReturn(mockResponse);

        ResponseEntity<List<RecentTransferResponse>> response = recentTransferController.getRecentTransfersByAccount(accountId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockResponse, response.getBody());
    }
     **/

    @Test
    public void testAddBookMark() {
        Long myAccountId = 1L;
        Long markedAccountId = 2L;

        ResponseEntity<Void> response = recentTransferController.addBookMark(myAccountId, markedAccountId);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testRemoveBookMark() {
        Long myAccountId = 1L;
        Long markedAccountId = 2L;

        ResponseEntity<Void> response = recentTransferController.removeBookMark(myAccountId, markedAccountId);

        assertEquals(200, response.getStatusCodeValue());
    }
}
