package com.pivseacrh.pivseacrh.command;

import com.pivseacrh.pivseacrh.entity.Organisation;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Location;

import java.util.List;

@Component
public class DefaultCommand implements OrgSearchCommand{
    @Override
    public String getCommandName() {
        return "";
    }

    @Override
    public List<Organisation> getOrganisations(Location location) {
        return null;
    }

    @Override
    public String getSearchName() {
        return null;
    }

}
