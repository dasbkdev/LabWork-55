package homework.lesson55.dto;

public class OptionResponseDto {
    private Long id;
    private String optionText;

    public OptionResponseDto() {
    }

    public OptionResponseDto(Long id, String optionText) {
        this.id = id;
        this.optionText = optionText;
    }

    public Long getId() {
        return id;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }
}