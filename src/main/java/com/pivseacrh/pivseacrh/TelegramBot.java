package com.pivseacrh.pivseacrh;

import com.pivseacrh.pivseacrh.command.DefaultCommand;
import com.pivseacrh.pivseacrh.command.OrgSearchCommand;
import com.pivseacrh.pivseacrh.config.BotConfig;
import com.pivseacrh.pivseacrh.entity.Organisation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig botConfig;

    private final Map<String, OrgSearchCommand> commandMap;

    private Location currentLocation;

    public TelegramBot(BotConfig botConfig, List<OrgSearchCommand> list) {
        this.botConfig = botConfig;
        this.commandMap = list.stream().collect(Collectors.toMap(OrgSearchCommand::getCommandName, Function.identity()));
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage().getLocation() != null) {
            currentLocation = update.getMessage().getLocation();
            SendMessage response = new SendMessage(update.getMessage().getChatId().toString(),"Location updated successfully!");
            try {
                execute(response);
            } catch (TelegramApiException e) {
                log.error(e.getMessage());
            }
            return;
        }
        if(currentLocation == null){
            SendMessage response = new SendMessage(update.getMessage().getChatId().toString(),"Set location first");
            try {
                execute(response);
            } catch (TelegramApiException e) {
                log.error(e.getMessage());
            }
            return;
        }
        OrgSearchCommand searchCommand = commandMap.getOrDefault(update.getMessage().getText(),new DefaultCommand());
        for(Organisation organisation: searchCommand.getOrganisations(currentLocation)){
            SendMessage response = new SendMessage(update.getMessage().getChatId().toString(),organisation.toString());
            try {
                execute(response).setLocation(new Location());
                execute(SendLocation.builder()
                        .longitude(organisation.getCoordinates().get(0))
                        .latitude(organisation.getCoordinates().get(1))
                        .chatId(update.getMessage().getChatId().toString())
                        .build());

            } catch (TelegramApiException e) {
                log.error(e.getMessage());
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }
    @Override
    public String getBotToken() {
        return botConfig.getBotToken();
    }

}
