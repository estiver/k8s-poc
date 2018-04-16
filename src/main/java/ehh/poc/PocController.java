package ehh.poc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.client.ApiException;
import io.swagger.client.api.PersonControllerApi;

@RestController
@RequestMapping("/")
@RefreshScope
public class PocController {

	private Logger logger = LoggerFactory.getLogger(PocController.class);

	@Value("${template}")
	private String template;

//	@Autowired
//	private PersonRepository pessoaRepository;
	
	@Autowired
	private PersonControllerApi personControllerApi;

	@GetMapping(value = "/hello/pessoa/{id}", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Hello> retrieve(@PathVariable("id") String id) throws ApiException {
		logger.info(String.format("retrieve(id: %s)", id));

		//Person pessoa = pessoaRepository.findOne(id);
		io.swagger.client.model.Person pessoa = personControllerApi.getPersonUsingGET(id);
		Hello hello = new Hello();
		hello.setMsg(template.replaceAll("\\$id", pessoa.getId()).replaceAll("\\$name", pessoa.getName()));

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("hostname", System.getenv("HOSTNAME"));

		return new ResponseEntity<Hello>(hello, responseHeaders, HttpStatus.OK);
	}
//
//	@PostMapping(value = "/pessoa/", produces = "application/json; charset=UTF-8")
//	public void create(@RequestBody Person pessoa) {
//		logger.info(String.format("create(id: %s, name: %s)", pessoa.getId(), pessoa.getName()));
//		pessoaRepository.save(pessoa);
//	}
}
