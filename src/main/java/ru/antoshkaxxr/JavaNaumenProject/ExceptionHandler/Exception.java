package ru.antoshkaxxr.JavaNaumenProject.ExceptionHandler;

public final class Exception {
    private String message;

    /**
     * Приватный конструктор для создания экземпляра исключения с указанным сообщением.
     *
     * @param message Сообщение об ошибке.
     */
    private Exception(String message) {
        this.message = message;
    }

    /**
     * Возвращает сообщение об ошибке.
     *
     * @return Сообщение об ошибке.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Устанавливает сообщение об ошибке.
     *
     * @param message Сообщение об ошибке.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Создает экземпляр исключения на основе переданного исключения.
     *
     * @param e Исключение, на основе которого создается новое исключение.
     * @return Новый экземпляр исключения с сообщением, взятым из переданного исключения.
     */
    public static Exception create(Throwable e) {
        return new Exception(e.getMessage());
    }

    /**
     * Создает экземпляр исключения с указанным сообщением.
     *
     * @param message Сообщение об ошибке.
     * @return Новый экземпляр исключения с указанным сообщением.
     */
    public static Exception create(String message) {
        return new Exception(message);
    }
}
