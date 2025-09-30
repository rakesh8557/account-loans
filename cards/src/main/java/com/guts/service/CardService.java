package com.guts.service;

import com.guts.dto.CardsDTO;

public interface CardService {
    void createCard(String mobileNumber);

    CardsDTO getCard(String mobileNumber);

    boolean updateCard(CardsDTO cardsDTO);

    boolean deleteCard(String mobileNumber, String cardNumber);
}
