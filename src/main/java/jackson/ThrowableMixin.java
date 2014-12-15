package jackson;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

/***
 * This is a mixin class to add @JsonFilter so that we can customize
 * the filtering of the stacktrace message based on application mode
 * @link http://wiki.fasterxml.com/JacksonMixInAnnotations
 * @link http://wiki.fasterxml.com/JacksonFeatureJsonFilter
 */
@JsonFilter("exceptionFilter")
abstract class ThrowableMixin {

    @JsonIgnore
    abstract Throwable getCause();

    @JsonIgnore
    abstract Throwable[] getSuppressed();

    @JsonIgnore
    abstract String getLocalizedMessage();

}