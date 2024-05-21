package models.lombok;

import lombok.Data;

import java.util.Date;

@Data
public class CheckEmailResponseLombok {
    private UserData data;
    public Date updatedAt;
    private SupportData support;
}