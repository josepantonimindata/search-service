package com.mindata.searchservice.lib.search.application;

import com.mindata.searchservice.lib.shared.domain.bus.command.Command;

import java.util.List;

public record SearchCommand(
    String searchId,
    String hotelId,
    String checkIn,
    String checkOut,
    List<Integer> ages
) implements Command {
}
