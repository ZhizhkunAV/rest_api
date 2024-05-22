package models.lombok;

import lombok.Data;

import java.util.Date;

@Data
public class UserModel {
    private UserData data;
    public Date updatedAt;
    private SupportData support;
}