package exceptions;

import utils.Messages;

import javax.persistence.EntityExistsException;

public class StudentAlreadyExistException extends EntityExistsException {

    public StudentAlreadyExistException(long id) {
        super(String.format(Messages.ENTITY_WITH_ID_EXIST, "Student", id));
    }
}
