package com.pivseacrh.pivseacrh.command;

import com.pivseacrh.pivseacrh.entity.Organisation;
import org.telegram.telegrambots.meta.api.objects.Location;

import java.util.List;

public interface OrgSearchCommand {

    String getCommandName();

    List<Organisation> getOrganisations(Location location);

    String getSearchName();

}
