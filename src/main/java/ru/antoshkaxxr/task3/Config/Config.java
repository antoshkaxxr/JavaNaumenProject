package ru.antoshkaxxr.task3.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.antoshkaxxr.task3.ConsoleInput.CommandProcessor;

import java.util.Scanner;

@Configuration
public class Config
{
    @Autowired
    private CommandProcessor commandProcessor;
    @Bean
    public CommandLineRunner commandScanner()
    {
        return args ->
        {
            try (Scanner scanner = new Scanner(System.in))
            {
                System.out.println("Введите команду. 'exit' для выхода.");
                while (true)
                {
// Показать приглашение для ввода
                    System.out.print("> ");
                    String input = scanner.nextLine();
// Выход из цикла, если введена команда "exit"
                    if ("exit".equalsIgnoreCase(input.trim()))
                    {
                        System.out.println("Выход из программы...");
                        break;
                    }
// Обработка команды
                    commandProcessor.processCommand(input);
                }
            }
        };
    }
}
