package models.lombok;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class UserData {

    private Integer id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String email;

    private String avatar;

}