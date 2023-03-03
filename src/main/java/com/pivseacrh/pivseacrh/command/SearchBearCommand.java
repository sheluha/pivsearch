package com.pivseacrh.pivseacrh.command;

import com.pivseacrh.pivseacrh.entity.Organisation;
import com.pivseacrh.pivseacrh.service.OrganisationsSearchService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Location;

import java.util.List;

@Component
@AllArgsConstructor
public class SearchBearCommand implements OrgSearchCommand{

    private final OrganisationsSearchService organisationsSearchService;

    @Override
    public String getCommandName() {
        return "/пиво";
    }

    @Override
    public List<Organisation> getOrganisations(Location location) {
        return organisationsSearchService.getOrganisations(getSearchName(),location);
    }

    @Override
    public String getSearchName() {
        return "Пиво";
    }
}
