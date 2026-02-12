package model01.dto01;

import java.time.LocalDate;

public record StudentRequestDto(
        String fullName,
        String gender,
        LocalDate dateOfBirth
) {}