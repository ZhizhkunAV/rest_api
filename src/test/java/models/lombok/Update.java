package models.lombok;

import lombok.Data;

import java.util.Date;

@Data
public class Update {
    public String name;
    public String job;
    public Date updatedAt;
}