package exceptions;

import utils.Messages;

import javax.persistence.EntityNotFoundException;

public class StudentNotFoundException extends EntityNotFoundException {

    public StudentNotFoundException(long id) {
        super(String.format(Messages.ENTITY_BY_ID_NOT_FOUND, "Student", id));
    }
}
