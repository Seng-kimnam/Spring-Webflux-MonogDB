package _skn.electricshopapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private Boolean success;
    private String message;

    //  return numeric status (better for frontend)
    private int status;
    private int code;

    private T payload;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    //  SUCCESS
    public static <T> ApiResponse<T> success(String message, T payload) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .status(HttpStatus.OK.value())
                .code(0)
                .payload(payload)
                .build();
    }

    //  SUCCESS (no payload)
    public static <T> ApiResponse<T> success(String message) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .status(HttpStatus.OK.value())
                .code(0)
                .build();
    }

    //  ERROR
    public static <T> ApiResponse<T> error(String message, HttpStatus status) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .status(status.value())
                .code(status.value())
                .build();
    }
}