package com.pivseacrh.pivseacrh.service;

import com.pivseacrh.pivseacrh.config.BotConfig;
import com.pivseacrh.pivseacrh.entity.Organisation;
import com.pivseacrh.pivseacrh.entity.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.telegrambots.meta.api.objects.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class OrganisationsSearchService {

    private final WebClient webClient;

    private final BotConfig botConfig;

    private Root fetchOrganisations(String name, Location location){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/")
                        .queryParam("text",name)
                        .queryParam("type","biz")
                        .queryParam("lang","ru_RU")
                        .queryParam("results",5)
                        .queryParam("ll",location.getLongitude() + "," + location.getLatitude())
                        .queryParam("spn","0.552069,0.400552")
                        .queryParam("apikey",botConfig.getApikey())
                        .build())
                .retrieve()
                .bodyToMono(Root.class)
                .block();

    }

    public List<Organisation> getOrganisations(String name, Location location){
        Root root = fetchOrganisations(name,location);
        List<Organisation> result = new ArrayList<>();
        for (Root.Feature f : root.getFeatures()){
            Root.CompanyMetaData companyMetaData = f.getProperties().getCompanyMetaData();

            Organisation org = new Organisation();
            org.setName(companyMetaData.getName());
            org.setAddress(companyMetaData.getAddress());
            org.setUrl(companyMetaData.getUrl());
            org.setHours(companyMetaData.getHours().getText());
            org.setCategories(companyMetaData.getCategories().stream().map(Root.Category::getName).collect(Collectors.toList()));
            org.setCoordinates(f.getGeometry().getCoordinates());

            result.add(org);
        }
        return result;
    }
}
