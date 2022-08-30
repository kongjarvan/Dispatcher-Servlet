package site.metacoding.ds;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doProcess 요청됨");
		String httpMethod = req.getMethod();
		System.out.println(httpMethod); // 어떤 메서드를 요청 받았는지 출력해줌
		String identifier = req.getRequestURI();
		System.out.println(identifier); // 로컬호스트 기본주소 (localhost:8000) 이후의 주소를 출력해줌

		UserController c = new UserController();

		Method[] methodList = c.getClass().getDeclaredMethods();

		// foreach
		for (Method method : methodList) {
			// System.out.println(method.getName()); // 클래스가 가지고 있는 메서드를 전부 때림
			Annotation annotation = method.getDeclaredAnnotation(RequestMapping.class);
			RequestMapping requestMapping = (RequestMapping) annotation; // 다운캐스팅
			try {
				if(identifier.equals(requestMapping.value())) {
					method.invoke(c); // invoke: c를 new 함
				}
			} catch (Exception e) {
				System.out.println(method.getName()+"은 어노테이션이 없습니다.");
			}
		}
	}
}
