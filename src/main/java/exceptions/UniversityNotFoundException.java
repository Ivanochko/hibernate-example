package exceptions;

import utils.Messages;

import javax.persistence.EntityNotFoundException;

public class UniversityNotFoundException extends EntityNotFoundException {

    public UniversityNotFoundException(long id) {
        super(String.format(Messages.ENTITY_BY_ID_NOT_FOUND, "University", id));
    }
}
