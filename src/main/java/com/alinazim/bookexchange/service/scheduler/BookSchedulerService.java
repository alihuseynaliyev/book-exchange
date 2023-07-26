package com.alinazim.bookexchange.service.scheduler;

import com.alinazim.bookexchange.model.response.BookResponse;
import com.alinazim.bookexchange.service.concrete.BookServiceHandle;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookSchedulerService {
    private final BookServiceHandle bookServiceHandle;

    @Scheduled(cron = "0 * * * * ?")
    public void printAllBooks() {
        List<BookResponse> bookResponses = bookServiceHandle.getAllBooks();
        for (BookResponse bookResponse : bookResponses) {
            System.out.println(bookResponse);
        }
    }
}