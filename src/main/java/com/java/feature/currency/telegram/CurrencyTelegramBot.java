package com.java.feature.currency.telegram;

import com.java.feature.currency.CurrencyService;
import com.java.feature.currency.PrivatBankCurrencyService;
import com.java.feature.currency.currency.Currency;
import com.java.feature.currency.telegram.command.HelpCommand;
import com.java.feature.currency.telegram.command.StartCommand;
import com.java.feature.currency.ui.PrettyPrintCurrencyService;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;


public class CurrencyTelegramBot extends TelegramLongPollingCommandBot {

    private CurrencyService currencyService;
    private PrettyPrintCurrencyService prettyPrintCurrencyService;

    public CurrencyTelegramBot() {
        currencyService = new PrivatBankCurrencyService();
        prettyPrintCurrencyService = new PrettyPrintCurrencyService();
        register(new StartCommand());
        register(new HelpCommand());
    }
    @Override
    public void processNonCommandUpdate(Update update) {
        if(update.hasCallbackQuery()){


            String data = update.getCallbackQuery().getData();
            Currency currency = Currency.valueOf(data);


            try {
                double rate = currencyService.getRate(currency);
                String prettyText = prettyPrintCurrencyService.convert(rate,currency);
                SendMessage responseMessage = new SendMessage();
                responseMessage.setText(prettyText);
                Long chatId = update.getCallbackQuery().getMessage().getChatId();
                responseMessage.setChatId(Long.toString(chatId));


                execute(responseMessage);



            } catch (IOException | TelegramApiException e) {
                throw new RuntimeException(e);
            }


        }


        if(update.hasMessage()){
            String message = update.getMessage().getText();
            String responseText = "You are write: "+message;

            SendMessage sendMessage = new SendMessage();
            sendMessage.setText(responseText);
            sendMessage.setChatId(Long.toString(update.getMessage().getChatId()));
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public String getBotUsername() {
        return BotConstants.BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BotConstants.BOT_TOKEN;
    }


}
