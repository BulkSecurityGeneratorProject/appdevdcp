package br.com.lasa.dcpdesconformidades.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

/**
 * Simple exception with a message, that returns an Internal Server Error code.
 */
public class PreconditionFailedException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    public PreconditionFailedException(String message) {
        super(ErrorConstants.DEFAULT_TYPE, message, Status.PRECONDITION_FAILED);
    }
}
