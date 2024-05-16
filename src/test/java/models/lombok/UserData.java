package models.lombok;

import lombok.Data;

import java.util.Date;

@Data
public class UserData {
    public int id;
    public String email;
    public String first_name;
    public String last_name;
    public String avatar;

}