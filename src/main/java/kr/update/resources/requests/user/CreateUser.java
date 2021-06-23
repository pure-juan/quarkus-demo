package kr.update.resources.requests.user;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateUser {

    @NotEmpty(message="Username may not be empty")
    private String username;

    @NotEmpty(message="Name may not be empty")
    private String name;

}
