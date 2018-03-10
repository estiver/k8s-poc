package ehh.poc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RefreshScope
public class PocController {

	private Logger logger = LoggerFactory.getLogger(PocController.class);

	@Value("${template}")
	private String template;

	@GetMapping(value = "/hello/pessoa/{nome}", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Hello> hello(@PathVariable("nome") String nome) {
		logger.info(String.format("hello(id: %s)", nome));

		Hello hello = new Hello();
		hello.setMsg(template.replaceAll("\\$name", nome));

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("hostname", System.getenv("HOSTNAME"));

		return new ResponseEntity<Hello>(hello, responseHeaders, HttpStatus.OK);
	}

}
