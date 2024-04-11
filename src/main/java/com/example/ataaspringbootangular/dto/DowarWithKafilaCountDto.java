package com.example.ataaspringbootangular.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DowarWithKafilaCountDto {
    private DowarDto dowar;
    private int arrivedKafilaCount;
}
