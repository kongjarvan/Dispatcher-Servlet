package site.metacoding.ds;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.METHOD}) // 메서드, 클래스, 필드(변수) (target은 여러개 지정 가능하기 때문에 배열이 나옴)
@Retention(RetentionPolicy.RUNTIME) // runtime(런타임 시 등장), source(컴파일 시 등장)
public @interface RequestMapping {

	String value(); // value메서드 = 키값(고정)
	
	
}
