package models.lombok;

import lombok.Data;

import java.util.Date;

@Data
public class CreateUserResponseLombok {
    public String name;
    public String job;
    public String id;
    public Date createdAt;
}