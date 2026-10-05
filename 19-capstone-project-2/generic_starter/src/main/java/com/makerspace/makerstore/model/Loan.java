package com.makerspace.makerstore.model;

import java.time.LocalDate;

/** A tool lent to a member. Records are perfect here: a loan's details never change. */
public record Loan(String toolId, String memberId, LocalDate borrowed, LocalDate due) {

    public boolean isOverdue(LocalDate today) {
        return today.isAfter(due);
    }
}
