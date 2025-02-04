package org.example.springbootpractice.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.springbootpractice.entity.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String userId;
    private String userPw;
    private String userName;
    private String userEmail;
    private String userPhone;
    private String userBusinessNumber;
    private Boolean marketingAgreed;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.userId = user.getUserId();
        this.userPw = user.getUserPw();
        this.userName = user.getUserName();
        this.userEmail = user.getUserEmail();
        this.userPhone = user.getUserPhone();
        this.userBusinessNumber = user.getUserBusinessNumber();
        this.marketingAgreed = user.isMarketingAgreed();
    }
}
