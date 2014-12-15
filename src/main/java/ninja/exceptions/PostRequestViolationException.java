package ninja.exceptions;

import java.io.Serializable;
import java.util.List;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import ninja.validation.FieldViolation;

public class PostRequestViolationException extends BadRequestException {

    //Rather than have to deal with another mixin for FieldViolation this is a simple wrapper
    static class Violation implements Serializable{
        public String field;
        public String message;
        public Violation(String field, String message) {
            this.field = field;
            this.message = message;
        }
    }

    final static String DEFAULT_MESSAGE = "There exists violations with the expected post request.";

    private final List<Violation> violations;

    public PostRequestViolationException(List<FieldViolation> fieldViolations) {
        super(DEFAULT_MESSAGE);
        this.violations = FluentIterable.from(fieldViolations).transform(new Function<FieldViolation,Violation>() {
            @Override
            public Violation apply(FieldViolation input) {
                return new Violation(input.field, input.constraintViolation.getMessageKey());
            }
        }).toList();
    }

    public List<Violation> getViolations() {
        return violations;
    }


}
