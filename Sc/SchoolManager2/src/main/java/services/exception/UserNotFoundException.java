package services.exception;

public class UserNotFoundException extends RuntimeException
{
    private int userId;

    public UserNotFoundException(int userId)
    {
        this.userId = userId;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    @Override
    public String toString()
    {
        return "UserNotFoundException{" +
                "userId=" + userId +
                '}';
    }
}
