package ru.job4j.javajobsparsing;

/**
 * Class represents vacancy.
 *
 * @since 05/12/2017
 * @version 1
 */
public class Vacancy {

    private String vacancyName;
    private String vacancyText;
    private String datePublishing;
    private String dateParsing;
    private String link;

    public Vacancy(String vacancyName, String vacancyText, String datePublishing, String dateParsing, String link) {
        this.vacancyName = vacancyName;
        this.vacancyText = vacancyText;
        this.datePublishing = datePublishing;
        this.dateParsing = dateParsing;
        this.link = link;
    }

    public Vacancy() {
    }

    public String getVacancyName() {
        return vacancyName;
    }

    public String getVacancyText() {
        return vacancyText;
    }

    public String getDatePublishing() {
        return datePublishing;
    }

    public String getDateParsing() {
        return dateParsing;
    }

    public void setVacancyName(String vacancyName) {
        this.vacancyName = vacancyName;
    }

    public void setVacancyText(String vacancyText) {
        this.vacancyText = vacancyText;
    }

    public void setDatePublishing(String datePublishing) {
        this.datePublishing = datePublishing;
    }

    public void setDateParsing(String dateParsing) {
        this.dateParsing = dateParsing;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vacancy vacancy = (Vacancy) o;

        if (getVacancyName() != null ? !getVacancyName().equals(vacancy.getVacancyName()) : vacancy.getVacancyName() != null)
            return false;
        if (getVacancyText() != null ? !getVacancyText().equals(vacancy.getVacancyText()) : vacancy.getVacancyText() != null)
            return false;
        if (getDatePublishing() != null ? !getDatePublishing().equals(vacancy.getDatePublishing()) : vacancy.getDatePublishing() != null)
            return false;
        return getDateParsing() != null ? getDateParsing().equals(vacancy.getDateParsing()) : vacancy.getDateParsing() == null;
    }

    @Override
    public int hashCode() {
        int result = getVacancyName() != null ? getVacancyName().hashCode() : 0;
        result = 31 * result + (getVacancyText() != null ? getVacancyText().hashCode() : 0);
        result = 31 * result + (getDatePublishing() != null ? getDatePublishing().hashCode() : 0);
        result = 31 * result + (getDateParsing() != null ? getDateParsing().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String sep = System.lineSeparator();
        return sep + "=====================" + sep +
                "Title: " + vacancyName + sep +
                "Text: " + vacancyText + sep +
                "Time of publishing: " + datePublishing + sep +
                "Time of parsing: " + dateParsing + sep +
                "Link: " + link + sep;
    }
}
