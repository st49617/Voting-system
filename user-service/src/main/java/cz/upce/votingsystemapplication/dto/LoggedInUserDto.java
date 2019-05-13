package cz.upce.votingsystemapplication.dto;


public class LoggedInUserDto {

    private UserDto user;
    private String jwtToken;

    public LoggedInUserDto() {
    }

    public LoggedInUserDto(UserDto user, String jwtToken) {
        this.user = user;
        this.jwtToken = jwtToken;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
