package interfaces;

import models.User;

public interface IRepositoryUser {
    User searchById(int id);
}
