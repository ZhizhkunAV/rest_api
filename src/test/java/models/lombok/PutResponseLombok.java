package models.lombok;

import lombok.Data;

import java.util.Date;

@Data
public class PutResponseLombok {
    public String name;
    public String job;
    public Date updatedAt;
    public Integer id;

}