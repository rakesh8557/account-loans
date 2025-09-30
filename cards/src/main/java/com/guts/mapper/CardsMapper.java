package com.guts.mapper;

import com.guts.dto.CardsDTO;
import com.guts.entity.Cards;

public class CardsMapper {

    public static CardsDTO mapToDTO(Cards cards, CardsDTO cardsDTO) {
        cardsDTO.setCardNumber(cards.getCardNumber());
        cardsDTO.setCardType(cards.getCardType());
        cardsDTO.setMobileNumber(cards.getMobileNumber());
        cardsDTO.setTotalLimit(cards.getTotalLimit());
        cardsDTO.setAmountUsed(cards.getAmountUsed());
        cardsDTO.setAvailableLimit(cards.getAvailableLimit());
        return cardsDTO;
    }

    public static Cards mapToEntity(CardsDTO cardsDTO, Cards cards) {
        cards.setCardNumber(cardsDTO.getCardNumber());
        cards.setCardType(cardsDTO.getCardType());
        cards.setMobileNumber(cardsDTO.getMobileNumber());
        cards.setTotalLimit(cardsDTO.getTotalLimit());
        cards.setAmountUsed(cardsDTO.getAmountUsed());
        cards.setAvailableLimit(cardsDTO.getAvailableLimit());
        return cards;
    }

}
