package me.jics;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.micronaut.http.annotation.PathVariable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Introspected
public class User {

    @PathVariable
    private String username;
    @PathVariable
    private String firstName;
    @PathVariable
    private String lastName;

}