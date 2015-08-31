package tr.org.lkd.lyk2015.camp.config;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Layout {
    String value();
}