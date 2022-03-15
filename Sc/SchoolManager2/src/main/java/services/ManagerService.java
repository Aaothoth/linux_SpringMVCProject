package services;

import model.User;

public interface ManagerService
{
    User findUserByUserNumber(int id);
}
