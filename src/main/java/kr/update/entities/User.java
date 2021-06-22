package kr.update.entities;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String id;
    private String username;

    private String name;

    private LocalDateTime createdAt;

}
