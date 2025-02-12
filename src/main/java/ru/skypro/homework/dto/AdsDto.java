package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdsDto {
    private Integer count;
    private List<AdDto> results;

    public AdsDto(List<AdDto> results) {
        this.results = results;
        this.count = results.size();
    }
}
