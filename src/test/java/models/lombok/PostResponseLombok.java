package models.lombok;

import lombok.Data;

import java.util.Date;

@Data
public class PostResponseLombok {
    public String name;
    public String job;
    public String id;
    public Date createdAt;
}