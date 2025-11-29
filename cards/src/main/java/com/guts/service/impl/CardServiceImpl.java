package com.guts.service.impl;

import com.guts.constants.CardsConstants;
import com.guts.dto.CardsDTO;
import com.guts.entity.Cards;
import com.guts.exception.ResourceAlreadyExistsException;
import com.guts.exception.ResourceNotFoundException;
import com.guts.mapper.CardsMapper;
import com.guts.repo.CardsRepo;
import com.guts.service.CardService;
//import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
//@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    @Autowired
    private CardsRepo cardsRepo;

    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> optionalCards = cardsRepo.findByMobileNumber(mobileNumber);
        if (optionalCards.isPresent()) {
            throw new ResourceAlreadyExistsException("Card", "mobileNumber", mobileNumber);
        }
        cardsRepo.save(createNewCard(mobileNumber));
    }

    public Cards createNewCard(String mobileNumber) {
        Cards cards = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        cards.setCardNumber(Long.toString(randomCardNumber));
        cards.setMobileNumber(mobileNumber);
        cards.setCardType(CardsConstants.CREDIT_CARD);
        cards.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        cards.setAmountUsed(0);
        cards.setAvailableLimit(CardsConstants.NEW_CARD_LIMIT);
//        System.out.println(cards);
        return cards;
    }

    @Override
    public CardsDTO getCard(String mobileNumber) {
        Cards cards = cardsRepo.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Card", "Mobile Number", mobileNumber));
        return CardsMapper.mapToDTO(cards, new CardsDTO());
    }

    @Override
    public boolean updateCard(CardsDTO cardsDTO) {
        Cards cards = cardsRepo.findByMobileNumberAndCardNumber(cardsDTO.getMobileNumber(), cardsDTO.getCardNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Card", "Mobile number and Card Number", String.format("%s, %s", cardsDTO.getMobileNumber(), cardsDTO.getCardNumber())));
        System.out.println(cards);
        cards.setTotalLimit(cardsDTO.getTotalLimit());
        cards.setAmountUsed(cardsDTO.getAmountUsed());
        cards.setAvailableLimit(cardsDTO.getAvailableLimit());
        cardsRepo.save(cards);
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber, String cardNumber) {
        Cards cards = cardsRepo.findByMobileNumberAndCardNumber(mobileNumber, cardNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card", "Mobile Number and Card Number", String.format("%s, %s", mobileNumber, cardNumber)));
        cardsRepo.deleteById(cards.getCardId());
        return true;
    }
}
