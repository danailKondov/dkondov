package ru.job4j.tracker;

/**
* Class for input/output operations.
* @since 04/08/2017
* @version 1
**/
public class ValidateInput extends ConsoleInput {
    /**
     * Method for asking menu options.
     * @param question - text of question
     * @param range - range of answers
     * @return number of menu option
     */
    @Override
    public int ask(String question, int[] range) {
        while(true) {
            try {
                return super.ask(question,range);
            } catch (MenuOutException e) {
                System.out.println("Please select key form menu");
            } catch (NumberFormatException e) {
                System.out.println("Please enter number of option again");
            }
        }
    }
}
