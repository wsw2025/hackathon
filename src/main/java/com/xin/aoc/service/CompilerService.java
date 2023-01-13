package com.xin.aoc.service;

public interface CompilerService {
    public String compile(String name, String code);
    public String execute(String name, String input);
}
