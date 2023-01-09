package com.xin.aoc.service;

public interface MailService {
    public boolean sendMessage(String toEmailAddress, String subject, String bodyText);
}
