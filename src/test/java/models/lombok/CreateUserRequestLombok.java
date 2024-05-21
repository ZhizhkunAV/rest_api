package models.lombok;

import lombok.Data;

import java.util.Date;

@Data
public class CreateUserRequestLombok {
    public String name;
    public String job;
    public String id;
    public Date createdAt;
}