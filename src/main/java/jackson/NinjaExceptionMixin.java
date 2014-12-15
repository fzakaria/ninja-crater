package jackson;

import com.fasterxml.jackson.annotation.JsonProperty;

abstract class NinjaExceptionMixin extends ThrowableMixin {

    @JsonProperty
    public int httpStatus;

    abstract int getHttpStatus();
}
