package jackson;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

/***
 * This is a mixin class to add @JsonFilter so that we can customize
 * the filtering of the stacktrace message based on application mode
 * @link http://wiki.fasterxml.com/JacksonMixInAnnotations
 * @link http://wiki.fasterxml.com/JacksonFeatureJsonFilter
 */
@JsonFilter("exceptionFilter")
@JsonTypeInfo(use=Id.CLASS, include=As.PROPERTY, property="class")
abstract class ThrowableMixin {

    @JsonIgnore
    abstract Throwable getCause();

    @JsonIgnore
    abstract Throwable[] getSuppressed();

    @JsonIgnore
    abstract String getLocalizedMessage();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    abstract String getMessage();

}