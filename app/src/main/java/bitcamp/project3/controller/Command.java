package bitcamp.project3.controller;

public interface Command {
    void cmd();
    void printTUI();
    void create();
    void read();
    void update();
    void delete();
}
