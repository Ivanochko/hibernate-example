package exceptions;

import utils.Messages;

import javax.persistence.EntityExistsException;

public class UniversityAlreadyExistException extends EntityExistsException {

    public UniversityAlreadyExistException(long id) {
        super(String.format(Messages.ENTITY_WITH_ID_EXIST, "University", id));
    }
}
